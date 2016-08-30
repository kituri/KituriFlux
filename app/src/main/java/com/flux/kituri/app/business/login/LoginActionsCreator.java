package com.flux.kituri.app.business.login;


import android.content.Context;
import android.content.Intent;

import com.flux.kituri.app.business.backgroundService.UpdateService;
import com.flux.kituri.app.data.UserData;
import com.flux.kituri.app.flux.Action;
import com.flux.kituri.app.flux.ActionsCreator;
import com.flux.kituri.app.flux.Dispatcher;
import com.flux.kituri.app.model.net.MobileApi;
import com.flux.kituri.app.model.retrofit.RetGankLogin;
import com.flux.kituri.app.utils.UserUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActionsCreator extends ActionsCreator {

    public LoginActionsCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public void autoLogin(Context context){
        dispatcher.dispatch(new Action(Action.ACTION_UI_LOADING, null));

        if(UserUtils.isLogin()){
            dispatcher.dispatch(new Action(Action.ACTION_AUTO_LOGIN, UserUtils.getUserData()));
            dispatcher.dispatch(new Action(Action.ACTION_UI_SUCCESS, null));
            Intent mIntent = new Intent(UpdateService.ACTION_SERVICE_LOGIN);
            context.sendBroadcast(mIntent);
        }else{
            dispatcher.dispatch(new Action(Action.ACTION_NEED_LOGIN, null));
        }
    }

    //形参account和password也就做做样子^_^
    public void login(Context context, String account, String password){
        dispatcher.dispatch(new Action(Action.ACTION_UI_LOADING, null));
        MobileApi.getInstance().getService(RetGankLogin.class).login().
                enqueue(new Callback<UserData>() {
                    @Override
                    public void onResponse(Call<UserData> call, Response<UserData> response) {
                        dispatcher.dispatch(new Action(Action.ACTION_LOGIN, (UserData)response.body()));
                        dispatcher.dispatch(new Action(Action.ACTION_UI_SUCCESS, null));
                    }

                    @Override
                    public void onFailure(Call<UserData> call, Throwable t) {
                        dispatcher.dispatch(new Action(Action.ACTION_UI_FAILURE, null));
                    }
                });
    }

}
