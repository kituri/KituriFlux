package com.flux.kituri.app.flux;


import android.os.Handler;

import java.util.ArrayList;
import java.util.List;


public class Dispatcher {
    private static Dispatcher instance;
    private final List<Store> stores = new ArrayList<>();
    private final Handler handler = new Handler();

    protected static final Object object = new Object();

    public static Dispatcher get() {
        synchronized (object) {
            if (instance == null) {
                instance = new Dispatcher();
            }
            return instance;
        }
    }

    Dispatcher() {}

    public void register(final Store store) {
        if (!stores.contains(store)) {
            stores.add(store);
        }
    }

    public void unregister(final Store store) {
        stores.remove(store);
    }

    public void dispatch(final Action action) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                post(action);
            }
        });
    }

    private void post(final Action action) {
        for (Store store : stores) {
            store.onAction(action);
        }
    }
}
