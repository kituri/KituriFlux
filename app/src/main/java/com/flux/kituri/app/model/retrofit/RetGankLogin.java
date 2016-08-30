package com.flux.kituri.app.model.retrofit;

import com.flux.kituri.app.data.UserData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kirijin on 2016/8/30.
 * 也就类名叫login了……url莫吐槽-_-b
 */
//http://gank.io/api/history/content/2/1
public interface RetGankLogin {
    @GET("history/content/2/1")
    Call<UserData> login();
}
