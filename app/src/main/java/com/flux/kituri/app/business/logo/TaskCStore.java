package com.flux.kituri.app.business.logo;

import com.flux.kituri.app.data.MeizhiData;
import com.flux.kituri.app.flux.Action;
import com.flux.kituri.app.flux.BaseStoreChangeEvent;
import com.flux.kituri.app.flux.Store;

public class TaskCStore extends Store {

    private MeizhiData data;
    public TaskCStore() {
        super();
    }

    public MeizhiData getData(){ return data;}

    @Override
    public void onAction(Action action) {
        super.actionEvent(action);
        switch (action.getType()) {
            case Action.ACTION_TASK_C:
                data = (MeizhiData) action.getData();
                event.status = BaseStoreChangeEvent.EVENT_SUCCESS;
                break;
            default:
        }
        emitEventChange();
    }

    @Override
    public BaseStoreChangeEvent getStoreChangeEvent() {
        return event;
    }

    @Override
    public BaseStoreChangeEvent getEvent() {
        return new TaskCChangeEvent(BaseStoreChangeEvent.EVENT_UI_INIT);
    }

    static public class TaskCChangeEvent extends BaseStoreChangeEvent {
        public TaskCChangeEvent(String status) {
            super(status);
        }
    }

}
