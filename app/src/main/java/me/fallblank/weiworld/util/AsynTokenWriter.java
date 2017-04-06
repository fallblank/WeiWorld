package me.fallblank.weiworld.util;

import android.content.Context;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * Created by fallb on 2017/4/6.
 */

public final class AsynTokenWriter extends Thread {
    private Oauth2AccessToken mToken;
    private Context mContext;

    public AsynTokenWriter(Context context, Oauth2AccessToken token) {
        super("AsynTokenWriterThread");
        this.mContext = context;
        this.mToken = token;
    }

    @Override
    public void run() {
        AccessTokenKeeper.writeAccessToken(mContext,mToken);
        LogUtil.d("Thread-"+Thread.currentThread().getName()+":write token finished");
    }
}
