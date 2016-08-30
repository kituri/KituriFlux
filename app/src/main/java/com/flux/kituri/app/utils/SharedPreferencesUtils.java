package com.flux.kituri.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kirijin on 2016/8/24.
 * DaoUtils可选支持方案（偏轻量级，实现方式为字面意思）
 **/


 public class SharedPreferencesUtils {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEdit;
    private static SharedPreferencesUtils mAppStoreSetting;
    private Map<String, Object> cache = new HashMap<String, Object>();
    private static final Object object = new Object();

    private SharedPreferencesUtils(Context context) {
        //this.mContext = context;
        initSetting(context);

    }

    private void initSetting(Context context) {
        mSharedPreferences = context.getSharedPreferences("app_setting",
                Context.MODE_PRIVATE);
        mEdit = mSharedPreferences.edit();
    }

    protected static SharedPreferencesUtils getInstance(Context context) {
        synchronized (object) {
            if (mAppStoreSetting == null) {
                mAppStoreSetting = new SharedPreferencesUtils(context);
                return mAppStoreSetting;
            } else {
                return mAppStoreSetting;
            }
        }

    }

    protected synchronized Object getData(String key, Object defData){
        if(defData == null || key == null){
            //error
            return defData;
        }
        Object data = cache.get(key);
        if(data == null){
            if(defData instanceof Integer){
                data = mSharedPreferences.getInt(key, (Integer)defData);
            }else if(defData instanceof String){
                data = mSharedPreferences.getString(key, (String)defData);
            }else if(defData instanceof Long){
                data = mSharedPreferences.getLong(key, (Long)defData);
            }else if(defData instanceof Float){
                data = mSharedPreferences.getFloat(key, (Float)defData);
            }else if(defData instanceof Boolean){
                data = mSharedPreferences.getBoolean(key, (Boolean)defData);
            }
            if(data != null){
                cache.put(key, data);
                return data;
            }
            return defData;
        }
        return data;
    }

    protected synchronized void setData(String key, Object value){
        if(value == null || key == null){
            return;
        }
        if(value instanceof Integer){
            mEdit.putInt(key, (Integer)value);
        }else if(value instanceof String){
            mEdit.putString(key, (String)value);
        }else if(value instanceof Long){
            mEdit.putLong(key, (Long)value);
        }else if(value instanceof Float){
            mEdit.putFloat(key, (Float)value);
        }else if(value instanceof Boolean){
            mEdit.putBoolean(key, (Boolean)value);
        }
        cache.put(key, value);
        mEdit.commit();
    }

    public static void setData(Context context, String KEY, Object value){
        SharedPreferencesUtils.getInstance(context).setData(KEY, value);
    }

    // public <T extends View> void display(T container, String uri) {
    public static <T extends Object> T getData(Context context, String KEY, T defData){
        return (T) SharedPreferencesUtils.getInstance(context).getData(KEY, defData);
    }


}