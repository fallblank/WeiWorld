package me.fallblank.weiworld.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import me.fallblank.weiworld.biz.OnFetchListener;
import me.fallblank.weiworld.biz.OnLoadListener;
import me.fallblank.weiworld.iview.IWaitView;
import me.fallblank.weiworld.model.UserInfoManager;
import me.fallblank.weiworld.util.AsynTokenWriter;
import me.fallblank.weiworld.util.Constant;

/**
 * Created by fallb on 2017/3/22.
 */

public class LoginPresenter extends BasePresenter<IWaitView> implements WeiboAuthListener, OnLoadListener<LoginUser> {
    
    private AuthInfo mAuthInfo;
    
    private SsoHandler mSsoHandler;
    
    //从新浪端授权完成后调用接口
    private AuthComplete mAuthComplete;
    
    //拉取用户信息回掉接口
    private OnFetchListener mOnFetchListener;
    
    private Activity mLoginActivity;
    
    private UserInfoManager mUserInfoManager = new UserInfoManager(this);;
    
    public LoginPresenter(Context context, Activity activity, AuthComplete authComplete, OnFetchListener fetchListener, IWaitView view) {
        super(context, view);
        this.mLoginActivity = activity;
        this.mAuthInfo = new AuthInfo(mContext, BuildConfig.APP_KEY, BuildConfig.APP_GRANT_URL, BuildConfig.APP_SCOPE);
        this.mOnFetchListener = fetchListener;
        this.mSsoHandler = new SsoHandler((Activity) mContext, mAuthInfo);
        this.mAuthComplete = authComplete;
    }
    
    public LoginPresenter(Context context) {
        super(context);
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
            mAuthComplete.success();
        } else {
            mAuthComplete.fail();
        }
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
    
    public void fetchLoginUserInfo() {
        if (mView!=null){
            mView.show();
        }
        App app = (App) mContext.getApplicationContext();
        Oauth2AccessToken token = app.getAccessToken();
        String access_token = token.getToken();
        String uid = token.getUid();
        mUserInfoManager.fetchFromNet(access_token, uid);
    }
    
    public void setOnFetchListener(OnFetchListener onFetchListener) {
        mOnFetchListener = onFetchListener;
    }
    
    @Override
    public void onComplete(LoginUser result) {
        App app = (App) mContext.getApplicationContext();
        app.setUser(result);
        mOnFetchListener.onFetchSuccess();
        if (null != mView) {
            mView.hide();
        }
    }
    
    @Override
    public void onError(Throwable throwable) {
        mOnFetchListener.onFetchFail();
        if (null != mView) {
            mView.hide();
        }
    }
}
