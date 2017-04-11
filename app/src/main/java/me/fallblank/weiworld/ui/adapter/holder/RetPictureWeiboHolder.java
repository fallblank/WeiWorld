package me.fallblank.weiworld.ui.adapter.holder;

import android.view.View;

import java.util.List;

import me.fallblank.weiworld.bean.Weibo;

/**
 * Created by fallb on 2017/4/10.
 */

public class RetPictureWeiboHolder extends RetWeiboHolder {

    private PictureGallery mGallery;

    public RetPictureWeiboHolder(View itemView) {
        super(itemView);
        mGallery = new PictureGallery(itemView, mRetweetOption);
    }

    public void setPictureList(List<Weibo.PicUrlsBean> picList) {
        mGallery.setPictureList(picList);
    }

}
