package me.fallblank.weiworld;

import android.app.Application;
import android.util.DisplayMetrics;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import me.fallblank.weiworld.util.DeviceUtil;

/**
 * Created by fallb on 2017/3/27.
 */

public class App extends Application {
    private Oauth2AccessToken mAccessToken;
    private int mScreenWidth;
    private int mScreenWidthNoSpace;


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
        DeviceUtil device = new DeviceUtil(this);
        mScreenWidth = device.getDisplayMatrics().widthPixels;
    }

    public int getScreenWidth() {
        return mScreenWidth;
    }

    public int getScreenWidthNoSpcae() {
        if (mScreenWidthNoSpace == 0){
            int pictureSpcae = getResources().getDimensionPixelSize(R.dimen.picture_span_size);
            int marginSpace = getResources().getDimensionPixelSize(R.dimen.margin_padding_design);
            int space = pictureSpcae * 4 + marginSpace * 2;
            mScreenWidthNoSpace = mScreenWidth-space;
        }
        return mScreenWidthNoSpace;
    }

}
