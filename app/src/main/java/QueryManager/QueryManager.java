package QueryManager;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import QueryManager.connection.ConnectionManager;
import QueryManager.connection.ConnectionType;
import QueryManager.connection.actions.DataExchange;
import QueryManager.connection.data.ConvertData;
import QueryManager.queries.Query;
import QueryManager.queries.interfaces.ResultCallback;
import QueryManager.queryprocessor.QueryProcessor;
import QueryManager.response.QueryResult;
import QueryManager.response.ResponseState;
import QueryManager.sensors.SensorHandler;

public class QueryManager {

    private static String TAG = "WEARABLE_QUERY_MANAGER_DEBUG";
    public static void log(String msg) {
        Log.d(TAG,msg);
    }
    private static QueryManager _instance = new QueryManager();
    private Boolean debug = false;
    private ConnectionManager connection;
    //private Context context;
    private SensorHandler sensorHandler;
    private QueryProcessor queryProcessor = QueryProcessor.getInstance();
    private ResultCallback resultCallback = new ResultCallback() {
        @Override
        public void onResult(QueryResult result)  {
           try{
               List<byte[]> response = new ArrayList<>();
               response.add( ConvertData.toByteArray(ResponseState.QUERY_RESULT) );
               response.add(ConvertData.toByteArray(result));
               send(ConvertData.toByteArray(response));
           }catch (Exception e){
               e.printStackTrace();
               if(debug) {
                   Log.d(TAG, "Failed to send query results");
               }
           }
        }

        @Override
        public void onError(Exception e)  {
           try{
               List<byte[]> response = new ArrayList<>();
               response.add( ConvertData.toByteArray(ResponseState.ERROR) );
               response.add(ConvertData.toByteArray(e.getMessage()));
               send(ConvertData.toByteArray(response));
           }catch (Exception ex){
               ex.printStackTrace();
               if(debug) {
                   Log.d(TAG, "Failed to send that there was an error retrieving query results");
               }
           }
        }
    };
    private DataExchange dataExchange = new DataExchange() {
        @Override
        public void onSendSuccess(byte[] data) {
            if(debug) {
                Log.d(TAG, "Sending data was successful");
            }
        }

        @Override
        public void onSendError(IOException e) {
            e.printStackTrace();
            if(debug)
                Log.d(TAG, "Error sending data. ( Printed stack trace )");
        }

        @Override
        public void onReceivedData(byte[] data, int numBytes) {
            try{
                String strQuery = (String) ConvertData.toObject(data);
                if(debug)
                    Log.d(TAG, "Query Received From server: " + strQuery);
                Query query =  queryProcessor.createQuery(strQuery);
                if(debug) {
                    Log.d(TAG, "Received query is type: " + query.getName());
                }
                query.setResultCallback(resultCallback);
                query.execute();
                if(debug) {
                    Log.d(TAG, "Query Executed, Expecting Results");
                }
            }catch (Exception e){
                e.printStackTrace();
                Log.d(TAG,"Attempt to Print Received Query failed");
            }

        }

        @Override
        public void onReceivedDataError(IOException e) {
            e.printStackTrace();
            if(debug)
                Log.d(TAG,"Received data error. (Printed stack trace)");
        }

        @Override
        public void onSocketError(Exception e) {
            e.printStackTrace();
            if(debug)
                Log.d(TAG,"Socket error. (Printed stack trace)");
        }

        @Override
        public void onConnected() throws Exception {
            if(debug)
                Log.d(TAG,"Connection Successful, sending available sensors");
            Map<String, Integer> availableSensorsMap = sensorHandler.getAvailableSensorsMap();
            List<byte[]> response = new ArrayList<>();
            response.add( ConvertData.toByteArray(ResponseState.SENSOR_MAP) );
            response.add(ConvertData.toByteArray(availableSensorsMap));
            send(ConvertData.toByteArray(response));
        }
    };

    private QueryManager() {}
    public static QueryManager getInstance(){return _instance;}

    public void init(Context context, ConnectionType connectionType){
        sensorHandler = new SensorHandler(context);
        setConnectionType(context,connectionType);
    }

    public void send(byte[] data) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InterruptedException {
        this.connection.send(data);

    }

    private void setConnectionType(Context context,ConnectionType type) {
        try{
            switch (type){
                case NSD:
                    this.connection = ConnectionManager.nsd(context);
                    break;
                case BLUETOOTH:
                    this.connection = ConnectionManager.bluetooth();
                    break;
                case AUTO:
                    this.connection = ConnectionManager.auto(context);
                    break;
                default:
                    break;
            }
            this.connection.setDataExchange(dataExchange);
        }catch (Exception e){
            throw new RuntimeException("Could not initiate the connection type. System broke with error: " +
             e.getMessage());
        }

    }

    public void connect() {
        if(this.connection == null) throw new RuntimeException("Run init(Context,ConnectionType) " +
                "before running connect()");
        try{
            this.connection.connect();
            this.connection.accept();
        }catch (Exception e){
            throw new RuntimeException("Could not execute connect() and accept() on connection object, system broke with message: " + e.getMessage());
        }
    }

    public SensorHandler getSensorHandler() {return this.sensorHandler;}

    public void setDebug(Boolean b){this.debug = b;}

    public void setDataExchange(DataExchange dataExchange){
        this.connection.setDataExchange(dataExchange);
    }


}
