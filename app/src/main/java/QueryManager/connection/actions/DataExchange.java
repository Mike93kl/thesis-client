package QueryManager.connection.actions;

import java.io.IOException;

public interface DataExchange {
    void onSendSuccess(byte[] data);
    void onSendError(IOException e);
    void onReceivedData(byte[] data, int numBytes);
    void onReceivedDataError(IOException e);
    void onSocketError(Exception e);
    void onConnected() throws Exception;
}
