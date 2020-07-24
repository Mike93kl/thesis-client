package QueryManager.connection;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import QueryManager.QueryManager;
import QueryManager.connection.actions.Connection;
import QueryManager.connection.actions.DataExchangeImpl;
import QueryManager.connection.data.AcceptData;
import QueryManager.connection.data.SendData;

public class NsdClient extends DataExchangeImpl implements Connection {
    private static final String SERVICE_TYPE = "_dqm._tcp";
    private NsdManager nsdManager;
    private NsdManager.DiscoveryListener discoveryListener;
    private NsdManager.ResolveListener resolveListener;
    private Context context;
    private NsdServiceInfo serverServiceInfo;
    private Socket socket;
    private Thread acceptThread;

    public NsdClient(Context context) {
        this.context = context;
        nsdManager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);
        initializeDiscoveryListener();
        initializeResolveListener();
    }

    private void initializeDiscoveryListener(){
        discoveryListener = new NsdManager.DiscoveryListener() {
            @Override
            public void onStartDiscoveryFailed(String s, int i) {
                QueryManager.log("START DISCOVERY FAILED");
            }

            @Override
            public void onStopDiscoveryFailed(String s, int i) {
                QueryManager.log("STOP DISCOVERY FAILED");
            }

            @Override
            public void onDiscoveryStarted(String s) {
                QueryManager.log("START DISCOVERY SUCCESSFULLY");
            }

            @Override
            public void onDiscoveryStopped(String s) {
                QueryManager.log("DISCOVERY STOPPED");
            }

            @Override
            public void onServiceFound(NsdServiceInfo nsdServiceInfo) {
                QueryManager.log("SERVICE FOUND: " + nsdServiceInfo.getServiceName());
                if( nsdServiceInfo.getServiceName().contains("DQMServer") ) {
                    nsdManager.resolveService(nsdServiceInfo, resolveListener);
                }

            }

            @Override
            public void onServiceLost(NsdServiceInfo nsdServiceInfo) {
                QueryManager.log("SERVICE LOST: " + nsdServiceInfo.getServiceName());
            }
        };
    }

    private void initializeResolveListener(){
        resolveListener = new NsdManager.ResolveListener() {
            @Override
            public void onResolveFailed(NsdServiceInfo nsdServiceInfo, int i) {
                dataExchange.onSocketError(new Exception("Could not resolve client. Service failed to connect to :" +
                        nsdServiceInfo.getServiceName() + " with code: " +i));
            }

            @Override
            public void onServiceResolved(NsdServiceInfo nsdServiceInfo) {
                serverServiceInfo = nsdServiceInfo;
                connectSocket();
            }
        };
    }

    private synchronized void connectSocket(){
        try{
            socket = new Socket(serverServiceInfo.getHost(),serverServiceInfo.getPort());
            notifyAll();
            onConnected();
        }catch (Exception e){
            e.printStackTrace();
            dataExchange.onSocketError(e);
        }

    }

    @Override
    public void connect() throws IOException {
        nsdManager.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener);
    }

    @Override
    public void disconnect() throws IOException {
        if(socket != null && socket.isConnected()){
            socket.close();
        }
        if(acceptThread != null && acceptThread.isAlive()){
            acceptThread.interrupt();
        }
    }

    private void setAcceptDataThread() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(socket == null) throw new IOException("Socket was null");
        AcceptData<Socket> acceptData = new AcceptData<Socket>(socket) {

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

    @Override
    public synchronized void accept() throws IOException {
        try{
            while (socket == null ) {
                wait();
            }
            setAcceptDataThread();
        }catch (Exception e){
            e.printStackTrace();
            onInitializeSocketError(e);
        }
    }

    @Override
    public synchronized void send(byte[] data) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InterruptedException {
        if(socket == null || !socket.isConnected()){
            wait();
        }
        SendData<Socket> send = new SendData<Socket>(socket,data) {
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
        return socket.isConnected();
    }

    @Override
    public void onConnected() throws Exception {
        dataExchange.onConnected();
    }
}
