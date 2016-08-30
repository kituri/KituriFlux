package com.flux.kituri.app.business.logo;


import android.content.Context;

import com.flux.kituri.app.data.MeizhiData;
import com.flux.kituri.app.flux.Action;
import com.flux.kituri.app.flux.ActionsCreator;
import com.flux.kituri.app.flux.Dispatcher;
import com.flux.kituri.app.model.net.MobileApi;
import com.flux.kituri.app.model.retrofit.RetGankMeizhi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogoActionsCreator extends ActionsCreator {

    public LogoActionsCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    //模拟初始应用会调用多个接口,这里并没有用到rxjava等情况，方便理解
    public void task(Context context, final String actionTask, final int page){
        dispatcher.dispatch(new Action(Action.ACTION_UI_LOADING, null));
        MobileApi.getInstance().getService(RetGankMeizhi.class).getMeizhiList(page).enqueue(new Callback<MeizhiData>() {
            @Override
            public void onResponse(Call<MeizhiData> call, Response<MeizhiData> response) {
                dispatcher.dispatch(new Action(actionTask, response.body()));
                dispatcher.dispatch(new Action(Action.ACTION_UI_SUCCESS, null));
            }

            @Override
            public void onFailure(Call<MeizhiData> call, Throwable t) {
                dispatcher.dispatch(new Action(Action.ACTION_UI_FAILURE, null));
            }
        });
    }

    public void taskA(Context context) {
        task(context, Action.ACTION_TASK_A, 1);
    }

    public void taskB(Context context) {
        task(context, Action.ACTION_TASK_B, 2);
    }

    public void taskC(Context context) {
        task(context, Action.ACTION_TASK_C, 3);
    }


}
