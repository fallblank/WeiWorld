package me.fallblank.weiworld.ui.adapter.holder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.ui.adapter.PictureAdapter;
import me.fallblank.weiworld.ui.adapter.util.GridItemDecoration;

/**
 * Created by fallb on 2017/4/11.
 */

public class PictureGallery {

    @BindView(R.id.rv_list)
    RecyclerView mPictureList;
    private GridLayoutManager mLayoutManager;
    private PictureAdapter mAdapter;
    private List<Weibo.PicUrlsBean> mPicList;
    private Context mContext;

    public PictureGallery(View itemView, ViewGroup parent) {
        mContext = itemView.getContext();

        LayoutInflater.from(mContext)
                .inflate(R.layout.view_recycler, parent, true);
        ButterKnife.bind(this, itemView);
        init();
    }

    public void setPictureList(List<Weibo.PicUrlsBean> picList) {
        mPicList = picList;
        mAdapter = new PictureAdapter(mContext, picList);
        mPictureList.setAdapter(mAdapter);
    }

    /**
     * 初始化RecyclerView，并绑定监听事件
     */
    private void init() {
        mLayoutManager = new GridLayoutManager(mContext, 3);
        int spanWidth = mContext.getResources().getDimensionPixelSize(R.dimen.picture_span_size);
        RecyclerView.ItemDecoration itemDecoration = new GridItemDecoration(spanWidth);
        mPictureList.addItemDecoration(itemDecoration);
        mPictureList.setLayoutManager(mLayoutManager);

        // TODO: 2017/4/11 Recyclerview的点击事件
    }

}
