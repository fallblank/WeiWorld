package me.fallblank.weiworld.ui.text;

import android.support.annotation.ColorInt;
import android.text.TextPaint;
import android.text.style.ClickableSpan;

/**
 * Created by fallb on 2017/4/13.
 */

public abstract class InterativeClickableSpan extends ClickableSpan {

    private int foregroundColor;

    public InterativeClickableSpan(@ColorInt int color) {
        super();
        foregroundColor = color;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
        ds.setColor(foregroundColor);
    }
}
