package QueryManager.connection;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import QueryManager.connection.actions.Connection;
import QueryManager.connection.actions.DataExchange;
import QueryManager.connection.actions.DataExchangeImpl;

@SuppressWarnings(value = "unchecked")
public class ConnectionManager<T extends DataExchangeImpl & Connection> implements Connection {

    private T connection;
    private DataExchange dataExchange;

    public static ConnectionManager<BluetoothClient> bluetooth() throws Exception {
        return new ConnectionManager<>();
    }

    public static ConnectionManager<NsdClient> nsd(Context context) throws IOException {
        return new ConnectionManager<>(context);
    }

    public static ConnectionManager auto(Context context) throws Exception {
        if(!BluetoothClient.isBluetoothEnabled()){
            return new ConnectionManager<NsdClient>(context);
        }
        return new ConnectionManager<BluetoothClient>();
    }

   private ConnectionManager() throws Exception {
        this.connection = (T) new BluetoothClient();
   }

   private ConnectionManager(Context context) throws IOException {
        this.connection = (T) new NsdClient(context);
   }

    public DataExchange getConnectionDataExchange(){
        return this.connection.getDataExchange();
    }
    public void setDataExchange(DataExchange dataExchange){
        this.connection.setDataExchange(dataExchange);
    }




    @Override
    public void connect() throws IOException {
        this.connection.connect();
    }

    @Override
    public void disconnect() throws IOException {
        this.connection.disconnect();
    }

    @Override
    public void accept() throws IOException {
        this.connection.accept();
    }

    @Override
    public void send(byte[] data) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InterruptedException {
        this.connection.send(data);
    }


    @Override
    public Boolean isConnected() {
        return this.connection.isConnected();
    }

    @Override
    public void onConnected() throws Exception {
        this.connection.onConnected();
    }
    @Override
    public void onInitializeSocketError(Exception e) {
        e.printStackTrace();
        Log.d("WEARABLE","SOCKET INIT ERROR");
    }

}
