package me.fallblank.weiworld.ui.adapter.holder;

import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import me.fallblank.weiworld.App;
import me.fallblank.weiworld.R;

/**
 * Created by fallb on 2017/4/10.
 */

public class PictureHolder extends BaseHolder {

    @BindView(R.id.iv_picture)
    SimpleDraweeView mWeiboPicture;

    public PictureHolder(View itemView) {
        super(itemView);
        App app = (App) mContext.getApplicationContext();
        int width = app.getScreenWidthNoSpcae();
        //保证图片是正方形
        mWeiboPicture.setMaxHeight(width / 3);
        mWeiboPicture.setMinimumHeight(width / 3);
    }

    public void setPicture(String picUrl) {
        mWeiboPicture.setImageURI(picUrl);
    }
}
