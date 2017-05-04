package me.fallblank.weiworld.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Comment;
import me.fallblank.weiworld.ui.text.SpannableWeiboText;
import me.fallblank.weiworld.util.TimeFormatter;

/**
 * Created by fallb on 2017/5/3.
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
    
    @BindView(R.id.comment_weibo)
    TextView mWeiboOrComment;
    
    public CommentHolder(View itemView) {
        super(itemView);
    }
    
    public void setContent(Comment content) {
        mUserProfile.setImageURI(content.getUser().getProfile_image_url());
        mUsername.setText(content.getUser().getScreen_name());
        mTimestamp.setText(TimeFormatter.getVisualTimestamp(content.getCreated_at()));
        SpannableWeiboText spannable = new SpannableWeiboText.Builder()
                .loadTopic()
                .loadAt()
                .build(content.getText());
        mComment.setText(spannable.getStringBuilder());
        String weiboOrComment = "";
        if (content.getReply_comment() != null) {
            weiboOrComment = content.getReply_comment().getText();
        } else {
            weiboOrComment = content.getStatus().getText();
        }
        SpannableWeiboText span = new SpannableWeiboText.Builder()
                .loadTopic()
                .loadAt()
                .build(weiboOrComment);
        mWeiboOrComment.setText(span.getStringBuilder());
    }
}
