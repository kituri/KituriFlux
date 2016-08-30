package com.flux.kituri.app.business.backgroundService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.flux.kituri.app.business.login.LoginStore;
import com.flux.kituri.app.data.UserData;
import com.flux.kituri.app.flux.ActionsCreator;
import com.flux.kituri.app.flux.BaseStoreChangeEvent;
import com.flux.kituri.app.flux.Store;
import com.flux.kituri.app.ui.base.BaseFluxService;
import com.flux.kituri.app.utils.UserUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by kirijin on 2016/8/29.
 */
public class UpdateService extends BaseFluxService {

    static public final String ACTION_SERVICE_LOGIN = "action_service_login";

    private UpdateActionsCreator updateActionsCreator;

    @Override
    public ArrayList<Store> initFlux() {
        updateActionsCreator = (UpdateActionsCreator) ActionsCreator.get(getDispatcher(), UpdateActionsCreator.class);
        ArrayList<Store> stores = new ArrayList<Store>();
        stores.add(new LoginStore());
        return stores;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(updateReceiver, new IntentFilter(ACTION_SERVICE_LOGIN));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(updateReceiver);
    }

    @Subscribe
    public void onActionEvent(BaseStoreChangeEvent storeChangeEvent) {
        if (storeChangeEvent == null) {
            return;
        }
        if (storeChangeEvent.status == BaseStoreChangeEvent.EVENT_SUCCESS) {
            if (storeChangeEvent instanceof LoginStore.LoginChangeEvent) {
                Log.e("", "登录成功");
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    final public BroadcastReceiver updateReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case ACTION_SERVICE_LOGIN:
                    //模拟了下从数据库获取数据后登录，只用到了一个error属性……莫怪-_-b要怪就怪家家没给登录接口..
                    UserData userData = UserUtils.getUserData();
                    updateActionsCreator.login(UpdateService.this, userData.getError());
                    break;
            }
        }

    };

}
