package me.fallblank.weiworld.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import me.fallblank.weiworld.BuildConfig;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.util.LogUtil;
import me.fallblank.weiworld.util.ToastUtil;
import me.fallblank.weiworld.view.IView;
import me.fallblank.weiworld.view.IWaitView;

import static com.sina.weibo.sdk.auth.Oauth2AccessToken.parseAccessToken;
import static com.sina.weibo.sdk.openapi.legacy.AccountAPI.CAPITAL.S;
import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.m;

/**
 * Created by fallb on 2017/3/22.
 */

public class LoginPresenter extends BasePresenter<IWaitView> implements WeiboAuthListener{

    private AuthInfo mAuthInfo;

    private SsoHandler mSsoHandler;

    private Activity mLoginActivity;

    public LoginPresenter(Context context,Activity activity, IWaitView view) {
        super(context, view);
        mLoginActivity = activity;
        mAuthInfo = new AuthInfo(mContext, BuildConfig.APP_KEY, BuildConfig.APP_GRANT_URL, BuildConfig.APP_SCOPE);
        mSsoHandler = new SsoHandler((Activity) mContext,mAuthInfo);
    }

    public boolean login(){
        mView.show();
        authority();
        //check athcode
        saveAuthCode();
        mView.hide();
        return false;
        //处理登录逻辑
    }

    public void register(){
        mView.show();
        //处理注册逻辑
    }

    public void forgotPassword(){
        mView.show();
        //处理密码找回逻辑
    }

    private void authority(){
        mSsoHandler.authorize(this);
    }

    public void authorizeCallback(int requestCode,int resultCode,Intent data){
        mSsoHandler.authorizeCallBack(requestCode,resultCode,data);
    }

    private String saveAuthCode(){
        return null;
    }

    @Override
    public void onComplete(Bundle bundle) {
        Oauth2AccessToken token = Oauth2AccessToken.parseAccessToken(bundle);
        LogUtil.d(token.getToken());
    }

    @Override
    public void onWeiboException(WeiboException e) {

    }

    @Override
    public void onCancel() {
        mView.hide();
        ToastUtil.show(mContext,mContext.getString(R.string.auth_cancle));
    }
}
