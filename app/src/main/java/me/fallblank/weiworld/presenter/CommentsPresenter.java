package me.fallblank.weiworld.presenter;

import android.content.Context;

import com.sina.weibo.sdk.utils.MD5;

import java.util.List;

import me.fallblank.weiworld.bean.Comment;
import me.fallblank.weiworld.biz.ILoader;
import me.fallblank.weiworld.iview.ILoadView;
import me.fallblank.weiworld.iview.IView;
import me.fallblank.weiworld.model.CommentsManager;

import static android.R.attr.id;

/**
 * Created by fallb on 2017/4/20.
 */

public class CommentsPresenter extends BasePresenter implements ILoader {

    private CommentsManager mCommentsManager;
    private List<Comment> mDataList;
    private ILoadView mLoadListner;

    public CommentsPresenter(Context context, IView view, List<Comment> dataList, long id) {
        super(context, view);
        this.mCommentsManager = new CommentsManager(mContext, dataList, id, this);
        this.mDataList = dataList;

    }

    public void setListener(ILoadView listener){
        this.mLoadListner = listener;
    }

    public void load() {
        mCommentsManager.load();
    }

    @Override
    public void start() {
        mLoadListner.onStartLoad();
    }

    @Override
    public void complete(int size) {
        mLoadListner.onLoadComplete();
    }

    @Override
    public void error(Throwable throwable) {

    }
}
