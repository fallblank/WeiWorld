package me.fallblank.weiworld.model;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.fallblank.weiworld.bean.LoginUser;
import me.fallblank.weiworld.biz.OnLoadListener;
import me.fallblank.weiworld.biz.retrofit.ILoginUser;
import me.fallblank.weiworld.impl.retrofit.RetrofitCenter;

/**
 * Created by fallb on 2017/4/24.
 */

public class UserInfoManager extends BaseModel {
    
    private ILoginUser mLoginUser = new RetrofitCenter().getLoginUser();
    
    private OnLoadListener<LoginUser> mListener;
    
    public UserInfoManager(Context context, OnLoadListener<LoginUser> listener) {
        super(context);
        this.mListener = listener;
    }
    
    
    public void fetchFromNet(String token, String uid) {
        mLoginUser.getUser(token, uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //empty
                    }
                    
                    @Override
                    public void onNext(LoginUser loginUser) {
                        mListener.onComplete(loginUser);
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        mListener.onError(e);
                    }
                    
                    @Override
                    public void onComplete() {
                        //empty
                    }
                });
    }
    
    @Override
    protected void store() {
        
    }
    
    @Override
    protected void restore() {
        
    }
}
