package me.fallblank.weiworld.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Favorite;
import me.fallblank.weiworld.biz.ILoader;
import me.fallblank.weiworld.model.FavoritaManager;
import me.fallblank.weiworld.ui.WeiboDetailActivity;
import me.fallblank.weiworld.ui.adapter.BaseAdapter;
import me.fallblank.weiworld.ui.adapter.FavoriteAdapter;
import me.fallblank.weiworld.ui.adapter.util.CardItemDecoration;
import me.fallblank.weiworld.util.ToastUtil;

/**
 * Created by fallb on 2017/4/25.
 */

public class FavoriteFragment extends BaseFragment {
    
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    
    RecyclerView.LayoutManager mLayoutManager;
    FavoriteAdapter mAdapter;
    private FavoritaManager mFavoritaManager;
    private List<Favorite.FavoritesBean> mDataList = new ArrayList<>();
    
    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }
    
    @Override
    protected int setLayout() {
        return R.layout.fragment_favorite;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return root;
    }
    
    private void init() {
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mAdapter = new FavoriteAdapter(mContext, mDataList);
        mAdapter.setFManager(getActivity().getSupportFragmentManager());
        mAdapter.setOnItemClickListener(new BaseAdapter.OnRecyclerItemClickListener<Favorite.FavoritesBean>() {
            @Override
            public void onItemClicked(View item, int position, Favorite.FavoritesBean data) {
                Intent intent = new Intent(getActivity(),
                        WeiboDetailActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra(WeiboDetailActivity.EXTRA_WEIBO, data.getStatus());
                startActivity(intent);
            }
        });
        int spanWidth = this.getResources().getDimensionPixelSize(R.dimen.card_span_size);
        RecyclerView.ItemDecoration itemDecoration = new CardItemDecoration(spanWidth);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mFavoritaManager = new FavoritaManager(mContext, mDataList);
        mFavoritaManager.setLoadListener(new ILoader() {
            @Override
            public void start() {
                ToastUtil.show(mContext, "获取收藏数据中");
            }
            
            @Override
            public void complete(int size) {
                mAdapter.notifyDataSetChanged();
                ToastUtil.show(mContext, "获取：" + size + "条");
            }
            
            @Override
            public void error(Throwable throwable) {
                ToastUtil.show(mContext, "获取收藏数据出错");
            }
        });
        mFavoritaManager.load();
    }
}
