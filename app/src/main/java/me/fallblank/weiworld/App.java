package me.fallblank.weiworld;

import android.app.Application;
import android.content.IntentFilter;
import android.util.DisplayMetrics;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import me.fallblank.weiworld.bean.LoginUser;
import me.fallblank.weiworld.ui.receiver.NetworkReceiver;
import me.fallblank.weiworld.util.DeviceUtil;
import me.fallblank.weiworld.util.NetworkChecker;

/**
 * Created by fallb on 2017/3/27.
 */

public class App extends Application {
    private Oauth2AccessToken mAccessToken;
    private LoginUser mUser;

    private int mScreenWidth;
    private int mScreenWidthNoSpace;

    private boolean mWifiStatue;
    private boolean mMobileStatue;


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化fresco图片加载框架
        Fresco.initialize(this);

        //加载设备屏幕宽度数据
        DeviceUtil device = new DeviceUtil(this);
        mScreenWidth = device.getDisplayMatrics().widthPixels;

        //加载网络情况
        NetworkChecker checker = new NetworkChecker();
        checker.check(this);
    }

    public LoginUser getUser() {
        return mUser;
    }

    public void setUser(LoginUser user) {
        mUser = user;
    }

    public Oauth2AccessToken getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(Oauth2AccessToken accessToken) {
        mAccessToken = accessToken;
    }

    public int getScreenWidth() {
        return mScreenWidth;
    }

    public int getScreenWidthNoSpcae() {
        if (mScreenWidthNoSpace == 0) {
            int pictureSpcae = getResources().getDimensionPixelSize(R.dimen.picture_span_size);
            int marginSpace = getResources().getDimensionPixelSize(R.dimen.margin_padding_design);
            int space = pictureSpcae * 4 + marginSpace * 2;
            mScreenWidthNoSpace = mScreenWidth - space;
        }
        return mScreenWidthNoSpace;
    }

    public boolean isWifiStatue() {
        return mWifiStatue;
    }

    public void setWifiStatue(boolean wifiStatue) {
        mWifiStatue = wifiStatue;
    }

    public boolean isMobileStatue() {
        return mMobileStatue;
    }

    public void setMobileStatue(boolean mobileStatue) {
        mMobileStatue = mobileStatue;
    }
}
