package me.fallblank.weiworld.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Comment;
import me.fallblank.weiworld.biz.ILoader;
import me.fallblank.weiworld.model.CommentManager;
import me.fallblank.weiworld.ui.adapter.CommentAdapter;
import me.fallblank.weiworld.ui.adapter.LoadMoreWrapperAdapter;
import me.fallblank.weiworld.ui.adapter.util.CardItemDecoration;
import me.fallblank.weiworld.util.LogUtil;
import me.fallblank.weiworld.util.ToastUtil;

/**
 * Created by fallb on 2017/5/3.
 */

public class CommentFragment extends BaseFragment {
    
    @BindView(R.id.sr_refresh)
    SwipeRefreshLayout mRefreshView;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    
    RecyclerView.LayoutManager mLayoutManager;
    
    CommentAdapter mAdapter;
    LoadMoreWrapperAdapter mWrapperAdapter;
    
    List<Comment> mCommentList = new ArrayList<>();
    
    CommentManager mCommentManager;
    
    
    public static CommentFragment newInstance() {
        CommentFragment fragment = new CommentFragment();
        return fragment;
    }
    
    @Override
    protected int setLayout() {
        return R.layout.fragment_comment;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        int spanWidth = this.getResources().getDimensionPixelSize(R.dimen.card_span_size);
        CardItemDecoration decoration = new CardItemDecoration(spanWidth);
        mCommentManager = new CommentManager(mContext, mCommentList);
        mRecyclerView.addItemDecoration(decoration);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mAdapter = new CommentAdapter(mContext, mCommentList);
        mWrapperAdapter = new LoadMoreWrapperAdapter(mAdapter, R.layout.item_recycler_bottom_view);
        mWrapperAdapter.setBottomListener(new LoadMoreWrapperAdapter.OnBottomListener() {
            @Override
            public void onBottom() {
                mCommentManager.loadMore();
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mWrapperAdapter);
        mCommentManager.setRefreshListener(new ILoader() {
            @Override
            public void start() {
                if (!mRefreshView.isRefreshing()) {
                    mRefreshView.setRefreshing(true);
                }
            }
            
            @Override
            public void complete(int size) {
                mRefreshView.setRefreshing(false);
                ToastUtil.show(mContext, "新增数据：" + size + "条");
                mWrapperAdapter.selfNotifyDataSetChanged();
            }
            
            @Override
            public void error(Throwable throwable) {
                mRefreshView.setRefreshing(false);
                LogUtil.d("comment 刷新失败", throwable);
            }
        });
        mCommentManager.setLoadMoreListener(new ILoader() {
            @Override
            public void start() {
                
            }
            
            @Override
            public void complete(int size) {
                ToastUtil.show(mContext, "新增数据：" + size + "条");
                mWrapperAdapter.selfNotifyDataSetChanged();
            }
            
            @Override
            public void error(Throwable throwable) {
                LogUtil.d("comment 加载更多失败", throwable);
                mWrapperAdapter.setHideBottomView(true);
            }
        });
        mRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCommentManager.refresh();
            }
        });
        mCommentManager.refresh();
    }
}
