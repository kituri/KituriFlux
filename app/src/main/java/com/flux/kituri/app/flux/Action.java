package com.flux.kituri.app.flux;

import com.flux.kituri.app.data.Entry;

public class Action<T extends Entry> {
    private final String type;
    private final T data;

    //基于网络的常用状态，父类直接集成
    public static final String ACTION_UI_INIT = "action_ui_init";
    public static final String ACTION_UI_LOADING = "action_ui_loading";
    public static final String ACTION_UI_LOADING_NO_CANCEL = "action_ui_loading_no_cancel";
    public static final String ACTION_UI_SUCCESS = "action_ui_success";
    public static final String ACTION_UI_FAILURE = "action_ui_failure";
    public static final String ACTION_UI_DISMISS = "action_ui_dismiss";

    //业务逻辑使用宏定义们~
    public static final String ACTION_TASK_A = "action_task_a";
    public static final String ACTION_TASK_B = "action_task_b";
    public static final String ACTION_TASK_C = "action_task_c";
    public static final String ACTION_LOGIN = "action_login";
    public static final String ACTION_NEED_LOGIN = "action_need_login";
    public static final String ACTION_AUTO_LOGIN = "action_auto_login";

    public Action(String type, T data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public T getData() {
        return data;
    }
}
