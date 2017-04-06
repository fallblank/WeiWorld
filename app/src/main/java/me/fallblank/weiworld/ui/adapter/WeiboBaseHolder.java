package me.fallblank.weiworld.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fallblank.weiworld.R;

/**
 * Created by fallb on 2017/4/5.
 */

public class WeiboBaseHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.user_profile)
    SimpleDraweeView mUserProfile;

    @BindView(R.id.user_name)
    TextView mUserName;

    @BindView(R.id.weibo_timestamp)
    TextView mTimestamp;

    @BindView(R.id.weibo_text)
    TextView mContentText;

    public WeiboBaseHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
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
