package QueryManager.queries;

import android.hardware.Sensor;

import java.util.List;

import QueryManager.sensors.SensorHandler;

public class Snapshot<T> extends Query<T> {

    public Snapshot(SensorHandler sensorHandler, List<Sensor> sensors){
        super(sensorHandler,sensors);
    }

    @Override
    public int getType(){
        return Query.SNAPSHOT;
    }

    @Override
    public void execute() throws Exception {
        for(Sensor sensor: sensors){
            this.sensorHandler.startListener(sensor,this);
        }
    }

    @Override
    public void execute(List<Sensor> sensors) throws Exception {
        this.sensors = sensors;
        execute();
    }

    @Override
    public int callback(Sensor sensor, T values){
        queryResult.getResult().put(sensor.getName().replace(" ","_").toUpperCase(),
                values);
        if( queryResult.getResult().size() == sensors.size() ){
            onResult(queryResult);
        }
        return SensorHandler.SENSOR_STOP;
    }

    @Override
    public String getName(){
        return "Snapshot";
    }

}
