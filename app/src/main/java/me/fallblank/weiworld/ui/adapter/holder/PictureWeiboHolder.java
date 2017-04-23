package me.fallblank.weiworld.ui.adapter.holder;

import android.view.View;

import me.fallblank.weiworld.bean.Weibo;


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

    @Override
    public void setContent(Weibo weibo) {
        super.setContent(weibo);
        mGallery.setPictureList(weibo.getPic_urls());
    }
}
