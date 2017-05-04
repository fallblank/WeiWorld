package me.fallblank.weiworld.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.legacy.FavoritesAPI;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.fallblank.weiworld.App;
import me.fallblank.weiworld.BuildConfig;
import me.fallblank.weiworld.bean.Favorite;
import me.fallblank.weiworld.biz.ILoader;
import me.fallblank.weiworld.util.LogUtil;
import me.fallblank.weiworld.util.TimeFormatter;

/**
 * Created by fallb on 2017/4/25.
 */

public class FavoritaManager extends BaseModel {
    
    private FavoritesAPI mAPI;
    private int mTotalSize;
    private int mPageSize = 20;
    private int mPageIndex = 1;
    private List<Favorite.FavoritesBean> mDataList;
    private ILoader mLoadListener;
    
    public FavoritaManager(Context context, List<Favorite.FavoritesBean> dataList) {
        super(context);
        App app = (App) context.getApplicationContext();
        mContext = context;
        this.mAPI = new FavoritesAPI(context, BuildConfig.APP_KEY, app.getAccessToken());
        this.mDataList = dataList;
    }
    
    public void load() {
        mAPI.favorites(mPageSize, mPageIndex, new RequestListener() {
            @Override
            public void onComplete(String s) {
                Observable.just(s)
                        .map(new Function<String, Favorite>() {
                            @Override
                            public Favorite apply(@NonNull String s) throws Exception {
                                Gson mGson = new GsonBuilder()
                                        .setDateFormat(TimeFormatter.TIME_FORMAT_PARTTERN)
                                        .create();
                                return mGson.fromJson(s, Favorite.class);
                            }
                        })
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Favorite>() {
                            private int size = 0;
                            
                            @Override
                            public void onSubscribe(Disposable d) {
                                mLoadListener.start();
                            }
                            
                            @Override
                            public void onNext(Favorite favorite) {
                                for (Favorite.FavoritesBean bean : favorite.getFavorites()) {
                                    mDataList.add(bean);
                                }
                                size = favorite.getFavorites().size();
                                setTotalSize(favorite.getTotal_number());
                            }
                            
                            @Override
                            public void onError(Throwable e) {
                                mLoadListener.error(e);
                            }
                            
                            @Override
                            public void onComplete() {
                                mLoadListener.complete(size);
                                if (size != 0){
                                    addPageIndex();
                                }
                            }
                        });
            }
            
            @Override
            public void onWeiboException(WeiboException e) {
                LogUtil.d("exception", e);
            }
        });
    }
    
    @Override
    protected void store() {
        
    }
    
    @Override
    protected void restore() {
        
    }
    
    private void setTotalSize(int totalSize) {
        mTotalSize = totalSize;
    }
    
    private void addPageIndex(){
        mPageIndex++;
    }
    
    public void setLoadListener(ILoader loadListener) {
        mLoadListener = loadListener;
    }
}
