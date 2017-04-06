package me.fallblank.weiworld.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.ui.adapter.ContentAdapter;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.presenter.ContentPresenter;
import me.fallblank.weiworld.util.ToastUtil;
import me.fallblank.weiworld.view.IRefreshView;

public class ContentFragment extends Fragment implements IRefreshView,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.sr_refresh)
    SwipeRefreshLayout mRefresh;

    @BindView(R.id.rv_content_list)
    RecyclerView mContentList;

    private RecyclerView.LayoutManager mLayoutManager;
    private ContentAdapter mContentAdapter;
    private List<Weibo> mDataList;
    private ContentPresenter mContentPresenter;

    public ContentFragment() {
        // Required empty public constructor
    }

    public static ContentFragment newInstance() {
        ContentFragment fragment = new ContentFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_content,container,false);
        ButterKnife.bind(this,root);
        init();
        return root;
    }

    //完成对RecycleView、SwipeRefreshLayout和ContentPresenter的初始化操作
    private void init(){
        if (null == mContentList || null == mRefresh){
            throw new RuntimeException("Recycle view didn't bind");
        }
        Context context = getActivity().getApplicationContext();
        mDataList = new ArrayList<>();

        mContentAdapter = new ContentAdapter(context,mDataList);
        mLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        mContentList.setLayoutManager(mLayoutManager);
        mContentList.setAdapter(mContentAdapter);
        /**
         * 故意留白，待实现下拉刷新，需要处理RecycleView的事件
         */
        mRefresh.setColorSchemeResources(
                R.color.color_schema_first,
                R.color.color_schema_second,
                R.color.color_schema_third);
        mRefresh.setOnRefreshListener(this);

        mContentPresenter = new ContentPresenter(context,mDataList,this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mContentPresenter.refresh();
    }

    @Override
    public void refreshStart() {
        mRefresh.setRefreshing(true);
        ToastUtil.show(getActivity().getApplicationContext(),"Reflesh start");
    }

    @Override
    public void refreshSuccess(int size) {
        mRefresh.setRefreshing(false);
        mContentAdapter.notifyDataSetChanged();
        ToastUtil.show(getActivity().getApplicationContext(),"Refresh successfully");
    }

    @Override
    public void refreshFail(Throwable throwable) {
        mRefresh.setRefreshing(false);
        ToastUtil.show(getActivity().getApplicationContext(),"Refresh failed");
    }

    @Override
    public void onRefresh() {
        mContentPresenter.refresh();
    }
}
