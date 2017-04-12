package me.fallblank.weiworld.ui.text;

import android.text.SpannableStringBuilder;

/**
 * Created by fallb on 2017/4/12.
 */

public class BaseText extends SpannableStringBuilder {
    public BaseText(CharSequence text) {
        super(text);
    }

    protected char[] content() {
        int length = length();
        char[] chars = new char[length];
        getChars(0, length, chars, 0);
        return chars;
    }

    protected void setSpan(Object obj, int start, int end) {
        setSpan(obj, start, end, SPAN_EXCLUSIVE_EXCLUSIVE);
    }

}
