package me.fallblank.weiworld.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.net.Uri;
import android.os.Bundle;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import me.fallblank.weiworld.App;
import me.fallblank.weiworld.BuildConfig;
import me.fallblank.weiworld.bean.LoginUser;
import me.fallblank.weiworld.biz.AuthComplete;
import me.fallblank.weiworld.util.AsynTokenWriter;
import me.fallblank.weiworld.util.Constant;
import me.fallblank.weiworld.iview.IWaitView;
import me.fallblank.weiworld.util.LogUtil;
import me.fallblank.weiworld.util.UserInfoLoader;

/**
 * Created by fallb on 2017/3/22.
 */

public class LoginPresenter extends BasePresenter<IWaitView> implements WeiboAuthListener {

    private AuthInfo mAuthInfo;

    private SsoHandler mSsoHandler;

    private AuthComplete mAuthComplete;

    private Activity mLoginActivity;

    public LoginPresenter(Context context, Activity activity, AuthComplete authComplete, IWaitView view) {
        super(context, view);
        mLoginActivity = activity;
        mAuthInfo = new AuthInfo(mContext, BuildConfig.APP_KEY, BuildConfig.APP_GRANT_URL, BuildConfig.APP_SCOPE);
        mSsoHandler = new SsoHandler((Activity) mContext, mAuthInfo);
        mAuthComplete = authComplete;
    }

    public void login() {
        mView.show();
        authority();
    }

    public void register() {
        mView.show();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(Constant.REG_URL);
        intent.setData(uri);
        mLoginActivity.startActivity(intent);
        mView.hide();
    }

    public void forgotPassword() {
        mView.show();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(Constant.FORGOT_URL);
        intent.setData(uri);
        mLoginActivity.startActivity(intent);
        mView.hide();
    }

    private void authority() {
        mSsoHandler.authorize(this);
    }

    public void authorizeCallback(int requestCode, int resultCode, Intent data) {
        mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
    }

    @Override
    public void onComplete(Bundle bundle) {
        Oauth2AccessToken token = Oauth2AccessToken.parseAccessToken(bundle);
        if (token.isSessionValid()) {
            //保存token、user到context对象
            final App app = (App) mContext.getApplicationContext();
            app.setAccessToken(token);
            //异步写入token到sharedPreference
            asynWriteToken(token);
            UserInfoLoader.asynLoadUserInfo(mContext, token, new UserInfoLoader.LoadListener() {
                @Override
                public void onComplete(LoginUser userInfo) {
                    app.setUser(userInfo);
                    mAuthComplete.success();
                }

                @Override
                public void error(Throwable throwable) {
                    LogUtil.d("load user info", throwable);
                    mAuthComplete.fail();
                }
            });

        } else {
            mAuthComplete.fail();
        }
    }

    private void loadLoginUserInfo(Context context, Oauth2AccessToken token) {

    }

    @Override
    public void onWeiboException(WeiboException e) {
        mView.hide();
        mAuthComplete.exception(e);
    }

    @Override
    public void onCancel() {
        mView.hide();
        mAuthComplete.cancel();
    }

    private void asynWriteToken(Oauth2AccessToken token) {
        AsynTokenWriter tokenWriter = new AsynTokenWriter(mContext, token);
        tokenWriter.start();
    }

}
