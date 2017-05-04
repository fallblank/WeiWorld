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
import me.fallblank.weiworld.bean.BilateralResponse;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.bean.WeiboComparator;
import me.fallblank.weiworld.bean.WeiboReverseComparator;
import me.fallblank.weiworld.biz.ILoader;
import me.fallblank.weiworld.biz.OnNoMoreDataListener;
import me.fallblank.weiworld.biz.retrofit.IBilateral;
import me.fallblank.weiworld.impl.retrofit.RetrofitCenter;

/**
 * Created by fallb on 2017/5/4.
 */

public class BilateralManager extends BaseModel {
    private List<Weibo> mWeiboList;
    private long mSinceId = 0L;
    private long mMaxId = 0L;
    
    private Map<String, String> mQueryMap = new HashMap<>();
    private IBilateral mIBilateral = new RetrofitCenter().getBilateral();
    
    private ILoader mRefreshListener;
    private ILoader mLoadMoreListener;
    private OnNoMoreDataListener mNoMoreDataListener;
    
    public BilateralManager(Context context, List<Weibo> weiboList) {
        super(context);
        App app = (App) context.getApplicationContext();
        mQueryMap.put("access_token", app.getAccessToken().getToken());
        mWeiboList = weiboList;
    }
    
    public void refresh() {
        mQueryMap.remove("max_id");
        mQueryMap.put("since_id", String.valueOf(mSinceId));
        mIBilateral.getBilateral(mQueryMap)
                .subscribeOn(Schedulers.io())
                .map(new Function<BilateralResponse, List<Weibo>>() {
                    @Override
                    public List<Weibo> apply(@NonNull BilateralResponse bilateralResponse) throws Exception {
                        updateMaxId(bilateralResponse.getNext_cursor());
                        WeiboComparator comparator = new WeiboComparator();
                        Collections.sort(bilateralResponse.getStatuses(), comparator);
                        return bilateralResponse.getStatuses();
                    }
                })
                .flatMap(new Function<List<Weibo>, ObservableSource<Weibo>>() {
                    @Override
                    public ObservableSource<Weibo> apply(@NonNull List<Weibo> list) throws Exception {
                        if (list.size() > 0) {
                            updateSinceId(list.get(list.size() - 1).getId());
                        }
                        return Observable.fromIterable(list);
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Weibo>() {
                    private int mSize = 0;
                    
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mRefreshListener != null) {
                            mRefreshListener.start();
                        }
                        
                    }
                    
                    @Override
                    public void onNext(Weibo weibo) {
                        mWeiboList.add(0, weibo);
                        mSize++;
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
                            mRefreshListener.complete(mSize);
                        }
                    }
                });
    }
    
    public void loadMore() {
        mQueryMap.remove("since_id");
        mQueryMap.put("max_id", String.valueOf(mMaxId));
        mIBilateral.getBilateral(mQueryMap)
                .subscribeOn(Schedulers.io())
                .map(new Function<BilateralResponse, List<Weibo>>() {
                    @Override
                    public List<Weibo> apply(@NonNull BilateralResponse bilateralResponse) throws Exception {
                        WeiboReverseComparator comparator = new WeiboReverseComparator();
                        Collections.sort(bilateralResponse.getStatuses(), comparator);
                        updateMaxId(bilateralResponse.getNext_cursor());
                        return bilateralResponse.getStatuses();
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
                    private int mSize = 0;
                    
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mLoadMoreListener != null) {
                            mLoadMoreListener.start();
                        }
                    }
                    
                    @Override
                    public void onNext(Weibo weibo) {
                        mWeiboList.add(weibo);
                        mSize++;
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
                            mLoadMoreListener.complete(mSize);
                        }
                    }
                });
    }
    
    
    private void updateSinceId(long newSinceId) {
        if (mSinceId == 0L) {
            mSinceId = newSinceId;
        }
        mSinceId = newSinceId > mSinceId ? newSinceId : mSinceId;
    }
    
    private void updateMaxId(long newMaxId) {
        if (mMaxId == 0L) {
            mMaxId = newMaxId;
        }
        mMaxId = newMaxId < mMaxId ? newMaxId : mMaxId;
        if (newMaxId == 0L && mNoMoreDataListener != null) {
            mNoMoreDataListener.onNoMoreData();
        }
    }
    
    
    public void setRefreshListener(ILoader refreshListener) {
        mRefreshListener = refreshListener;
    }
    
    
    public void setLoadMoreListener(ILoader loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }
    
    public void setNoMoreDataListener(OnNoMoreDataListener noMoreDataListener) {
        mNoMoreDataListener = noMoreDataListener;
    }
    
    
    @Override
    protected void store() {
        
    }
    
    @Override
    protected void restore() {
        
    }
}
