package QueryManager.queryprocessor;

import android.hardware.Sensor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import QueryManager.QueryManager;
import QueryManager.queries.Aggregation;
import QueryManager.queries.EventBased;
import QueryManager.queries.Periodic;
import QueryManager.queries.Query;
import QueryManager.queries.Snapshot;
import QueryManager.queries.enums.QueryAggregationType;
import QueryManager.queries.enums.QueryEventType;
import QueryManager.queries.enums.QueryTiming;

public class QuerySpecifier {
    /** time char **/
    private static HashMap<String, QueryTiming> _timeSpecifier ;
    /** event operators **/
    private static HashMap<String, QueryEventType> _eventOperators;
    /** Aggregation Types */
    private static HashMap<String, QueryAggregationType> _aggregationTypes;
    static {
        _timeSpecifier = new HashMap<>();
        _timeSpecifier.put("ms",QueryTiming.MS);
        _timeSpecifier = new HashMap<>();
        _timeSpecifier.put("s",QueryTiming.S);
        _timeSpecifier.put("m",QueryTiming.M);
        _timeSpecifier.put("h",QueryTiming.H);
        _eventOperators = new HashMap<>();
        _eventOperators.put("=",QueryEventType.EQUAL);
        _eventOperators.put(">",QueryEventType.GREATER);
        _eventOperators.put(">=",QueryEventType.GREATER_OR_EQUAL);
        _eventOperators.put("<", QueryEventType.LOWER);
        _eventOperators.put("<=",QueryEventType.LOWER_OR_EQUAL);
        _eventOperators.put("!=",QueryEventType.NOT_EQUAL);
        _aggregationTypes = new HashMap<>();
        _aggregationTypes.put("MIN", QueryAggregationType.MIN);
        _aggregationTypes.put("MAX",QueryAggregationType.MAX);
        _aggregationTypes.put("SUM",QueryAggregationType.SUM);
        _aggregationTypes.put("AVG",QueryAggregationType.AVG);
    }
    private List<String> _selectedSensors = new ArrayList<>();
    private Boolean _isStarQuery = false;
    private Boolean _isPeriodic  = false;
    private Boolean _isEventBased = false;
    private Boolean _isAggregation = false;

    // time Based vars
    int _lifetime = 0; QueryTiming _lifetimeTiming;
    int _periodicity = 0;QueryTiming _periodicityTiming;

    // measurement based;
    int _measurements = 0;

    // event based;
    private List<EventQuerySpecifier> _eventSpecifiers;

    // aggregation
    private List<AggregationQuerySpecifier> _aggregationSpecifier;
    /** Constructor */
    QuerySpecifier(){
    }

    /** Getters and setters */
    public void setSelectedSensors(List<String> sensors) { this._selectedSensors = sensors;}
    public List<String> getSelectedSensors(){return this._selectedSensors;}

    public Boolean isStarQuery(){return this._isStarQuery;}
    public void setStartQUery(Boolean b){_isStarQuery = b;}
    public Boolean isPeriodic(){return this._isPeriodic;}
    public void setPeriodic(Boolean b){_isPeriodic = b;}
    public Boolean isEventBased(){return this._isEventBased;}
    public void setEventBased(Boolean b){_isEventBased = b;}
    public Boolean isAggregation(){return this._isAggregation;}
    public void setAggregation(Boolean b){_isAggregation = b;}

    public void setLifeTime(int l){this._lifetime = l;}
    public void setLifetimeTiming(String t) { _lifetimeTiming = _timeSpecifier.get(t); }

    public void setPeriodicity(int p){this._periodicity = p;}
    public void setPeriodicityTiming(String t) { _periodicityTiming = _timeSpecifier.get(t); }

    public void setMeasurementNumber(int m){this._measurements = m;}

    public <C extends Comparable<? super C>> void addEventSpecifier(String sensor, String operator, C value) {
        if(_eventSpecifiers == null ){
            _eventSpecifiers = new ArrayList<>();
        }
        EventQuerySpecifier specifier = new EventQuerySpecifier();
        specifier.setSensor(sensor);
        specifier.setType(_eventOperators.get(operator));
        specifier.setValue(value);
        _eventSpecifiers.add(specifier);
    }

