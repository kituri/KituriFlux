package com.flux.kituri.app.flux;

public class BaseStoreChangeEvent {

    public static final String EVENT_UI_INIT = "event_ui_init";
    public static final String EVENT_UI_LOADING = "event_ui_loading";
    public static final String EVENT_UI_LOADING_NO_CANCEL = "event_ui_loading_no_cancel";
    public static final String EVENT_UI_SUCCESS = "event_ui_success";
    public static final String EVENT_UI_FAILURE = "event_ui_failure";

    //业务成功基础类型
    static public final String EVENT_SUCCESS = "event_success";

    public String status;

    public BaseStoreChangeEvent(String status) {
        this.status = status;
    }

}
