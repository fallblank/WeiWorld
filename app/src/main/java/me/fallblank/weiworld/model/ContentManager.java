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
import me.fallblank.weiworld.bean.ContentsResponse;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.bean.WeiboComparator;
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
    
    
    private final String mRefreshPageSize = "20";
    private final String mLoadPageSize = "10";
    private final String mPageIndex = "1";
    
    private String mMaxID = "0";
    private String mSinceID = "0";
    private boolean firstRefresh = true;
    
    private IWeiboContent mServer;
    private List<Weibo> mDataList;
    private HashMap<String, String> mQueryMap;
    private ILoader mLoadListner;
    
    public ContentManager(Context context, List<Weibo> dataList) {
        this.mDataList = dataList;
        RetrofitCenter imp = new RetrofitCenter();
        this.mServer = imp.getWeiboContent();
        initQueryMap(context);
    }
    
    public void setLoadListner(ILoader loadListner) {
        mLoadListner = loadListner;
    }
    
    public void initQueryMap(Context context) {
        mQueryMap = new HashMap<>();
        App app = (App) context.getApplicationContext();
        String token = app.getAccessToken().getToken();
        mQueryMap.put(ContentManager.kEY_ACCESS_TOKEN, token);
    }
    
    public void loadMore() {
        mQueryMap.put(ContentManager.kEY_COUNT, mLoadPageSize);
        mQueryMap.put(ContentManager.kEY_PAGE, mPageIndex);
        mQueryMap.put(ContentManager.KEY_MAX_ID, mMaxID);
        mServer.listLastWeibo(mQueryMap)
                .subscribeOn(Schedulers.io())
                .map(new Function<ContentsResponse, ContentsResponse>() {
                    @Override
                    public ContentsResponse apply(@NonNull ContentsResponse contentResponse) throws Exception {
                        WeiboComparator comparator = new WeiboComparator();
                        Collections.sort(contentResponse.getStatuses(), comparator);
                        return contentResponse;
                    }
                })
                .flatMap(new Function<ContentsResponse, ObservableSource<Weibo>>() {
                    @Override
                    public ObservableSource<Weibo> apply(@NonNull ContentsResponse contentResponse) throws Exception {
                        List<Weibo> weiboList = contentResponse.getStatuses();
                        if (weiboList.size() > 0) {
                            updateMaxId(weiboList.get(weiboList.size() - 1).getIdstr());
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
                        mLoadListner.start();
                    }
                    
                    @Override
                    public void onNext(Weibo weibo) {
                        size++;
                        mDataList.add(weibo);
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        mLoadListner.error(e);
                    }
                    
                    @Override
                    public void onComplete() {
                        mLoadListner.complete(size);
                    }
                });
    }
    
    /**
     * 获取最新数据
     *
     * @return 新增数据数量
     */
    public void refresh() {
        mQueryMap.put(ContentManager.kEY_COUNT, mRefreshPageSize);
        mQueryMap.put(ContentManager.kEY_PAGE, mPageIndex);
        mQueryMap.put(ContentManager.KEY_SINCE_ID, mSinceID);
        mServer.listLastWeibo(mQueryMap)
                .subscribeOn(Schedulers.io())
                .map(new Function<ContentsResponse, ContentsResponse>() {
                    @Override
                    public ContentsResponse apply(@NonNull ContentsResponse contentResponse) throws Exception {
                        WeiboComparator comparator = new WeiboComparator();
                        Collections.sort(contentResponse.getStatuses(), comparator);
                        return contentResponse;
                    }
                })
                .flatMap(new Function<ContentsResponse, ObservableSource<Weibo>>() {
                    @Override
                    public ObservableSource<Weibo> apply(@NonNull ContentsResponse contentResponse) throws Exception {
                        List<Weibo> weiboList = contentResponse.getStatuses();
                        if (weiboList.size() > 0) {
                            updateSinceId(weiboList.get(0).getIdstr());
                            if (isFirstRefresh()) {
                                updateMaxId(weiboList.get(weiboList.size() - 1).getIdstr());
                            }
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
                        mLoadListner.start();
                    }
                    
                    @Override
                    public void onNext(Weibo weibo) {
                        //保证最新在最前面
                        mDataList.add(0, weibo);
                        size++;
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        mLoadListner.error(e);
                    }
                    
                    @Override
                    public void onComplete() {
                        mLoadListner.complete(size);
                    }
                });
        firstRefresh = false;
    }
    
    private boolean isFirstRefresh() {
        return firstRefresh;
    }
    
    private void updateSinceId(String newSinceId) {
        this.mSinceID = newSinceId;
    }
    
    private void updateMaxId(String newMaxId) {
        this.mMaxID = newMaxId;
    }
    
    @Override
    protected void store() {
        
    }
    
    @Override
    protected void restore() {
        
    }
}
