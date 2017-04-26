package me.fallblank.weiworld.ui.adapter.holder.weibo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.ui.adapter.holder.PictureGallery;
import me.fallblank.weiworld.ui.text.SpannableWeiboText;

/**
 * Created by fallb on 2017/4/25.
 */

public class RetweetWeiboHolder extends BaseWeiboHolder {
    
    @Nullable
    @BindView(R.id.weibo_retweet_text)
    TextView mRetweetContent;
    
    @Nullable
    @BindView(R.id.weibo_retweet_option)
    FrameLayout mRetweetOption;
    
    PictureGallery mGallery;
    
    public RetweetWeiboHolder(View itemView) {
        super(itemView);
        Context context = itemView.getContext();
        View root = LayoutInflater.from(context).inflate(R.layout.item_retweet_weibo_content, mWeiboOption, true);
        ButterKnife.bind(this, itemView);
        mGallery = new PictureGallery(root, mRetweetOption);
    }
    
    @Override
    public void setContent(Weibo weibo) {
        super.setContent(weibo);
        Weibo retweetWeibo = weibo.getRetweeted_status();
        String content;
        if (retweetWeibo.getUser() != null){
            content = "@" + retweetWeibo.getUser().getScreen_name() + " :"
                    + retweetWeibo.getText();
        }else {
            content = retweetWeibo.getText();
        }
        SpannableWeiboText retweetText = new SpannableWeiboText.Builder()
                .loadAt()
                .loadTopic()
                .build(content);
        mRetweetContent.setText(retweetText.getStringBuilder());
        if (retweetWeibo.isContainPic()){
            mRetweetOption.setVisibility(View.VISIBLE);
            mGallery.setPictureList(retweetWeibo.getBmiddle_urls());
        }else {
            mRetweetOption.setVisibility(View.GONE);
        }
    }
}
