package me.fallblank.weiworld.ui.fragment;

import android.content.Intent;
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
import me.fallblank.weiworld.iview.IRefreshView;
import me.fallblank.weiworld.model.ContentManager;
import me.fallblank.weiworld.ui.WeiboDetailActivity;
import me.fallblank.weiworld.ui.adapter.BaseAdapter;
import me.fallblank.weiworld.ui.adapter.ContentAdapter;
import me.fallblank.weiworld.ui.adapter.LoadMoreWrapperAdapter;
import me.fallblank.weiworld.ui.adapter.util.CardItemDecoration;
import me.fallblank.weiworld.util.ToastUtil;

/**
 * Created by fallb on 2017/4/20.
 */

public class ContentFragment extends BaseFragment implements IRefreshView {
    
    @BindView(R.id.rv_list)
    RecyclerView mContentList;
    
    @BindView(R.id.sr_refresh)
    SwipeRefreshLayout mRefreshIndicator;
    
    private RecyclerView.LayoutManager mLayoutManager;
    private ContentAdapter mContentAdapter;
    private LoadMoreWrapperAdapter mWrapperAdapter;
    private List<Weibo> mDataList;
    
    private ContentManager mContentManager;
    
    public static ContentFragment newInstance() {
        ContentFragment fragment = new ContentFragment();
        // TODO: init bundle
        return fragment;
    }
    
    
    @Override
    public int setLayout() {
        return R.layout.fragment_content;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return root;
    }
    
    private void init() {
        mDataList = new ArrayList<>();
        mContentManager = new ContentManager(mContext, mDataList);
        mContentAdapter = new ContentAdapter(mContext, getActivity().getSupportFragmentManager(), mDataList);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        
        int spanWidth = this.getResources().getDimensionPixelSize(R.dimen.card_span_size);
        RecyclerView.ItemDecoration itemDecoration = new CardItemDecoration(spanWidth);
        mContentList.addItemDecoration(itemDecoration);
        mContentList.setLayoutManager(mLayoutManager);
        mContentAdapter.setOnItemClickListener(new BaseAdapter.OnRecyclerItemClickListener<Weibo>() {
            @Override
            public void onItemClicked(View item, int position, Weibo data) {
                Intent intent = new Intent(getActivity(),
                        WeiboDetailActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra(WeiboDetailActivity.EXTRA_WEIBO, data);
                startActivity(intent);
            }
        });
        mWrapperAdapter = new LoadMoreWrapperAdapter(mContentAdapter, R.layout.item_recycler_bottom_view);
        mWrapperAdapter.setBottomListener(new LoadMoreWrapperAdapter.OnBottomListener() {
            @Override
            public void onBottom() {
                mContentManager.loadMore();
            }
        });
        mContentList.setAdapter(mWrapperAdapter);
        mContentManager.setRefreshListner(new ILoader() {
            @Override
            public void start() {
                refreshStart();
            }
            
            @Override
            public void complete(int size) {
                refreshSuccess(size);
            }
            
            @Override
            public void error(Throwable throwable) {
                refreshFail(throwable);
            }
        });
        mContentManager.setLoadMoreListner(new ILoader() {
            @Override
            public void start() {
                //empty
            }
            
            @Override
            public void complete(int size) {
                ToastUtil.show(mContext, "新增加数据：" + size);
                if (size > 0) {
                    mWrapperAdapter.selfNotifyDataSetChanged();
                }
            }
            
            @Override
            public void error(Throwable throwable) {
                mWrapperAdapter.setHideBottomView(true);
            }
        });
        mContentManager.refresh();
        mRefreshIndicator.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mContentManager.refresh();
            }
        });
    }
    
    @Override
    public void refreshStart() {
        if (!mRefreshIndicator.isRefreshing()) {
            mRefreshIndicator.setRefreshing(true);
        }
    }
    
    @Override
    public void refreshSuccess(int size) {
        mRefreshIndicator.setRefreshing(false);
        if (size > 0) {
            ToastUtil.show(mContext, "刷新成功,新增加数据：" + size + "条");
            mWrapperAdapter.notifyDataSetChanged();
        }
    }
    
    @Override
    public void refreshFail(Throwable throwable) {
        mRefreshIndicator.setRefreshing(false);
    }
}
