package QueryManager.queries.interfaces;

public interface QueryEvent<T> {
    Boolean conditionTrue(T values);
}
