package QueryManager.queries;

import android.hardware.Sensor;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import QueryManager.queries.enums.QueryAggregationType;
import QueryManager.queries.enums.QueryTiming;
import QueryManager.queries.interfaces.MeasurementBased;
import QueryManager.queries.interfaces.QueryAggregation;
import QueryManager.queries.interfaces.ThreadBased;
import QueryManager.queries.interfaces.TimeBased;
import QueryManager.response.QueryResult;
import QueryManager.sensors.SensorHandler;

public class Aggregation<T> extends Query<T> implements TimeBased, ThreadBased, MeasurementBased {

    private static final int TIME_BASED   = 0X01;
    private static final int MEASUREMENTS = 0X002;

    private long _for = 0;
    private int _measurements = 0;
    private HashMap<Sensor,QueryAggregationImpl> aggregationMap;
    private Boolean queryDone        = false;
    private Thread thread;
    private Boolean sensorsCollected = false;


    public Aggregation(SensorHandler sensorHandler, List<Sensor> sensors) {
        super(sensorHandler, sensors);
        aggregationMap = new HashMap<>();
    }


    public void addAggregation(Sensor sensor, QueryAggregationType type ) {
        aggregationMap.put(sensor, getAggregationType(type));
    }

    private void completeResultAndSend() {
        for (Map.Entry kv : aggregationMap.entrySet()) {
            Float[] res = { (Float) ((QueryAggregationImpl) kv.getValue()).getValue() };
            queryResult.getResult().put(
                    ((Sensor)kv.getKey()).getName().replace(" ","_").toUpperCase(),
                    (T) res
            );
        }
        if( sensors != null && sensors.size() > 0) {
            Log.d("WEARABLE_D", "AGGREGATION FINISHED, SNAPSHOTTING OTHER SENSORS");
            final Aggregation _this = this;
            Snapshot<T> snapshot = new Snapshot<T>(sensorHandler,sensors) {
                @Override
                public void onResult(QueryResult result) {
                      _this.queryResult.getResult().putAll(result.getResult());
                      _this.onResult(_this.queryResult);

                }
            };
            try{
                snapshot.execute();
            }catch (Exception e){
                e.printStackTrace();
                onResult(queryResult);
            }
        }else{
            onResult(queryResult);
        }
    }

    private QueryAggregationImpl getAggregationType(QueryAggregationType aggregationType){
        QueryAggregationImpl aggregation = null;
        switch (aggregationType){
            case MAX:
                aggregation = new Max();
                break;
            case MIN:
                aggregation = new Min();
                break;
            case SUM:
                aggregation = new Sum();
                break;
            case AVG:
                aggregation = new Avg();
                break;
        }
        return aggregation;
    }


    @Override
    public int callback(Sensor sensor, T values) {
        if(queryDone) {
            if(!sensorsCollected) {
                completeResultAndSend();
                sensorsCollected = true;
            }
            return SensorHandler.SENSOR_STOP;
        }
        Number v;
        if( values.getClass().isArray() ) {
            v = (Number) Array.get(values,0);
        }else{
            v = (Number) values;
        }
        Log.d("WEARABLE_D", "GOT VALUE: V: " +  v);
        aggregationMap.get(sensor).calculate(v);

        if(type == MEASUREMENTS) {
            _measurements--;
            if(_measurements == 0) {
                queryDone = true;
                completeResultAndSend();
                sensorsCollected = true;
                return SensorHandler.SENSOR_STOP;
            }
        }

        return SensorHandler.SENSOR_PROCEED;
    }

    @Override
    public int getType() {
        return Query.AGGREGATION;
    }

    @Override
    public void execute() throws Exception {
        for (Map.Entry kv : aggregationMap.entrySet()) {
            sensorHandler.startListener((Sensor) kv.getKey(),this);
        }

        if(type == TIME_BASED){
            this.thread = new Thread(this);
            this.thread.start();
        }

    }

    @Override
    public void execute(List<Sensor> sensors) throws Exception {
        this.sensors = sensors;
        this.execute();
    }

    @Override
    public void setMeasurementNumber(int measurementNumber) {
        this._measurements = measurementNumber;
        type = MEASUREMENTS;
    }

    @Override
    public Thread getThread() {
        return null;
    }

    @Override
    public Boolean isRunning() {
        return null;
    }

    @Override
    public void interrupt() {
        _for = 0;
        _measurements = 0;
    }

    @Override
    public void setLifeTime(int until, QueryTiming timeType) {
        this._for = until * timeType.getValue();
        this.type = TIME_BASED;
    }

    @Override
    public void setPeriodicity(int every, QueryTiming timeType) {
    }

    @Override
    public void run() {
        try {
            Thread.sleep(_for);
            queryDone = true;
            completeResultAndSend();
            sensorsCollected = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName(){
        return "Aggregation";
    }


    /**
     * Inner classes
     *
     */
    private abstract class QueryAggregationImpl implements QueryAggregation {
        Object value;
        public QueryAggregationImpl(){this.value = 0;}
        Object getValue(){return value;}
        public  abstract QueryAggregationType getType();
    }

    private class Max extends QueryAggregationImpl{
        public Max(){ super(); this.value = Float.MIN_VALUE; }

        @Override
        public QueryAggregationType getType() {
            return QueryAggregationType.MAX;
        }

        @Override
        public <N extends Number> void calculate(N value) {
            if( value.floatValue() > ((N) this.value).floatValue() ) {
                this.value = value;
            }
        }
    }

    private class Min extends QueryAggregationImpl{
        public Min(){ super(); this.value = Float.MAX_VALUE; }
        @Override
        public <N extends Number> void calculate(N value) {
            if( value.floatValue() < ((N) this.value).floatValue() ) {
                this.value = value;
            }
        }
        @Override
        public QueryAggregationType getType() {
            return QueryAggregationType.MIN;
        }
    }

    private class Sum extends QueryAggregationImpl{
        public Sum(){ super(); }
        @Override
        public <N extends Number> void calculate(N value) {
            this.value =  ((N) this.value).floatValue() + value.floatValue();
        }
        @Override
        public QueryAggregationType getType() {
            return QueryAggregationType.SUM;
        }
    }

    private class Avg extends QueryAggregationImpl{
        private int counter;
        public Avg(){ super(); counter = 0;}
        @Override
        public <N extends Number> void calculate(N value) {
            this.value =  ((N) this.value).floatValue() + value.floatValue();
            counter++;
        }
        @Override
        public Object getValue(){
            return (Float) value / counter;
        }
        @Override
        public QueryAggregationType getType() {
            return QueryAggregationType.AVG;
        }
    }
}
