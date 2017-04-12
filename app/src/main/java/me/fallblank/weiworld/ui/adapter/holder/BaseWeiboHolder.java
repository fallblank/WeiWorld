package me.fallblank.weiworld.ui.adapter.holder;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.ui.adapter.holder.BaseHolder;

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

    public void setUserProfile(String uri) {
        mUserProfile.setImageURI(uri);
    }

    public void setUserName(String userName) {
        mUserName.setText(userName);
    }

    public void setTimestamp(String timestamp) {
        mTimestamp.setText(timestamp);
    }

    public void setContentText(CharSequence content){
        mContentText.setText(content);
    }
}
