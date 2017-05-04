package me.fallblank.weiworld.model;


import android.content.Context;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.fallblank.weiworld.App;
import me.fallblank.weiworld.bean.ContentResponse;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.bean.WeiboComparator;
import me.fallblank.weiworld.bean.WeiboReverseComparator;
import me.fallblank.weiworld.biz.ILoader;
import me.fallblank.weiworld.biz.retrofit.IWeiboContent;
import me.fallblank.weiworld.impl.retrofit.RetrofitCenter;

/**
 * Created by fallb on 2017/3/31.
 */

public class ContentManager extends BaseModel {
    private static final String kEY_ACCESS_TOKEN = "access_token";
    private static final String kEY_COUNT = "count";
    private static final String kEY_PAGE = "page";
    private static final String KEY_MAX_ID = "max_id";
    private static final String KEY_SINCE_ID = "since_id";
    
    
    private final String mRefreshPageSize = "15";
    private final String mLoadPageSize = "15";
    private final String mPageIndex = "1";
    
    private long mMaxID = 0L;
    private long mSinceID = 0L;
    private HashMap<String, String> mQueryMap;
    
    private IWeiboContent mServer;
    private List<Weibo> mDataList;
    
    private ILoader mRefreshListner;
    private ILoader mLoadMoreListner;
    
    public ContentManager(Context context, List<Weibo> dataList) {
        super(context);
        this.mDataList = dataList;
        RetrofitCenter imp = new RetrofitCenter();
        this.mServer = imp.getWeiboContent();
        initQueryMap(context);
    }
    
    public void setLoadMoreListner(ILoader listner) {
        mLoadMoreListner = listner;
    }
    
    public void setRefreshListner(ILoader listner) {
        mRefreshListner = listner;
    }
    
    public void initQueryMap(Context context) {
        mQueryMap = new HashMap<>();
        App app = (App) context.getApplicationContext();
        String token = app.getAccessToken().getToken();
        mQueryMap.put(ContentManager.kEY_ACCESS_TOKEN, token);
    }
    
    public void loadMore() {
        mQueryMap.remove(KEY_SINCE_ID);
        mQueryMap.put(ContentManager.kEY_COUNT, mLoadPageSize);
        mQueryMap.put(ContentManager.kEY_PAGE, mPageIndex);
        mQueryMap.put(ContentManager.KEY_MAX_ID, String.valueOf(mMaxID));
        mServer.listLastWeibo(mQueryMap)
                .subscribeOn(Schedulers.io())
                .map(new Function<ContentResponse, ContentResponse>() {
                    @Override
                    public ContentResponse apply(@NonNull ContentResponse contentResponse) throws Exception {
                        WeiboReverseComparator comparator = new WeiboReverseComparator();
                        Collections.sort(contentResponse.getStatuses(), comparator);
                        updateMaxId(contentResponse.getNext_cursor());
                        return contentResponse;
                    }
                })
                .flatMap(new Function<ContentResponse, ObservableSource<Weibo>>() {
                    @Override
                    public ObservableSource<Weibo> apply(@NonNull ContentResponse contentResponse) throws Exception {
                        List<Weibo> weiboList = contentResponse.getStatuses();
                        return Observable.fromIterable(weiboList);
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Weibo>() {
                    int size = 0;
                    
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mLoadMoreListner != null) {
                            mLoadMoreListner.start();
                        }
                    }
                    
                    @Override
                    public void onNext(Weibo weibo) {
                        size++;
                        mDataList.add(weibo);
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        if (mLoadMoreListner != null) {
                            mLoadMoreListner.error(e);
                        }
                    }
                    
                    @Override
                    public void onComplete() {
                        if (mLoadMoreListner != null) {
                            mLoadMoreListner.complete(size);
                        }
                    }
                });
    }
    
    /**
     * 获取最新数据
     *
     * @return 新增数据数量
     */
    public void refresh() {
        mQueryMap.remove(KEY_MAX_ID);
        mQueryMap.put(ContentManager.kEY_COUNT, mRefreshPageSize);
        mQueryMap.put(ContentManager.kEY_PAGE, mPageIndex);
        mQueryMap.put(ContentManager.KEY_SINCE_ID, String.valueOf(mSinceID));
        mServer.listLastWeibo(mQueryMap)
                .subscribeOn(Schedulers.io())
                .map(new Function<ContentResponse, ContentResponse>() {
                    @Override
                    public ContentResponse apply(@NonNull ContentResponse contentResponse) throws Exception {
                        WeiboComparator comparator = new WeiboComparator();
                        Collections.sort(contentResponse.getStatuses(), comparator);
                        updateMaxId(contentResponse.getNext_cursor());
                        return contentResponse;
                    }
                })
                .flatMap(new Function<ContentResponse, ObservableSource<Weibo>>() {
                    @Override
                    public ObservableSource<Weibo> apply(@NonNull ContentResponse contentResponse) throws Exception {
                        List<Weibo> weiboList = contentResponse.getStatuses();
                        if (weiboList.size() > 0) {
                            updateSinceId(weiboList.get(weiboList.size() - 1).getId());
                        }
                        return Observable.fromIterable(weiboList);
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Weibo>() {
                    int size = 0;
                    
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mRefreshListner != null) {
                            mRefreshListner.start();
                        }
                    }
                    
                    @Override
                    public void onNext(Weibo weibo) {
                        //保证最新在最前面
                        mDataList.add(0, weibo);
                        size++;
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        if (mRefreshListner != null) {
                            mRefreshListner.error(e);
                        }
                    }
                    
                    @Override
                    public void onComplete() {
                        if (mRefreshListner != null) {
                            mRefreshListner.complete(size);
                        }
                    }
                });
    }
    
    private void updateSinceId(long newSinceId) {
        if (mSinceID == 0L) {
            mSinceID = newSinceId;
        }
        mSinceID = newSinceId > mSinceID ? newSinceId : mSinceID;
    }
    
    private void updateMaxId(long newMaxId) {
        if (mMaxID == 0L) {
            mMaxID = newMaxId;
        }
        mMaxID = newMaxId < mMaxID ? newMaxId : mMaxID;
    }
    
    @Override
    protected void store() {
        
    }
    
    @Override
    protected void restore() {
        
    }
}
