package me.fallblank.weiworld.ui.adapter.holder;

import android.view.View;

import me.fallblank.weiworld.ui.adapter.holder.BaseWeiboHolder;

/**
 * Created by fallb on 2017/4/7.
 */

public class TextWeiboHolder extends BaseWeiboHolder {

    public TextWeiboHolder(View itemView) {
        super(itemView);
        //清除多余的布局元素
        mWeiboOption.setVisibility(View.GONE);
    }
}
