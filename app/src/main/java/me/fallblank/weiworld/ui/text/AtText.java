package me.fallblank.weiworld.ui.text;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.text.style.ClickableSpan;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.fallblank.weiworld.ui.PostActivity;

/**
 * Created by fallb on 2017/4/12.
 */

public class AtText extends BaseText {
    private int mForegroundColor = Color.BLUE;
    
    private final String mRegex = "(@)(\\w+)(\\s|$|:)";
    
    public AtText(BaseText builder) {
        super(builder);
        parse();
    }
    
    private void parse() {
        final String content = new String(content());
        Pattern atPattern = Pattern.compile(mRegex);
        Matcher atMatcher = atPattern.matcher(content);
        while (atMatcher.find()) {
            int start = atMatcher.start();
            int end = atMatcher.end(2);
            final String user = atMatcher.group(2);
            ClickableSpan clickSpan = new InterativeClickableSpan(mForegroundColor) {
                @Override
                public void onClick(View widget) {
                    Context context = widget.getContext();
                    Intent intent = new Intent(context, PostActivity.class);
                    intent.putExtra(PostActivity.EXTRA_CONTENT, "@" + user);
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
