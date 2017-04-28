package me.fallblank.weiworld.ui.adapter.holder.weibo;

import android.view.View;

import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.ui.adapter.holder.PictureGallery;

/**
 * Created by fallb on 2017/4/25.
 */

public class OriginWeiboHolder extends BaseWeiboHolder {
    
    private PictureGallery mGallery;
    
    public OriginWeiboHolder(View itemView) {
        super(itemView);
        mGallery = new PictureGallery(itemView, mWeiboOption);
    }
    
    @Override
    public void setContent(Weibo weibo) {
        super.setContent(weibo);
        if (weibo.isContainPic()) {
            mWeiboOption.setVisibility(View.VISIBLE);
            mGallery.setPictureList(weibo);
        } else {
            mWeiboOption.setVisibility(View.GONE);
        }
        
    }
}
