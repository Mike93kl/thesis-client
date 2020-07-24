package QueryManager.queries;

import android.hardware.Sensor;

import java.util.List;

import QueryManager.queries.interfaces.Executable;
import QueryManager.queries.interfaces.ResultCallback;
import QueryManager.response.QueryResult;
import QueryManager.sensors.SensorHandler;

public abstract class Query<T> implements Executable<List<Sensor>> {
    public static final int SNAPSHOT       = 0x0000;
    public static final int PERIODIC       = 0x0001;
    public static final int EVENT_BASED    = 0x0002;
    public static final int AGGREGATION    = 0x0003;

    protected int type;
    protected List<Sensor> sensors;
    protected SensorHandler sensorHandler;
    protected QueryResult<String,T> queryResult;
    protected ResultCallback resultCallback;

    public Query(SensorHandler sensorHandler,List<Sensor> sensors){
        this.sensorHandler = sensorHandler;
        this.sensors = sensors;
        this.queryResult = new QueryResult<>();
    }

    public abstract int callback(Sensor sensor,T values);
    public abstract int getType();
    public abstract String getName();
    public QueryResult<String,T> getQueryResult(){return queryResult;}

    protected void onResult(QueryResult result)  {
        this.resultCallback.onResult(result);
    }
    protected void onError(Exception e) {
        this.resultCallback.onError(e);
    }
    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public void setResultCallback(ResultCallback resultCallback) {
        this.resultCallback = resultCallback;
    }
}
