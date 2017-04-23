package me.fallblank.weiworld.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Comment;

/**
 * Created by fallb on 2017/4/20.
 */

public class CommentHolder extends BaseHolder {

    @BindView(R.id.user_profile)
    SimpleDraweeView mUserProfile;

    @BindView(R.id.username)
    TextView mUsername;

    @BindView(R.id.timestamp)
    TextView mTimestamp;

    @BindView(R.id.comment)
    TextView mComment;

    public CommentHolder(View itemView) {
        super(itemView);
    }

    public void setContent(Comment comment) {
        mUserProfile.setImageURI(comment.getUser().getProfile_image_url());
        mTimestamp.setText(comment.getCreated_at().toString());
        mUsername.setText(comment.getUser().getScreen_name());
        mComment.setText(comment.getText());
    }
}
