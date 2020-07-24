package QueryManager.connection;

public enum ConnectionType {
    BLUETOOTH("Bluetooth")
    ,NSD("Network Discovery Service")
    ,AUTO("Auto connection");

    private String name;

    ConnectionType(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
