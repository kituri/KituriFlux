package com.flux.kituri.app.business.backgroundService;


import android.content.Context;

import com.flux.kituri.app.data.UserData;
import com.flux.kituri.app.flux.Action;
import com.flux.kituri.app.flux.ActionsCreator;
import com.flux.kituri.app.flux.Dispatcher;
import com.flux.kituri.app.model.net.MobileApi;
import com.flux.kituri.app.model.retrofit.RetGankLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActionsCreator extends ActionsCreator {

    public UpdateActionsCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    //flux严格把业务逻辑绑定在UI上，若要跨界调用，请使用该方案实现。
    //自动登录的后加载实现，获取数据后会更新用户数据
    //形参只用到了一个error属性，应该是帐号和密码的。莫怪-_-b要怪就怪家家没给登录接口
    public void login(Context context, Boolean error){
        MobileApi.getInstance().getService(RetGankLogin.class).login().
                enqueue(new Callback<UserData>() {
                    @Override
                    public void onResponse(Call<UserData> call, Response<UserData> response) {
                        dispatcher.dispatch(new Action(Action.ACTION_LOGIN, (UserData)response.body()));
                    }

                    @Override
                    public void onFailure(Call<UserData> call, Throwable t) {

                    }
                });
    }

}
