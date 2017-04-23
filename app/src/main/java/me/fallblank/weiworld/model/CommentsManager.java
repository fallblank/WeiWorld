package me.fallblank.weiworld.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.CommentsAPI;

import java.util.Collections;
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
import me.fallblank.weiworld.BuildConfig;
import me.fallblank.weiworld.bean.Comment;
import me.fallblank.weiworld.bean.CommentComparator;
import me.fallblank.weiworld.bean.CommentsResponse;
import me.fallblank.weiworld.biz.ILoader;
import me.fallblank.weiworld.util.LogUtil;
import me.fallblank.weiworld.util.TimeFormatter;

/**
 * Created by fallb on 2017/4/20.
 */

public class CommentsManager extends BaseModel implements RequestListener {


    /**
     * 评论获取，因为直接调接口得不到非授权用户的数据，所有改用weibo sdk封装的方法。
     */
    private CommentsAPI mCommentsAPI;

    private List<Comment> mCommentList;

    private long mSinceId = 0L;

    private long mMaxId = 0L;

    private int mCount = 5;
    private int mPage = 1;
    private int mAuthType = CommentsAPI.AUTHOR_FILTER_ALL;

    private long mWeiboId;
    private Gson mGson;
    private ILoader mLoadListener;


    public CommentsManager(Context context, List<Comment> dataList, long id, ILoader listener) {
        App app = (App) context.getApplicationContext();
        this.mCommentsAPI = new CommentsAPI(context, BuildConfig.APP_KEY, app.getAccessToken());
        this.mCommentList = dataList;
        this.mWeiboId = id;
        mGson = new GsonBuilder()
                .setDateFormat(TimeFormatter.TIME_FORMAT_PARTTERN)
                .create();
        this.mLoadListener = listener;
    }

    public void load() {
        mCommentsAPI.show(mWeiboId, mSinceId, mMaxId, mCount, mPage, mAuthType, this);
    }

    @Override
    protected void store() {

    }

    @Override
    protected void restore() {

    }

    @Override
    public void onComplete(String s) {
        Observable.just(s)
                .map(new Function<String, CommentsResponse>() {
                    @Override
                    public CommentsResponse apply(@NonNull String json) throws Exception {
                        return mGson.fromJson(json, CommentsResponse.class);
                    }
                })
                .map(new Function<CommentsResponse, CommentsResponse>() {
                    @Override
                    public CommentsResponse apply(@NonNull CommentsResponse commentsResponse) throws Exception {
                        CommentComparator comparator = new CommentComparator();
                        Collections.sort(commentsResponse.getComments(), comparator);
                        return commentsResponse;
                    }
                })
                .flatMap(new Function<CommentsResponse, ObservableSource<Comment>>() {
                    @Override
                    public ObservableSource<Comment> apply(@NonNull CommentsResponse commentsResponse) throws Exception {
                        List comments = commentsResponse.getComments();
                        return Observable.fromIterable(comments);
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Comment>() {
                    int size = 0;

                    @Override
                    public void onSubscribe(Disposable d) {
                        mLoadListener.start();
                    }

                    @Override
                    public void onNext(Comment comment) {
                        mCommentList.add(comment);
                        size++;
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoadListener.error(e);
                    }

                    @Override
                    public void onComplete() {
                        mLoadListener.complete(size);
                    }
                });
    }

    @Override
    public void onWeiboException(WeiboException e) {
        LogUtil.d("error", e);
    }
}
