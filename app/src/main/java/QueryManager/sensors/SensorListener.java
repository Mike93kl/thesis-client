package QueryManager.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.util.ArrayList;
import java.util.List;

import QueryManager.queries.Query;

abstract class SensorListener<T> implements SensorEventListener {
    protected List<Query> queries;

    public SensorListener(Query query){
        queries = new ArrayList<>();
        queries.add(query);
    }

    public void addQuery(Query query){
        queries.add(query);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(queries.get(0).callback(sensorEvent.sensor, sensorEvent.values) == SensorHandler.SENSOR_STOP){
            queries.remove(0);
            if(queries.isEmpty()){
                terminate();
            }
        }
    }
    public abstract void terminate();
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}
