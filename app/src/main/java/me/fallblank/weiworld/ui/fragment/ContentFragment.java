package me.fallblank.weiworld.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.iview.IRefreshView;
import me.fallblank.weiworld.presenter.ContentsPresenter;
import me.fallblank.weiworld.ui.WeiboDetailActivity;
import me.fallblank.weiworld.ui.adapter.BaseAdapter;
import me.fallblank.weiworld.ui.adapter.ContentAdapter;
import me.fallblank.weiworld.ui.adapter.util.CardItemDecoration;
import me.fallblank.weiworld.util.LogUtil;
import me.fallblank.weiworld.util.ToastUtil;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.s;

/**
 * Created by fallb on 2017/4/20.
 */

public class ContentFragment extends BaseFragment implements IRefreshView {

    @BindView(R.id.rv_list)
    RecyclerView mContentList;

    private RecyclerView.LayoutManager mLayoutManager;
    private ContentAdapter mContentAdapter;
    private List<Weibo> mDataList;
    private ContentsPresenter mContentPresenter;

    public static ContentFragment newInstance(){
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
        View root = super.onCreateView(inflater,container,savedInstanceState);
        init();
        return root;
    }

    private void init() {
        mDataList = new ArrayList<>();

        mContentAdapter = new ContentAdapter(mContext, mDataList);
        mLayoutManager = new GridLayoutManager(mContext, 2, GridLayoutManager.HORIZONTAL, false);

        int spanWidth = this.getResources().getDimensionPixelSize(R.dimen.card_span_size);
        RecyclerView.ItemDecoration itemDecoration = new CardItemDecoration(spanWidth);
        mContentList.addItemDecoration(itemDecoration);
        mContentList.setLayoutManager(mLayoutManager);
        mContentList.setAdapter(mContentAdapter);
        mContentList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private final int FORWARD = 0x0001;
            private final int BEHIND = 0x0010;

            private int oritation;
            private boolean endpoint = false;

            private boolean setting = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_DRAGGING == newState && endpoint) {
                    if (BEHIND == oritation) {
                        LogUtil.d("到底了");
                        mContentPresenter.loadMore();
                    }
                    if (FORWARD == oritation) {
                        LogUtil.d("到顶了");
                        mContentPresenter.refresh();
                    }
                }
                if (RecyclerView.SCROLL_STATE_SETTLING == newState) {
                    setting = true;
                }
                if (RecyclerView.SCROLL_STATE_IDLE == newState && setting) {
                    endpoint = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx > 0) {
                    oritation = BEHIND;
                    endpoint = false;
                    setting = false;
                } else if (dx == 0) {
                    endpoint = true;
                } else {
                    oritation = FORWARD;
                    endpoint = false;
                    setting = false;
                }
            }
        });

        mContentAdapter.setOnItemClickListener(new BaseAdapter.OnRecyclerItemClickListener<Weibo>() {
            @Override
            public void onItemClicked(View item, Weibo data) {
                Intent intent = new Intent(getActivity(),
                        WeiboDetailActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra(WeiboDetailActivity.EXTRA_WEIBO, data);
                startActivity(intent);
            }
        });
        mContentPresenter = new ContentsPresenter(mContext, mDataList, this);
        mContentPresenter.refresh();
    }

    @Override
    public void refreshStart() {
        ToastUtil.show(mContext, "刷新开始");
    }

    @Override
    public void refreshSuccess(int size) {
        ToastUtil.show(mContext, "刷新成功,新增加数据：" + size);
        mContentAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshFail(Throwable throwable) {
        ToastUtil.show(mContext, "刷新失败");
    }
}
