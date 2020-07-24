package QueryManager.queries;

import android.hardware.Sensor;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import QueryManager.QueryManager;
import QueryManager.queries.enums.QueryEventType;
import QueryManager.queries.enums.QueryTiming;
import QueryManager.queries.interfaces.MeasurementBased;
import QueryManager.queries.interfaces.QueryEvent;
import QueryManager.queries.interfaces.ThreadBased;
import QueryManager.queries.interfaces.TimeBased;
import QueryManager.sensors.SensorHandler;

@SuppressWarnings(value = "unchecked")
public class EventBased<T> extends Query<T> implements  ThreadBased, TimeBased,
        MeasurementBased {
    public static final int TIME_BASED   = 0X01;
    public static final int MEASUREMENTS = 0X02;
    private Map<Sensor,QueryEvent> sensorQueryEventMap;
    private Thread thread;
    private long _for              = 0;
    private long _every            = 0;
    private Boolean isContinues    = true;
    private int measureNumber      = 0;
    private Boolean isInfinite     = false;
    private Boolean collectValues  = false;
    private Boolean queryDone      = false;
    private int type;

    public EventBased(SensorHandler sensorHandler, List<Sensor> sensors){
        super(sensorHandler,sensors);
        sensorQueryEventMap = new HashMap<>();
    }

    private <C extends Comparable<? super C>> int compareResult(T values,C compareValue){
       C value;
       if(values.getClass().isArray()){
           value = (C) Array.get(values,0);
       }else{
           value = (C) values;
       }
       return value.compareTo(compareValue);
    }

    public <C extends Comparable<? super C>> void addEvent(Sensor sensor, QueryEventType type, final C compareValue) throws Exception {
        QueryEvent<T> event;
        switch (type){
            case EQUAL:
                event = new QueryEvent<T>() {
                    @Override
                    public Boolean conditionTrue(T values) {
                        return compareResult(values,compareValue) == 0;
                    }
                };
                break;
            case GREATER:
                event = new QueryEvent<T>() {
                    @Override
                    public Boolean conditionTrue(T values) {
                        return compareResult(values,compareValue) > 0;
                    }
                };
                break;
            case LOWER:
                event = new QueryEvent<T>() {
                    @Override
                    public Boolean conditionTrue(T values) {
                        return compareResult(values,compareValue) < 0;
                    }
                };
                break;
            case GREATER_OR_EQUAL:
                event = new QueryEvent<T>() {
                    @Override
                    public Boolean conditionTrue(T values) {
                        return compareResult(values,compareValue) >= 0;
                    }
                };
                break;
            case LOWER_OR_EQUAL:
                event = new QueryEvent<T>() {
                    @Override
                    public Boolean conditionTrue(T values) {
                        return compareResult(values,compareValue) <= 0;
                    }
                };
                break;
            case NOT_EQUAL:
                event = new QueryEvent<T>() {
                    @Override
                    public Boolean conditionTrue(T values) {
                        return compareResult(values,compareValue) < 0 ||
                                compareResult(values,compareValue) > 0;
                    }
                };
                break;
            default:
                throw new Exception("Event type not supported");
        }
        sensorQueryEventMap.put(sensor,event);
    }

    private void launchSensors(){
        for(Sensor sensor: sensors){
            sensorHandler.startListener(sensor,this);
        }
    }

   @Override
   public int callback(Sensor sensor,T values){
        if(queryDone && !collectValues) {
            return SensorHandler.SENSOR_STOP;
        }

       if(collectValues) {
           queryResult.getResult().put(sensor.getName().replace(" ","_").toUpperCase(),
                   values);
           if( queryResult.getResult().size() == sensors.size() ) {
               QueryManager.log("VALUES FROM SENSORS COLLECTED, NOW SENDING...");
               collectValues = false;
               onResult(queryResult);
               queryResult.getResult().clear();
               // if not continues then event-sensors have stopped
               // re-initiate
               if(!isContinues) {
                   this.launchEventSensors();
               }
           }
           return SensorHandler.SENSOR_STOP;
       }

       QueryEvent queryEvent = sensorQueryEventMap.get(sensor);
       if(queryEvent != null) {
           if( queryEvent.conditionTrue(values) ) {
               collectValues = true;
               this.launchSensors();
               QueryManager.log("CONDITION IS TRUE, SENSORS INITIATED, COLLECTING VALUES...");
           }
       }

       if(type == MEASUREMENTS) {
           measureNumber--;
           if(measureNumber <= 0) {
               QueryManager.log("MEASUREMENTS REACHED 0, SETTING QUERY_DONE = TRUE");
               queryDone = true;
           }else{
               QueryManager.log("MEASUREMENTS LEFT: " + measureNumber);
           }
       }

       if(queryDone) return SensorHandler.SENSOR_STOP;
       if(isContinues) return SensorHandler.SENSOR_PROCEED;
       return SensorHandler.SENSOR_STOP;
   }

   @Override
   public void setLifeTime(int until, QueryTiming timeType){
        this._for = until * timeType.getValue();
        this.type = TIME_BASED;
   }

   @Override
   public void setPeriodicity(int every, QueryTiming timeType){
        this._every = every * timeType.getValue();
        this.isContinues = false;
   }

   @Override
   public void setMeasurementNumber(int measureNumber){
        this.measureNumber = measureNumber;
        this.type = MEASUREMENTS;
   }


   private void launchEventSensors() {
        final EventBased _this = this;
        sensorQueryEventMap.forEach(new BiConsumer<Sensor, QueryEvent>() {
            @Override
            public void accept(Sensor sensor, QueryEvent event) {
                sensorHandler.startListener(sensor,_this);
            }
        });
   }

   @Override
   public void execute() throws Exception {
        if(type == 0x00) {
            throw new Exception("Event Query type not given");
        }
        if(type == TIME_BASED){
            QueryManager.log(getName() + " QUERY IS TIME-BASED");
            thread = new Thread(this);
            thread.start();
        }else{
            QueryManager.log(getName() + " QUERY IS MEASUREMENT-BASED");
            this.launchEventSensors();
        }

   }

   @Override
   public void execute(List<Sensor> sensors) throws Exception {
        this.sensors = sensors;
        this.execute();
   }

   @Override
   public void run(){
        while ( _for > 0 || isInfinite ){
            try{
                if(!isInfinite){
                    if(!isContinues) {
                        QueryManager.log("QUERY IS TIME-BASED SLEEPING FOR: " + _every);
                        Thread.sleep(_every);
                        this.launchEventSensors();
                        _for -= _every;
                        QueryManager.log("QUERY WOKE UP AND INITIATED EVENT SENSORS, TIME LEFT FOR QUERY TO " +
                                "FINISH: " + _for);
                    }else{
                        QueryManager.log("NO PERIODICITY QUERY WILL END IN: " + _for);
                        this.launchEventSensors();
                        Thread.sleep(_for);
                        _for = 0;
                    }
                }else{
                    if(!isContinues){
                        Thread.sleep(_every);
                        this.launchEventSensors();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                QueryManager.log("ERROR FROM RUN METHOD. (Printed Stack Trace)");
            }
        }
        QueryManager.log("RUN METHOD FINISHED, SETTING QUERY_DONE = TRUE");
        queryDone = true;
   }


   public void setEventType(int type){
        this.type = type;
   }

   public int getEventType(){
        return this.type;
   }

   @Override
   public int getType(){
        return Query.EVENT_BASED;
   }

   @Override
   public Thread getThread(){
        return this.thread;
   }

   @Override
    public Boolean isRunning(){
        return this.thread != null && this.thread.isAlive();
   }

   @Override
    public void interrupt(){
        this.thread.interrupt();
   }
    @Override
    public String getName(){
        return "Event-based";
    }

}
