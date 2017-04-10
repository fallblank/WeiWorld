package me.fallblank.weiworld.ui.adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import me.fallblank.weiworld.R;

/**
 * Created by fallb on 2017/4/5.
 */

public class BaseWeiboHolder extends BaseHolder {

    @BindView(R.id.user_profile)
    SimpleDraweeView mUserProfile;

    @BindView(R.id.user_name)
    TextView mUserName;

    @BindView(R.id.weibo_timestamp)
    TextView mTimestamp;

    @BindView(R.id.weibo_text)
    TextView mContentText;

    @BindView(R.id.weibo_option)
    FrameLayout mWeiboOption;

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

    public void setContentText(String content){
        mContentText.setText(content);
    }
}
