package QueryManager.queries.interfaces;

public interface Executable<T> {
    void execute() throws Exception;
    void execute(T t) throws Exception;
}
