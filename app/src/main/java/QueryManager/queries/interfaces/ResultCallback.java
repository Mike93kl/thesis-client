package QueryManager.queries.interfaces;

import QueryManager.response.QueryResult;

public interface ResultCallback {
    void onResult(QueryResult result);
    void onError(Exception e) ;
}
