package com.flux.kituri.app.business.login;

import com.flux.kituri.app.data.UserData;
import com.flux.kituri.app.flux.Action;
import com.flux.kituri.app.flux.BaseStoreChangeEvent;
import com.flux.kituri.app.flux.Store;
import com.flux.kituri.app.utils.UserUtils;

import org.greenrobot.eventbus.Subscribe;

public class LoginStore extends Store {

    private UserData data;

    public LoginStore() {
        super();
    }


    public UserData getUserData(){ return data;}

    @Override
    @Subscribe
    public void onAction(Action action) {
        super.actionEvent(action);
        switch (action.getType()) {
            case Action.ACTION_LOGIN:
                data = (UserData) action.getData();
                UserUtils.saveUserData(data);
                event.status = BaseStoreChangeEvent.EVENT_SUCCESS;
                break;
            case Action.ACTION_NEED_LOGIN:
                event.status = LoginChangeEvent.EVENT_NEED_LOGIN;
                break;
            case Action.ACTION_AUTO_LOGIN:
                data = (UserData) action.getData();
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
        return new LoginChangeEvent(BaseStoreChangeEvent.EVENT_UI_INIT);
    }

    static public class LoginChangeEvent extends BaseStoreChangeEvent {

        public static final String EVENT_NEED_LOGIN = "event_need_login";

        public LoginChangeEvent(String status) {
            super(status);
        }
    }

}
