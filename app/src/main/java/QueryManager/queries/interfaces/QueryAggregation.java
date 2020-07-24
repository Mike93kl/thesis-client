package QueryManager.queries.interfaces;

public interface QueryAggregation {
    <N extends Number> void calculate(N value);
}
