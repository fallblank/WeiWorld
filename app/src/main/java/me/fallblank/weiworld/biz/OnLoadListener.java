package me.fallblank.weiworld.biz;

/**
 * Created by fallb on 2017/4/24.
 */

public interface OnLoadListener<T> {
    void onComplete(T result);
    
    void onError(Throwable throwable);
}
