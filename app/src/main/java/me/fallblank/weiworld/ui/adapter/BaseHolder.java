package me.fallblank.weiworld.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by fallb on 2017/4/7.
 */

public abstract class BaseHolder extends RecyclerView.ViewHolder {
    public BaseHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
