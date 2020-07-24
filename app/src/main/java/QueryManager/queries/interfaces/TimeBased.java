package QueryManager.queries.interfaces;

import QueryManager.queries.enums.QueryTiming;

public interface TimeBased {
    void setLifeTime(int until, QueryTiming timeType);
    void setPeriodicity(int every,QueryTiming timeType);
}
