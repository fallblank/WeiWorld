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
import me.fallblank.weiworld.biz.AuthComplete;
import me.fallblank.weiworld.util.AccessTokenKeeper;
import me.fallblank.weiworld.util.LogUtil;
import me.fallblank.weiworld.util.ToastUtil;
import me.fallblank.weiworld.view.IView;
import me.fallblank.weiworld.view.IWaitView;

import static com.sina.weibo.sdk.auth.Oauth2AccessToken.parseAccessToken;
import static com.sina.weibo.sdk.openapi.legacy.AccountAPI.CAPITAL.S;
import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.e;
import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.m;

/**
 * Created by fallb on 2017/3/22.
 */

public class LoginPresenter extends BasePresenter<IWaitView> implements WeiboAuthListener{

    private AuthInfo mAuthInfo;

    private SsoHandler mSsoHandler;

    private AuthComplete mAuthComplete;

    private Activity mLoginActivity;

    public LoginPresenter(Context context,Activity activity, AuthComplete authComplete,IWaitView view) {
        super(context, view);
        mLoginActivity = activity;
        mAuthInfo = new AuthInfo(mContext, BuildConfig.APP_KEY, BuildConfig.APP_GRANT_URL, BuildConfig.APP_SCOPE);
        mSsoHandler = new SsoHandler((Activity) mContext,mAuthInfo);
        mAuthComplete = authComplete;
    }

    public void login(){
        mView.show();
        authority();
        mView.hide();
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

    @Override
    public void onComplete(Bundle bundle) {
        Oauth2AccessToken token = Oauth2AccessToken.parseAccessToken(bundle);
        if (token.isSessionValid()){
            AccessTokenKeeper.writeAccessToken(mContext,token);
            LogUtil.d("Token:"+token.getToken());
            LogUtil.d("Phone"+token.getPhoneNum());
            LogUtil.d("RefreshToken:"+token.getRefreshToken());
            LogUtil.d("Uid:"+token.getUid());
            LogUtil.d("ExpiresTime:"+token.getExpiresTime());
            mAuthComplete.success();
        }else {
            mAuthComplete.fail();
        }
    }

    @Override
    public void onWeiboException(WeiboException e) {
        mAuthComplete.exception(e);
    }

    @Override
    public void onCancel() {
        mAuthComplete.cancer();
    }

}
