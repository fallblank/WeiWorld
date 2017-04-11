package me.fallblank.weiworld.ui.adapter.holder;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.ui.adapter.util.GridItemDecoration;
import me.fallblank.weiworld.ui.adapter.PictureAdapter;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.m;


/**
 * Created by fallb on 2017/4/7.
 */

public class PictureWeiboHolder extends BaseWeiboHolder {

    //处理9宫格图片显示
    private PictureGallery mGallery;

    public PictureWeiboHolder(View itemView) {
        super(itemView);
        mGallery = new PictureGallery(itemView, mWeiboOption);
    }

    public void setPictureList(List<Weibo.PicUrlsBean> picList) {
        mGallery.setPictureList(picList);
    }
}
