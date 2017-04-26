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
    
    private int mWidth;

    public PictureHolder(View itemView) {
        super(itemView);
        App app = (App) mContext.getApplicationContext();
        this.mWidth = app.getScreenWidthNoSpcae();
    }

    public void setPicture(String picUrl,int size) {
        int width = mWidth/size;
        mWeiboPicture.setMaxWidth(width);
        mWeiboPicture.setMinimumWidth(width);
        mWeiboPicture.setMaxHeight(width);
        mWeiboPicture.setMinimumHeight(width);
        
        mWeiboPicture.setImageURI(picUrl);
    }
}
