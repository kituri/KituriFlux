package com.flux.kituri.app.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.flux.kituri.R;
import com.flux.kituri.app.data.inject.Injector;
import com.flux.kituri.app.flux.BaseStoreChangeEvent;
import com.flux.kituri.app.flux.Dispatcher;
import com.flux.kituri.app.flux.Store;

import java.util.ArrayList;


abstract public class BaseActivity extends FragmentActivity {

    private ProgressDialog mDialog;

    abstract public ArrayList<Store> initFlux();

    private Dispatcher dispatcher;
    private ArrayList<Store> stores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) Injector.onRestore(this, savedInstanceState);
        dispatcher = Dispatcher.get();
        ArrayList<Store> list = initFlux();
        if(list != null){
            for(Store store : list){
                stores.add(store);
                dispatcher.register(store);
            }
        }
        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
        }
    }

    public Dispatcher getDispatcher(){
        return dispatcher;
    }

    public void showWaitDialog(final String message, final Boolean isCanCancel) {
        mDialog.setTitle(message);
        if (!mDialog.isShowing()) {
            mDialog.setTitle(message);
            mDialog.setCancelable(isCanCancel);
            mDialog.setCanceledOnTouchOutside(isCanCancel);
            mDialog.show();
        }
    }





    public void dismiss() {
        if (mDialog != null){
            mDialog.dismiss();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Injector.save(this, outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        for(Store store : stores){
            store.register(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for(Store store : stores){
            store.unregister(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for(Store store : stores){
            dispatcher.unregister(store);
        }
        stores.clear();
        dismiss();
        mDialog = null;
    }
    protected void actionEvent(BaseStoreChangeEvent event){
        actionEvent(event, true);
    }

    protected void actionEvent(BaseStoreChangeEvent event, Boolean showErrorToast){
        switch (event.status){
            case BaseStoreChangeEvent.EVENT_UI_INIT:
                dismiss();
                break;
            case BaseStoreChangeEvent.EVENT_UI_LOADING:
                showWaitDialog(getString(R.string.msg_ui_loading), true);
                break;
            case BaseStoreChangeEvent.EVENT_UI_LOADING_NO_CANCEL:
                showWaitDialog(getString(R.string.msg_ui_loading), false);
                break;
            case BaseStoreChangeEvent.EVENT_UI_SUCCESS:
                dismiss();
                break;
            case BaseStoreChangeEvent.EVENT_UI_FAILURE:
                dismiss();
                if(showErrorToast){
                    Toast.makeText(this, getString(R.string.msg_ui_failure), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
