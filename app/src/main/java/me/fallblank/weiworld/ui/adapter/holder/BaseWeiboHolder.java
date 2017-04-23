package me.fallblank.weiworld.ui.adapter.holder;

import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.ui.adapter.holder.BaseHolder;
import me.fallblank.weiworld.ui.text.SpannableWeiboText;
import me.fallblank.weiworld.util.ColorGenerator;
import me.fallblank.weiworld.util.TimeFormatter;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.e;
import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.i;
import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.m;
import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.w;

/**
 * Created by fallb on 2017/4/5.
 */

public class BaseWeiboHolder extends BaseHolder {

    @BindView(R.id.user_profile)
    protected SimpleDraweeView mUserProfile;

    @BindView(R.id.user_name)
    protected TextView mUserName;

    @BindView(R.id.weibo_timestamp)
    protected TextView mTimestamp;

    @BindView(R.id.weibo_text)
    protected TextView mContentText;

    @BindView(R.id.weibo_option)
    protected FrameLayout mWeiboOption;


    public BaseWeiboHolder(View itemView) {
        super(itemView);
    }

    public void setContent(Weibo weibo) {
        itemView.setTag(weibo);
        itemView.setBackgroundColor(ColorGenerator.genetateColor(mContext));

        mUserProfile.setImageURI(weibo.getUser().getProfile_image_url());
        mUserName.setText(weibo.getUser().getScreen_name());
        mTimestamp.setText(TimeFormatter.formatTime(weibo.getCreated_at()));

        mContentText.setMovementMethod(LinkMovementMethod.getInstance());
        SpannableWeiboText originTextBuilder = new SpannableWeiboText.Builder()
                .loadAt()
                .loadTopic()
                .build(weibo.getText());
        mContentText.setText(originTextBuilder.getStringBuilder());
    }
}
