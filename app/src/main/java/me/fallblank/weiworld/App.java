package me.fallblank.weiworld;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * Created by fallb on 2017/3/27.
 */

public class App extends Application {
    private Oauth2AccessToken mAccessToken;

    public Oauth2AccessToken getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(Oauth2AccessToken accessToken) {
        mAccessToken = accessToken;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化fresco图片加载框架
        Fresco.initialize(this);
    }
}
