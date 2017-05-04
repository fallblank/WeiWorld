package me.fallblank.weiworld.model;

import android.content.Context;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import me.fallblank.weiworld.biz.retrofit.IPublicTimeline;
import me.fallblank.weiworld.impl.retrofit.RetrofitCenter;

/**
 * Created by fallb on 2017/5/2.
 */

public class PublicManager extends BaseModel {
    
    private List<Weibo> mDataList;
    private Map<String, String> mQueryMap = new HashMap<>();
    private IPublicTimeline mPublicTimeline = new RetrofitCenter().getPublicTimeline();
    private ILoader mRefreshListener;
    private ILoader mLoadMoreListener;
    
    public PublicManager(Context context, List<Weibo> list) {
        super(context);
        this.mDataList = list;
        App app = (App) context.getApplicationContext();
        String accessToken = app.getAccessToken().getToken();
        mQueryMap.put("access_token", accessToken);
        mQueryMap.put("count", "20");
    }
    
    public void refresh() {
        mPublicTimeline.getPublicContent(mQueryMap)
                .subscribeOn(Schedulers.io())
                .map(new Function<ContentResponse, List<Weibo>>() {
                    @Override
                    public List<Weibo> apply(@NonNull ContentResponse contentResponse) throws Exception {
                        List<Weibo> statuses = contentResponse.getStatuses();
                        if (statuses != null && statuses.size() != 0) {
                            WeiboComparator comparator = new WeiboComparator();
                            Collections.sort(statuses, comparator);
                        }
                        return statuses;
                    }
                })
                .flatMap(new Function<List<Weibo>, ObservableSource<Weibo>>() {
                    @Override
                    public ObservableSource<Weibo> apply(@NonNull List<Weibo> list) throws Exception {
                        return Observable.fromIterable(list);
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Weibo>() {
                    private int size = 0;
                    
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mRefreshListener != null) {
                            mRefreshListener.start();
                        }
                    }
                    
                    @Override
                    public void onNext(Weibo weibo) {
                        mDataList.add(0, weibo);
                        size++;
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        if (mRefreshListener != null) {
                            mRefreshListener.error(e);
                        }
                    }
                    
                    @Override
                    public void onComplete() {
                        if (mRefreshListener != null) {
                            mRefreshListener.complete(size);
                        }
                    }
                });
    }
    
    public void loadMore() {
        mPublicTimeline.getPublicContent(mQueryMap)
                .subscribeOn(Schedulers.io())
                .map(new Function<ContentResponse, List<Weibo>>() {
                    @Override
                    public List<Weibo> apply(@NonNull ContentResponse contentResponse) throws Exception {
                        List<Weibo> statuses = contentResponse.getStatuses();
                        if (statuses != null && statuses.size() != 0) {
                            WeiboReverseComparator comparator = new WeiboReverseComparator();
                            Collections.sort(statuses, comparator);
                        }
                        return statuses;
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Weibo>>() {
                    private int size = 0;
                    
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mLoadMoreListener != null) {
                            mLoadMoreListener.start();
                        }
                    }
                    
                    @Override
                    public void onNext(List<Weibo> list) {
                        mDataList.addAll(list);
                        size = list.size();
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        if (mLoadMoreListener != null) {
                            mLoadMoreListener.error(e);
                        }
                    }
                    
                    @Override
                    public void onComplete() {
                        if (mLoadMoreListener != null) {
                            mLoadMoreListener.complete(size);
                        }
                    }
                });
    }
    
    public void setRefreshListener(ILoader refreshListener) {
        mRefreshListener = refreshListener;
    }
    
    public void setLoadMoreListener(ILoader loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }
    
    @Override
    protected void store() {
        
    }
    
    @Override
    protected void restore() {
        
    }
}
