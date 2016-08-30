package com.flux.kituri.app.model.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kirijin on 2016/4/12.
 */
public class MobileApi {

    public static final int TIME_OUT_LIMIT = 15;
    public static final String API_HOST = "http://gank.io/api/";

    protected static final Object object = new Object();
    static MobileApi mobileApi = null;
    private Retrofit retrofit;
    MobileApi(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(API_HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    private OkHttpClient getOkHttpClient() {
//暂时不启用拦截器 和 token设置
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                //.addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(TIME_OUT_LIMIT, TimeUnit.SECONDS)
                //.addNetworkInterceptor(mTokenInterceptor)
                .build();
        return client;
    }

    public static MobileApi getInstance() {
        synchronized (object) {
            if (mobileApi == null) {
                mobileApi = new MobileApi();
            }
            return mobileApi;
        }
    }

    public <T> T getService(Class<T> cls) {
        if(retrofit != null){
            return retrofit.create(cls);
        }
        return null;
    }

}
