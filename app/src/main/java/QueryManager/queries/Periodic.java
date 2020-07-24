package QueryManager.queries;

import android.hardware.Sensor;
import android.util.Log;

import java.util.List;

import QueryManager.queries.enums.QueryTiming;
import QueryManager.queries.interfaces.ThreadBased;
import QueryManager.queries.interfaces.TimeBased;
import QueryManager.sensors.SensorHandler;

public class Periodic<T> extends Snapshot<T> implements ThreadBased, TimeBased {

    private long _for;
    private long _every;
    private Boolean isContinues = true;
    private Boolean isInfinite = false;
    private Thread thread;
    private Boolean queryDone = false;

    public Periodic(SensorHandler sensorHandler, List<Sensor> sensors){
        super(sensorHandler,sensors);
    }

    @Override
    public void setLifeTime(int until, QueryTiming timeType) {
        this._for = until * timeType.getValue();
        isInfinite = false;
    }

    @Override
    public void setPeriodicity(int every, QueryTiming timeType){
        this._every = every * timeType.getValue();
        isContinues = false;
    }

    public void launch() throws Exception {
        super.execute();
    }


    @Override
    public int callback(Sensor sensor,T values){
        if(queryDone) {
            return SensorHandler.SENSOR_STOP;
        }
        queryResult.getResult().put(sensor.getName().replace(" ","_").toUpperCase(),
                values);
        if(queryResult.getResult().size() == sensors.size()) {
            onResult(queryResult);
            queryResult.getResult().clear();
        }
        return (isContinues) ? SensorHandler.SENSOR_PROCEED : SensorHandler.SENSOR_STOP;
    }

    @Override
    public void execute() throws Exception {
        isInfinite = _for == 0;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run(){
        while (_for > 0x00 || isInfinite) {
            try {
                if(!isInfinite){
                    if(!isContinues){
                        Thread.sleep(_every);
                        this.launch();
                        _for -= _every;
                    }else{
                        this.launch();
                        Thread.sleep(_for);
                        _for = 0;
                    }
                }else{
                    if(!isContinues){
                        Thread.sleep(_every);
                        this.launch();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        queryDone = true;
        Log.d("WEARABLE_D","PERIOD QUERY JUST FINISHED");
    }

    @Override
    public Thread getThread(){
        return thread;
    }

    @Override
    public Boolean isRunning(){
        return thread != null && thread.isAlive();
    }

    @Override
    public void interrupt(){
        thread.interrupt();
    }

    @Override
    public int getType(){
        return Query.PERIODIC;
    }
    @Override
    public String getName(){
        return "Periodic";
    }

}
