package me.fallblank.weiworld.ui.adapter.holder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.ui.adapter.holder.BaseWeiboHolder;
import me.fallblank.weiworld.ui.text.SpannableWeiboText;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.o;

/**
 * Created by fallb on 2017/4/7.
 */

public class RetWeiboHolder extends BaseWeiboHolder {

    @BindView(R.id.weibo_retweet_text)
    @Nullable
    protected TextView mRetweetText;

    @BindView(R.id.weibo_retweet_option)
    @Nullable
    protected FrameLayout mRetweetOption;

    public RetWeiboHolder(View itemView) {
        super(itemView);
        Context context = itemView.getContext();
        View retweet = LayoutInflater.from(context)
                .inflate(R.layout.item_retweet_weibo_content, mWeiboOption, true);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setContent(Weibo weibo) {
        super.setContent(weibo);
        mRetweetText.setMovementMethod(LinkMovementMethod.getInstance());

        Weibo retweetWeibo = weibo.getRetweeted_status();
        CharSequence retweetText = "@" + retweetWeibo.getUser().getScreen_name() + " :"
                + retweetWeibo.getText();
        SpannableWeiboText weiboText = new SpannableWeiboText.Builder()
                .loadAt()
                .loadTopic()
                .build(retweetText);
        mRetweetText.setText(weiboText.getStringBuilder());

    }
}
