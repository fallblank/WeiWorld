package me.fallblank.weiworld.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.biz.ILoader;
import me.fallblank.weiworld.biz.OnNoMoreDataListener;
import me.fallblank.weiworld.model.BilateralManager;
import me.fallblank.weiworld.ui.WeiboDetailActivity;
import me.fallblank.weiworld.ui.adapter.BaseAdapter;
import me.fallblank.weiworld.ui.adapter.ContentAdapter;
import me.fallblank.weiworld.ui.adapter.LoadMoreWrapperAdapter;
import me.fallblank.weiworld.ui.adapter.util.CardItemDecoration;
import me.fallblank.weiworld.util.LogUtil;
import me.fallblank.weiworld.util.ToastUtil;

/**
 * Created by fallb on 2017/5/4.
 */

public class BilateralFragment extends BaseFragment {
    
    @BindView(R.id.sr_refresh)
    SwipeRefreshLayout mRefreshView;
    
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    
    private RecyclerView.LayoutManager mLayoutManager;
    private ContentAdapter mAdapter;
    private LoadMoreWrapperAdapter mWrapperAdapter;
    private List<Weibo> mWeiboList = new ArrayList<>();
    private BilateralManager mBilateralManager;
    
    public static BilateralFragment newInstance() {
        BilateralFragment fragment = new BilateralFragment();
        return fragment;
    }
    
    
    @Override
    protected int setLayout() {
        return R.layout.fragment_bilateral;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBilateralManager = new BilateralManager(mContext, mWeiboList);
        mAdapter = new ContentAdapter(mContext, getFragmentManager(), mWeiboList);
        mAdapter.setOnItemClickListener(new BaseAdapter.OnRecyclerItemClickListener<Weibo>() {
            @Override
            public void onItemClicked(View item, int position, Weibo data) {
                Intent intent = new Intent(getActivity(),
                        WeiboDetailActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra(WeiboDetailActivity.EXTRA_WEIBO, data);
                startActivity(intent);
            }
        });
        mWrapperAdapter = new LoadMoreWrapperAdapter(mAdapter, R.layout.item_recycler_bottom_view);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        int spanWidth = this.getResources().getDimensionPixelSize(R.dimen.card_span_size);
        CardItemDecoration itemDecoration = new CardItemDecoration(spanWidth);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mWrapperAdapter);
        mWrapperAdapter.setBottomListener(new LoadMoreWrapperAdapter.OnBottomListener() {
            @Override
            public void onBottom() {
                mBilateralManager.loadMore();
            }
        });
        mBilateralManager.setRefreshListener(new ILoader() {
            @Override
            public void start() {
                if (!mRefreshView.isRefreshing()) {
                    mRefreshView.setRefreshing(true);
                }
            }
            
            @Override
            public void complete(int size) {
                mRefreshView.setRefreshing(false);
                mWrapperAdapter.selfNotifyDataSetChanged();
                ToastUtil.show(mContext, "新增数据：" + size + "条");
            }
            
            @Override
            public void error(Throwable throwable) {
                mRefreshView.setRefreshing(false);
                LogUtil.d("mention refresh", throwable);
            }
        });
        mBilateralManager.setLoadMoreListener(new ILoader() {
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
                LogUtil.d("mention refresh", throwable);
                mWrapperAdapter.setHideBottomView(true);
            }
        });
        
        mBilateralManager.setNoMoreDataListener(new OnNoMoreDataListener() {
            @Override
            public void onNoMoreData() {
                mWrapperAdapter.setHideBottomView(true);
            }
        });
        mRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBilateralManager.refresh();
            }
        });
        mBilateralManager.refresh();
    }
}
