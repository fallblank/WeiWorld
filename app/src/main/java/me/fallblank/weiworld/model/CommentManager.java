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
import me.fallblank.weiworld.bean.Comment;
import me.fallblank.weiworld.bean.CommentComparator;
import me.fallblank.weiworld.bean.CommentResponse;
import me.fallblank.weiworld.bean.CommentReverseComparator;
import me.fallblank.weiworld.biz.ILoader;
import me.fallblank.weiworld.biz.retrofit.IComment;
import me.fallblank.weiworld.impl.retrofit.RetrofitCenter;

/**
 * Created by fallb on 2017/5/4.
 */

public class CommentManager extends BaseModel {
    
    private List<Comment> mCommentList;
    private IComment mIComment = new RetrofitCenter().getComment();
    
    //加载数据的回调接口
    private ILoader mRefreshListener;
    private ILoader mLoadMoreListener;
    
    //分页控制
    private long mSinceId = 0L;
    private long mMaxId = 0L;
    
    //请求参数封装
    private Map<String, String> mQueryMap = new HashMap<>();
    
    
    public CommentManager(Context context, List<Comment> dataList) {
        super(context);
        this.mCommentList = dataList;
        App app = (App) context.getApplicationContext();
        mQueryMap.put("access_token", app.getAccessToken().getToken());
    }
    
    
    public void refresh() {
        mQueryMap.remove("max_id");
        mQueryMap.put("since_id", String.valueOf(mSinceId));
        mIComment.getComment(mQueryMap)
                .subscribeOn(Schedulers.io())
                .map(new Function<CommentResponse, List<Comment>>() {
                    @Override
                    public List<Comment> apply(@NonNull CommentResponse commentResponse) throws Exception {
                        updateMaxId(commentResponse.getNext_cursor());
                        List<Comment> commentList = commentResponse.getComments();
                        if (commentList != null && commentList.size() != 0) {
                            Collections.sort(commentList, new CommentComparator());
                            updateSinceId(commentList.get(commentList.size() - 1).getId());
                        }
                        return commentList;
                    }
                })
                .flatMap(new Function<List<Comment>, ObservableSource<Comment>>() {
                    @Override
                    public ObservableSource<Comment> apply(@NonNull List<Comment> comments) throws Exception {
                        return Observable.fromIterable(comments);
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Comment>() {
                    int mSize = 0;
                    
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mRefreshListener != null) {
                            mRefreshListener.start();
                        }
                    }
                    
                    @Override
                    public void onNext(Comment comment) {
                        mCommentList.add(0, comment);
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
        mIComment.getComment(mQueryMap)
                .subscribeOn(Schedulers.io())
                .map(new Function<CommentResponse, List<Comment>>() {
                    @Override
                    public List<Comment> apply(@NonNull CommentResponse commentResponse) throws Exception {
                        updateMaxId(commentResponse.getNext_cursor());
                        Collections.sort(commentResponse.getComments(), new CommentReverseComparator());
                        return commentResponse.getComments();
                    }
                })
                .flatMap(new Function<List<Comment>, ObservableSource<Comment>>() {
                    @Override
                    public ObservableSource<Comment> apply(@NonNull List<Comment> comments) throws Exception {
                        return Observable.fromIterable(comments);
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Comment>() {
                    int mSize = 0;
                    
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mLoadMoreListener != null) {
                            mLoadMoreListener.start();
                        }
                    }
                    
                    @Override
                    public void onNext(Comment comment) {
                        mCommentList.add(comment);
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
        if (newSinceId > mSinceId) {
            mSinceId = newSinceId;
        }
    }
    
    private void updateMaxId(long newMaxId) {
        if (mMaxId == 0L) {
            mMaxId = newMaxId;
        }
        if (newMaxId < mMaxId) {
            mMaxId = newMaxId;
        }
    }
    
    
    public ILoader getRefreshListener() {
        return mRefreshListener;
    }
    
    public void setRefreshListener(ILoader refreshListener) {
        mRefreshListener = refreshListener;
    }
    
    public ILoader getLoadMoreListener() {
        return mLoadMoreListener;
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
