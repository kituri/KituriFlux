package com.flux.kituri.app.flux;

import org.greenrobot.eventbus.EventBus;

public abstract class Store {

    private EventBus mEventBus = new EventBus();

    protected BaseStoreChangeEvent event = getEvent();

    public void register(Object view){
        mEventBus.register(view);
    }

    public void unregister(Object view){
        mEventBus.unregister(view);
    }

    public abstract void onAction(Action action);

    public void emitEventChange(){
        mEventBus.post(getStoreChangeEvent());
    }

    public void actionEvent(Action action){
        switch (action.getType()) {
            case Action.ACTION_UI_INIT:
                event.status = BaseStoreChangeEvent.EVENT_UI_INIT;
                break;
            case Action.ACTION_UI_LOADING:
                event.status = BaseStoreChangeEvent.EVENT_UI_LOADING;
                break;
            case Action.ACTION_UI_LOADING_NO_CANCEL:
                event.status = BaseStoreChangeEvent.EVENT_UI_LOADING_NO_CANCEL;
                break;
            case Action.ACTION_UI_SUCCESS:
                event.status = BaseStoreChangeEvent.EVENT_UI_SUCCESS;
                break;
            case Action.ACTION_UI_FAILURE:
                event.status = BaseStoreChangeEvent.EVENT_UI_FAILURE;
                break;
        }
    }

    abstract public BaseStoreChangeEvent getStoreChangeEvent();

    abstract public BaseStoreChangeEvent getEvent();

}
