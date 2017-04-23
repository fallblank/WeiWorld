package me.fallblank.weiworld.presenter;

import android.content.Context;

import java.util.List;

import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.biz.ILoader;
import me.fallblank.weiworld.model.ContentsManager;
import me.fallblank.weiworld.util.LogUtil;
import me.fallblank.weiworld.iview.IRefreshView;

/**
 * Created by fallb on 2017/3/30.
 */

public class ContentsPresenter extends BasePresenter<IRefreshView> implements ILoader{
    private List<Weibo> mDataList;
    private ContentsManager mContentManager;
    private IRefreshView mRefreshViewListener;

    public ContentsPresenter(Context context, List<Weibo> dataList, IRefreshView view) {
        super(context, view);
        this.mDataList = dataList;
        this.mRefreshViewListener = view;
        this.mContentManager = new ContentsManager(context,mDataList,this);

    }

    public void refresh(){
        mContentManager.refresh();
    }

    public void loadMore(){
        mContentManager.loadMore();
    }

    @Override
    public void error(Throwable throwable) {
        mRefreshViewListener.refreshFail(throwable);
        LogUtil.d("error",throwable);
    }

    @Override
    public void complete(int size) {
        mRefreshViewListener.refreshSuccess(size);
        LogUtil.d("refresh complete,size="+size);
    }

    @Override
    public void start() {
        mRefreshViewListener.refreshStart();
        LogUtil.d("start");
    }
}
