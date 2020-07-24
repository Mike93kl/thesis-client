package QueryManager.queries.interfaces;

public interface ThreadBased extends Runnable {
    Thread getThread();
    Boolean isRunning();
    void interrupt();
}
