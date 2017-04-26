package me.fallblank.weiworld.ui.adapter.holder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.ui.adapter.PictureAdapter;
import me.fallblank.weiworld.ui.adapter.util.PicItemDecoration;

/**
 * Created by fallb on 2017/4/11.
 */

public class PictureGallery {

    @BindView(R.id.rv_list)
    RecyclerView mPictureList;
    private GridLayoutManager mLayoutManager;
    private PictureAdapter mAdapter;
    private Context mContext;

    public PictureGallery(View itemView, ViewGroup parent) {
        mContext = itemView.getContext();

        LayoutInflater.from(mContext)
                .inflate(R.layout.view_recycler, parent, true);
        ButterKnife.bind(this, itemView);
        init();
    }

    public void setPictureList(List<Weibo.PicUrlsBean> picList) {
        mLayoutManager = new GridLayoutManager(mContext, getPictureGallerySize(picList));
        mPictureList.setLayoutManager(mLayoutManager);
        mAdapter = new PictureAdapter(mContext, picList);
        mPictureList.setAdapter(mAdapter);
    }

    /**
     * 初始化RecyclerView，并绑定监听事件
     */
    private void init() {
        int spanWidth = mContext.getResources().getDimensionPixelSize(R.dimen.picture_span_size);
        RecyclerView.ItemDecoration itemDecoration = new PicItemDecoration(spanWidth);
        mPictureList.addItemDecoration(itemDecoration);
        // TODO: 2017/4/11 Recyclerview的点击事件
    }
    
    public static int getPictureGallerySize(List<?> list) {
        if (list != null) {
            switch (list.size()) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 2;
                default:
                    return 3;
            }
        } else {
            return 3;
        }
    }
    
}
