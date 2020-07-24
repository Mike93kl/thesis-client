package QueryManager.connection.actions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Connection {
    void connect() throws IOException;
    void disconnect() throws IOException;
    void accept() throws IOException;
    void send(byte[] data) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InterruptedException;
    void onInitializeSocketError(Exception e);
    Boolean isConnected();
    void onConnected() throws Exception;
}
