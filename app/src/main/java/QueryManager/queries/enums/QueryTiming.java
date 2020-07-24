package QueryManager.queries.enums;

public enum QueryTiming {
    M("Minutes",60000),MS("MilliSeconds",1),H("Hours",60000*60),
    S("Seconds",1000);

    private String name;
    private int value;

    QueryTiming(String name,int value){
        this.name = name;
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
