package com.flux.kituri.app.utils;

import com.flux.kituri.app.data.UserData;

import java.util.ArrayList;

/**
 * Created by kirijin on 2016/8/29.
 */
/**
 * Created by kirijin on 2016/8/29.
 */
public class UserUtils {

    static public Boolean isLogin(){
        ArrayList<UserData> userInfoData = DaoUtils.getInstance().query(UserData.class);
        if(userInfoData != null && userInfoData.size() > 0){
            return true;
        }else {
            return false;
        }
    }

    static public void logout(){
        DaoUtils.getInstance().deleteAll(UserData.class);
    }

    static public void saveUserData(UserData userData){
        DaoUtils.getInstance().save(userData);
    }

    /**
     * 请在调用前使用 isLogin() 进行判断。数据可能返回空。
     * */
    static public UserData getUserData(){
        ArrayList<UserData> list = DaoUtils.getInstance().query(UserData.class);
        if(list != null && list.size() > 0){
            return list.get(list.size() - 1);
        }else {
            return new UserData();
        }
    }

    static public void clearUserData(){
        DaoUtils.getInstance().deleteAll(UserData.class);
    }

}

