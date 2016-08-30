package com.flux.kituri.app.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.flux.kituri.app.flux.Dispatcher;
import com.flux.kituri.app.flux.Store;
import com.flux.kituri.app.ui.base.BaseActivity;

import java.util.ArrayList;

//Fragment的flux还未实战过，等过段事件补完，当然现在也是能用的……（应该-_-b）
//继承该类的业务逻辑类Fragment除了我给出的例子，建议放入business包中进行业务归类
public abstract class BaseFragment extends Fragment{

    protected BaseActivity baseActivity;

    abstract public ArrayList<Store> initFlux();

    private Dispatcher dispatcher;
    private ArrayList<Store> stores = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dispatcher = Dispatcher.get();
        ArrayList<Store> list = initFlux();
        if(list != null){
            for(Store store : list){
                stores.add(store);
                dispatcher.register(store);
            }
        }
    }

    public Dispatcher getDispatcher(){
        return dispatcher;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for(Store store : stores){
            dispatcher.unregister(store);
        }
        stores.clear();
    }

    public BaseActivity getBaseActivity(){
        Activity act = getActivity();
        if(act instanceof BaseActivity){
            return (BaseActivity)act;
        }
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        for(Store store : stores){
            store.register(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        for(Store store : stores){
            store.unregister(this);
        }
    }

}
