package me.fallblank.weiworld.ui.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by fallb on 2017/4/7.
 */

public abstract class BaseHolder extends RecyclerView.ViewHolder {
    protected Context mContext;

    public BaseHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }



}
