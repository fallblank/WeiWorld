package me.fallblank.weiworld.presenter;

import android.content.Context;

import me.fallblank.weiworld.iview.IView;

/**
 * Created by fallb on 2017/3/6.
 * Base presenter for MVP model
 */

public abstract class BasePresenter<T extends IView>{
    protected T mView;
    protected Context mContext;

    public BasePresenter(Context context,T view){
        mContext = context;
        mView = view;
    }
    
    public BasePresenter(Context context){
        mContext = context;
    }

    public T getView(){
        return mView;
    }

    public Context getContext(){
        return mContext;
    }
}
