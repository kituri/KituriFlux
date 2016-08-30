package com.flux.kituri.app.utils;

import android.content.Context;

import com.flux.kituri.app.MobileApplication;
import com.litesuits.orm.LiteOrm;

/**
 * Created by kirijin on 2016/5/30.
 */

public class DaoUtils {

    protected static final Object object = new Object();
    private static LiteOrm liteOrm;

    public static LiteOrm getInstance(Context context){
        synchronized (object) {
            if (liteOrm == null) {
                liteOrm = LiteOrm.newSingleInstance(context, "liteorm.db");
            }
            liteOrm.setDebugged(true); // open the log
        }
        return liteOrm;
    }

    public static LiteOrm getInstance(){
        return getInstance(MobileApplication.getInstance());
    }

}