    public void addAggregationSpecifier(String sensor, String aggregationName){
        if(_aggregationSpecifier == null ){
            _aggregationSpecifier = new ArrayList<>();
        }
        AggregationQuerySpecifier specifier = new AggregationQuerySpecifier();
        specifier.setSensor(sensor);
        specifier.setType(_aggregationTypes.get(aggregationName));
        _aggregationSpecifier.add(specifier);
    }

    public Query specify()throws Exception{
            Query query;
           if(_isPeriodic) query = getPeriodicQuery();
           else if(_isEventBased) query = getEventBasedQuery();
           else if(_isAggregation) query = getAggregationQuery();
           else query = getSnapshotQuery();
           query.setSensors(getSensors());
           return query;
    }

    private Periodic getPeriodicQuery(){
        Periodic periodic = new Periodic(QueryManager.getInstance().getSensorHandler(),null);
        if( _lifetime != 0) {
            periodic.setLifeTime(_lifetime,_lifetimeTiming);
        }
        if( _periodicity != 0) {
            periodic.setPeriodicity(_periodicity,_periodicityTiming);
        }
        return periodic;
    }
    private EventBased getEventBasedQuery() throws Exception{
        EventBased eventBased =  new EventBased(QueryManager.getInstance().getSensorHandler(),null);
        if( _lifetime == 0 ) {
            eventBased.setMeasurementNumber(_measurements);
        }else{
            eventBased.setLifeTime(_lifetime,_lifetimeTiming);
            if(_periodicity != 0) {
                eventBased.setPeriodicity(_periodicity,_periodicityTiming);
            }
        }
        for(EventQuerySpecifier specifier: _eventSpecifiers) {
            eventBased.addEvent(
                    QueryManager.getInstance().getSensorHandler().getSensorFromName(specifier.getSensor()),
                    specifier.getType(),
                    specifier.getValue()
            );
        }
        return eventBased;
    }
    private Aggregation getAggregationQuery(){
        Aggregation aggregation = new Aggregation(QueryManager.getInstance().getSensorHandler(), null);
        if( _lifetime != 0 ) {
            aggregation.setLifeTime(_lifetime,_lifetimeTiming);
        }else{
            aggregation.setMeasurementNumber(_measurements);
        }
        for(AggregationQuerySpecifier specifier: _aggregationSpecifier) {
            aggregation.addAggregation(
                    QueryManager.getInstance().getSensorHandler().getSensorFromName(specifier.getSensor()),
                    specifier.getType()
            );
        }
        return aggregation;
    }
    private Snapshot getSnapshotQuery(){
        Snapshot snapshot = new Snapshot(QueryManager.getInstance().getSensorHandler(),null);
        return snapshot;
    }


    private List<Sensor> getSensors(){
        List<Sensor> sensors = new ArrayList<>();
        for(String sensor : _selectedSensors) {
            sensors.add(QueryManager.getInstance().getSensorHandler().getSensorFromName(sensor));
        }
        return sensors;
    }

    private static class AggregationQuerySpecifier {
        private String sensor;
        private QueryAggregationType type;

        public String getSensor() {
            return sensor;
        }

        public void setSensor(String sensor) {
            this.sensor = sensor;
        }

        public QueryAggregationType getType() {
            return type;
        }

        public void setType(QueryAggregationType type) {
            this.type = type;
        }
    }

    private static class EventQuerySpecifier {
        private String sensor;
        private QueryEventType type;
        private Comparable value;

        public String getSensor() {
            return sensor;
        }

        public void setSensor(String sensor) {
            this.sensor = sensor;
        }

        public QueryEventType getType() {
            return type;
        }

        public void setType(QueryEventType type) {
            this.type = type;
        }

        public Comparable getValue() {
            return value;
        }

        public void setValue(Comparable value) {
            this.value = value;
        }
    }
}
