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
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.biz.ILoader;
import me.fallblank.weiworld.model.PublicManager;
import me.fallblank.weiworld.ui.adapter.ContentAdapter;
import me.fallblank.weiworld.ui.adapter.LoadMoreWrapperAdapter;
import me.fallblank.weiworld.util.LogUtil;
import me.fallblank.weiworld.util.ToastUtil;

public class PublicFragment extends BaseFragment {
    
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    
    @BindView(R.id.sr_refresh)
    SwipeRefreshLayout mRefreshView;
    
    List<Weibo> mDataList = new ArrayList<>();
    RecyclerView.Adapter mAdapter;
    LoadMoreWrapperAdapter mWrapperAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    PublicManager mPublicManager;
    
    
    public static PublicFragment newInstance() {
        PublicFragment fragment = new PublicFragment();
        return fragment;
    }
    
    public PublicFragment() {
    }
    
    
    @Override
    protected int setLayout() {
        return R.layout.fragment_public;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPublicManager = new PublicManager(mContext, mDataList);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mAdapter = new ContentAdapter(mContext, getFragmentManager(), mDataList);
        mWrapperAdapter = new LoadMoreWrapperAdapter(mAdapter, R.layout.item_recycler_bottom_view);
        mWrapperAdapter.setBottomListener(new LoadMoreWrapperAdapter.OnBottomListener() {
            @Override
            public void onBottom() {
                mPublicManager.loadMore();
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mWrapperAdapter);
        mPublicManager.setRefreshListener(new ILoader() {
            @Override
            public void start() {
                if (!mRefreshView.isRefreshing()) {
                    mRefreshView.setRefreshing(true);
                }
            }
            
            @Override
            public void complete(int size) {
                if (size != 0) {
                    ToastUtil.show(mContext, "加载数据：" + size + "条");
                    mWrapperAdapter.selfNotifyDataSetChanged();
                }
                mRefreshView.setRefreshing(false);
            }
            
            @Override
            public void error(Throwable throwable) {
                LogUtil.d("public_timeline", throwable);
                mRefreshView.setRefreshing(false);
                mWrapperAdapter.setHideBottomView(true);
            }
        });
        mPublicManager.setLoadMoreListener(new ILoader() {
            @Override
            public void start() {
                
            }
            
            @Override
            public void complete(int size) {
                if (size != 0) {
                    ToastUtil.show(mContext, "加载数据：" + size + "条");
                    mWrapperAdapter.selfNotifyDataSetChanged();
                }
            }
            
            @Override
            public void error(Throwable throwable) {
                LogUtil.d("public_timeline", throwable);
                mWrapperAdapter.setHideBottomView(true);
            }
        });
        mRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPublicManager.refresh();
            }
        });
        mPublicManager.refresh();
    }
}
