package me.fallblank.weiworld.ui.adapter.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by fallb on 2017/4/19.
 */

public abstract class BaseItemDecoration extends RecyclerView.ItemDecoration {
    protected int mSpan;

    public BaseItemDecoration(int span) {
        this.mSpan = span;
    }

    @Override
    public abstract void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state);
}
