package QueryManager.connection;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import QueryManager.connection.actions.Connection;
import QueryManager.connection.actions.DataExchangeImpl;
import QueryManager.connection.data.AcceptData;
import QueryManager.connection.data.SendData;

public  class BluetoothClient extends DataExchangeImpl implements Connection {
    private UUID uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
    private BluetoothAdapter _adapter;
    private BluetoothSocket _socket;
    private BluetoothDevice _pDevice;
    private Thread acceptThread;

    public static String getPairedDeviceName(){
        Boolean isEnabled = BluetoothAdapter.getDefaultAdapter().isEnabled();
        try{
            if(!isEnabled) {
                BluetoothAdapter.getDefaultAdapter().enable();
            }
            String bluetoothName = BluetoothAdapter.getDefaultAdapter().getBondedDevices().iterator()
                    .next().getName();
            return bluetoothName;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            if(!isEnabled) {
                BluetoothAdapter.getDefaultAdapter().disable();
            }
        }

    }

    public BluetoothClient() throws Exception {
        _adapter = BluetoothAdapter.getDefaultAdapter();
        if( _adapter == null ) throw new Exception("Device Does not support Bluetooth");
        if( !_adapter.isEnabled() )
            _adapter.enable();
    }

    public static Boolean isBluetoothEnabled(){
        return BluetoothAdapter.getDefaultAdapter().isEnabled();
    }


    @Override
    public synchronized void connect(){
        try{
            if( _pDevice == null ){
                if(_adapter.getBondedDevices().isEmpty()){
                    throw new IOException("Pair your device first and then use this library");
                }
                _pDevice = _adapter.getBondedDevices().iterator().next();
            }

            _socket = _pDevice.createRfcommSocketToServiceRecord(uuid);
            _socket.connect();
            notifyAll();
            onConnected();
            setAcceptDataThread();
        }catch (Exception e){
            onInitializeSocketError(e);
        }
    }

    @Override
    public void accept(){
        if(acceptThread.isAlive()) return;
        acceptThread.start();
    }

    @Override
    public synchronized void send(byte[] data) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InterruptedException {
        if(_socket == null || !_socket.isConnected()){
            wait();
        }
        SendData<BluetoothSocket> send = new SendData<BluetoothSocket>(_socket,data) {
            @Override
            public void onSuccess(byte[] data) {
                dataExchange.onSendSuccess(data);
            }

            @Override
            public void onError(IOException e) {
                dataExchange.onSendError(e);
            }
        };
        new Thread(send).start();
    }

    @Override
    public void onInitializeSocketError(Exception e) {
        dataExchange.onSocketError(e);
    }

    @Override
    public Boolean isConnected() {
        return _socket != null && _socket.isConnected();
    }

    @Override
    public void onConnected() throws Exception {
        dataExchange.onConnected();
    }

    @Override
    public void disconnect(){
        this.acceptThread.interrupt();
    }

    private void setAcceptDataThread() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(_socket == null) throw new IOException("Socket was null");
        AcceptData<BluetoothSocket> acceptData = new AcceptData<BluetoothSocket>(_socket) {

            @Override
            public void onDataReceived(byte[] data, int numBytes) {
                dataExchange.onReceivedData(data,numBytes);
            }

            @Override
            public void onErrorReadingData(IOException e) {
                dataExchange.onReceivedDataError(e);
            }
        };
        this.acceptThread = new Thread(acceptData);
    }




}
