package me.fallblank.weiworld.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fallblank.weiworld.R;

/**
 * Created by fallb on 2017/4/7.
 */

public class RetweetWeiboHolder extends BaseWeiboHolder {

    @BindView(R.id.weibo_retweet_text)
    @Nullable TextView mRetweetText;

    @BindView(R.id.weibo_retweet_option)
    @Nullable FrameLayout mRetweetContainer;

    public RetweetWeiboHolder(View itemView) {
        super(itemView);
        Context context = itemView.getContext();
        View retweet = LayoutInflater.from(context)
                .inflate(R.layout.item_retweet_weibo_content,mWeiboOption,true);
        ButterKnife.bind(this,itemView);
    }

    public void setRetweetText(String content){
        mRetweetText.setText(content);
    }
}
