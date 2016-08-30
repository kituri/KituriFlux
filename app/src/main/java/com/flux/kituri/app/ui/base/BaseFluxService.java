package com.flux.kituri.app.ui.base;

import android.app.Service;

import com.flux.kituri.app.flux.Dispatcher;
import com.flux.kituri.app.flux.Store;

import java.util.ArrayList;

/**
 * Created by kirijin on 2016/8/29.
 */
abstract public class BaseFluxService extends Service{

    abstract public ArrayList<Store> initFlux();

    private Dispatcher dispatcher;
    private ArrayList<Store> stores = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        dispatcher = Dispatcher.get();
        ArrayList<Store> list = initFlux();
        if(list != null){
            for(Store store : list){
                stores.add(store);
                dispatcher.register(store);
            }
        }
        for(Store store : stores){
            store.register(this);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for(Store store : stores){
            store.unregister(this);
        }
        stores.clear();
    }

    public Dispatcher getDispatcher(){
        return dispatcher;
    }
}
