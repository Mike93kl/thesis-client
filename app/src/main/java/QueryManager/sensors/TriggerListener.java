package QueryManager.sensors;

import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;

import QueryManager.queries.Query;

class TriggerListener extends TriggerEventListener {


    private Query query;

    public TriggerListener(Query query){
        this.query = query;
    }

    @Override
    public void onTrigger(TriggerEvent triggerEvent) {
        query.callback(triggerEvent.sensor,triggerEvent.values);
    }
}
