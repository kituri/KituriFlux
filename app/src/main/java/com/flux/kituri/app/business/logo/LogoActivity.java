package com.flux.kituri.app.business.logo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.flux.kituri.R;
import com.flux.kituri.app.business.backgroundService.UpdateService;
import com.flux.kituri.app.business.login.LoginActivity;
import com.flux.kituri.app.flux.ActionsCreator;
import com.flux.kituri.app.flux.BaseStoreChangeEvent;
import com.flux.kituri.app.flux.Store;
import com.flux.kituri.app.ui.base.BaseActivity;


import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class LogoActivity extends BaseActivity{

    static public final String TAG = "LoginActivity";
    private LogoActionsCreator actionsCreator;

    private TextView tv_bg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        initView();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionsCreator.taskA(this);
        startService(new Intent(this, UpdateService.class));
    }

    private void initView(){
        tv_bg = (TextView) findViewById(R.id.tv_bg);
    }

    @Override
    public ArrayList<Store> initFlux() {
        actionsCreator = (LogoActionsCreator) ActionsCreator.get(getDispatcher(), LogoActionsCreator.class);
        ArrayList<Store> stores = new ArrayList<Store>();
        stores.add(new TaskAStore());
        stores.add(new TaskBStore());
        stores.add(new TaskCStore());
        return stores;
    }

    @Subscribe
    public void onActionEvent(BaseStoreChangeEvent storeChangeEvent){
        if (storeChangeEvent == null) {
            return;
        }
        super.actionEvent(storeChangeEvent);
        if(storeChangeEvent.status == BaseStoreChangeEvent.EVENT_SUCCESS){
            if(storeChangeEvent instanceof TaskAStore.TaskAChangeEvent){
                tv_bg.setText("onTaskA ok");
                actionsCreator.taskB(this);
            } else if(storeChangeEvent instanceof TaskBStore.TaskBChangeEvent){
                tv_bg.setText("onTaskB ok");
                actionsCreator.taskC(this);
            } else if(storeChangeEvent instanceof TaskCStore.TaskCChangeEvent){
                tv_bg.setText("onTaskC ok");
                navigationLogin();
            }
        }
    }

    private void navigationLogin(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
