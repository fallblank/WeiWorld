package me.fallblank.weiworld.ui.adapter.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static android.R.attr.width;

/**
 * Created by fallb on 2017/4/10.
 */

public class PicItemDecoration extends BaseItemDecoration {

    public PicItemDecoration(int span) {
        super(span);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = mSpan;
        outRect.bottom = mSpan;
        outRect.left = mSpan;
        outRect.right = mSpan;
        int position = parent.getChildLayoutPosition(view);
        if (position % 3 == 0) {
            outRect.left = 0;
        }
        if (position % 3 == 2) {
            outRect.right = 0;
        }
    }
}
