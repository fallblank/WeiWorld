package me.fallblank.weiworld.ui.text;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fallb on 2017/4/12.
 */

public class TopicText extends BaseText {

    private final String mRegex = "(#)(\\w)+(#)";

    private int mForegroundColor = Color.BLUE;

    public TopicText(BaseText text) {
        super(text);
        parse();
    }

    private void parse() {
        String content = new String(content());
        Pattern topicPattern = Pattern.compile(mRegex);
        Matcher toPicMatcher = topicPattern.matcher(content);
        while (toPicMatcher.find()) {
            int start = toPicMatcher.start();
            int end = toPicMatcher.end();
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(mForegroundColor);
            setSpan(colorSpan, start, end);
        }
    }

    public void setForegroundColor(@ColorInt int foregroundColor) {
        mForegroundColor = foregroundColor;
    }

    public void setForegroundColor(@ColorRes int colorId, Context context) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            mForegroundColor = context.getColor(colorId);
        } else {
            mForegroundColor = context.getResources().getColor(colorId);
        }
    }

}
