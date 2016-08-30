package com.flux.kituri.app.business.logo;

import com.flux.kituri.app.data.MeizhiData;
import com.flux.kituri.app.flux.Action;
import com.flux.kituri.app.flux.BaseStoreChangeEvent;
import com.flux.kituri.app.flux.Store;

public class TaskBStore extends Store {

    private MeizhiData data;
    public TaskBStore() {
        super();
    }

    public MeizhiData getData(){ return data;}

    @Override
    public void onAction(Action action) {
        super.actionEvent(action);
        switch (action.getType()) {
            case Action.ACTION_TASK_B:
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
        return new TaskBChangeEvent(BaseStoreChangeEvent.EVENT_UI_INIT);
    }

    static public class TaskBChangeEvent extends BaseStoreChangeEvent {

        public TaskBChangeEvent(String status) {
            super(status);
        }
    }

}
