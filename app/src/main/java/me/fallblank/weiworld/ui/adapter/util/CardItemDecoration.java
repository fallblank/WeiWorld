package me.fallblank.weiworld.ui.adapter.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by fallb on 2017/4/19.
 */

public class CardItemDecoration extends BaseItemDecoration {

    public CardItemDecoration(int span) {
        super(span);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = mSpan;
        outRect.right = mSpan;
        outRect.top = mSpan;
        outRect.left = mSpan;
    }
}
