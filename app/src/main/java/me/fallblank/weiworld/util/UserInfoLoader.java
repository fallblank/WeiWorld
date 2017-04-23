package me.fallblank.weiworld.util;

import android.content.Context;
import android.util.Log;


import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;

import me.fallblank.weiworld.BuildConfig;
import me.fallblank.weiworld.bean.LoginUser;

/**
 * Created by fallb on 2017/4/21.
 */

public class UserInfoLoader {

    public interface LoadListener {
        void onComplete(LoginUser userInfo);

        void error(Throwable throwable);
    }

    public static void asynLoadUserInfo(Context context, Oauth2AccessToken token, final LoadListener listener) {
        UsersAPI usersAPI = new UsersAPI(context, BuildConfig.APP_KEY, token);
        usersAPI.show(token.getUid(), new RequestListener() {
            @Override
            public void onComplete(String json) {
                Gson gson = new Gson();
                try {
                    LoginUser user = gson.fromJson(json, LoginUser.class);
                    listener.onComplete(user);
                } catch (Exception e) {
                    listener.error(e);
                }

            }

            @Override
            public void onWeiboException(WeiboException e) {
                listener.error(e);
            }
        });
    }

}
