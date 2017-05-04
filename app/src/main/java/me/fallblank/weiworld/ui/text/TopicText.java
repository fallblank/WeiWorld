package me.fallblank.weiworld.ui.text;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.fallblank.weiworld.ui.PostActivity;

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
            final String topic = toPicMatcher.group();
            InterativeClickableSpan clickSpan = new InterativeClickableSpan(mForegroundColor) {
                @Override
                public void onClick(View widget) {
                    Context context = widget.getContext();
                    Intent intent = new Intent(context, PostActivity.class);
                    intent.putExtra(PostActivity.EXTRA_CONTENT, topic);
                    context.startActivity(intent);
                }
            };
            setSpan(clickSpan, start, end);
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
