package me.fallblank.weiworld.ui.adapter.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by fallb on 2017/4/10.
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private int mWidth;

    public GridItemDecoration(int width) {
        this.mWidth = width;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = mWidth;
        outRect.bottom = mWidth;
        outRect.left = mWidth;
        outRect.right = mWidth;
        int position = parent.getChildLayoutPosition(view);
        if (position % 3 == 0) {
            outRect.left = 0;
        }
        if (position % 3 == 2) {
            outRect.right = 0;
        }
    }
}
