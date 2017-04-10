package me.fallblank.weiworld.ui.adapter;

import android.view.View;

/**
 * Created by fallb on 2017/4/7.
 */

public class PlainTextWeiboHolder extends BaseWeiboHolder {

    public PlainTextWeiboHolder(View itemView) {
        super(itemView);
        //清除多余的布局元素
        mWeiboOption.setVisibility(View.GONE);
    }
}
