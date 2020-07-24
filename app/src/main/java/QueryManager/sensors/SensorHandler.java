package QueryManager.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import QueryManager.queries.Query;
@SuppressWarnings(value = "unchecked")
public class SensorHandler {

    public static final int SENSOR_PROCEED = 0x01;
    public static final int SENSOR_STOP    = 0x02;

    private SensorManager sensorManager;
    private Map<Integer, Sensor> idSensorMap;
    private Map<String,Sensor> stringSensorMap;
    private Map<String,Integer> availableSensorsMap;
    private Map<Sensor,SensorListener> listenerMap;
    private int maxSearchValue;

    public SensorHandler(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        idSensorMap = new HashMap<>();
        stringSensorMap = new HashMap<>();
        availableSensorsMap = new HashMap<>();
        listenerMap = new HashMap<>();
        int sv = 1; // search value
        for(Sensor sensor: sensorList) {
            String sensorName = sensor.getName().replace(" ","_").toUpperCase();
            availableSensorsMap.put(sensorName,sv);
            idSensorMap.put(sv,sensor);
            stringSensorMap.put(sensorName,sensor);
            Log.d("WEARABLE_D","Id: " + sv + " Name: " + sensor.getName());
            sv <<= 1;
        }
        maxSearchValue = sv;
    }

    public Map<Integer,Sensor> getIdSensorMap(){
        return idSensorMap;
    }

    public Map<String,Integer> getAvailableSensorsMap(){
        return availableSensorsMap;
    }

    public void startListener(Sensor sensor,Query query) {
        if( sensor.getReportingMode() == Sensor.REPORTING_MODE_ONE_SHOT ){
            sensorManager.requestTriggerSensor(new TriggerListener(query), sensor);
            return;
        }


        if(listenerMap.get(sensor) != null ) {
            listenerMap.get(sensor).addQuery(query);
            return;
        }
        SensorListener listener = getNewSensorListener(sensor,query);
        if(sensor.getReportingMode() == Sensor.REPORTING_MODE_SPECIAL_TRIGGER){
            sensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_FASTEST,10);
        }else{
            sensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_FASTEST);
        }

        listenerMap.put(sensor,listener);
    }

    private SensorListener getNewSensorListener(final Sensor sensor, Query query){
        return new SensorListener(query) {
            @Override
            public void terminate() {
                sensorManager.unregisterListener(this);
                listenerMap.remove(sensor);
            }
        };
    }

    private List<Sensor> getSensorListFromValue(int value){
        List<Sensor> sensors = new ArrayList<>();
        int searchValue = maxSearchValue;
        while (searchValue > 0x00) {
            if((value & searchValue) == searchValue){
                sensors.add(idSensorMap.get(searchValue));
            }
            searchValue >>= 1;
        }
        return sensors;
    }

    public Sensor getSensorFromName(String name){
        return stringSensorMap.get(name);
    }

}
