package com.flux.kituri.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.flux.kituri.app.utils.DaoUtils;


public class MobileApplication extends Application {

    protected static final Object object = new Object();

    static private MobileApplication app = null;

    public static MobileApplication getInstance() {
        synchronized (object) {
            return app;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        DaoUtils.getInstance(this);
        app = this;
    }



}
