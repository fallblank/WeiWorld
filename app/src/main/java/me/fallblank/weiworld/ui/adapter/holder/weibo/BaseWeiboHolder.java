package me.fallblank.weiworld.ui.adapter.holder.weibo;

import android.support.v4.app.FragmentManager;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.ui.adapter.holder.BaseHolder;
import me.fallblank.weiworld.ui.fragment.WeiboOptionBottomSheetFragment;
import me.fallblank.weiworld.ui.text.SpannableWeiboText;
import me.fallblank.weiworld.util.TimeFormatter;

/**
 * Created by fallb on 2017/4/5.
 */

public class BaseWeiboHolder extends BaseHolder {
    
    @BindView(R.id.user_container)
    ViewGroup mUserInfoContainer;
    
    @BindView(R.id.user_profile)
    protected SimpleDraweeView mUserProfile;
    
    @BindView(R.id.user_name)
    protected TextView mUserName;
    
    @BindView(R.id.weibo_timestamp)
    protected TextView mTimestamp;
    
    @BindView(R.id.card_option_more)
    protected View mMoreOption;
    
    @BindView(R.id.weibo_text)
    protected TextView mContentText;
    
    @BindView(R.id.weibo_option)
    protected FrameLayout mWeiboOption;
    
    @BindView(R.id.bottombar_layout)
    ViewGroup mFuncContainer;
    
    @BindView(R.id.redirect)
    TextView mRedirect;
    
    @BindView(R.id.comment)
    TextView mComment;
    
    @BindView(R.id.feedlike)
    TextView mLike;
    
    private FragmentManager mFManager;
    private static final String TAG_BOTTOM_SHEET = "bottomsheetfragment";
    
    
    public BaseWeiboHolder(View itemView) {
        super(itemView);
    }
    
    public void setContent(final Weibo weibo) {
        if (!weibo.getDeleted().equals("1")) {
            mUserInfoContainer.setVisibility(View.VISIBLE);
            mFuncContainer.setVisibility(View.VISIBLE);
            mUserProfile.setImageURI(weibo.getUser().getProfile_image_url());
            mUserName.setText(weibo.getUser().getScreen_name());
            mTimestamp.setText(TimeFormatter.getVisualTimestamp(weibo.getCreated_at()));
            mMoreOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WeiboOptionBottomSheetFragment fragment = WeiboOptionBottomSheetFragment.newInstance(weibo);
                    fragment.show(mFManager, TAG_BOTTOM_SHEET);
                }
            });
            mRedirect.setText(String.valueOf(weibo.getReposts_count()));
            mComment.setText(String.valueOf(weibo.getComments_count()));
            mLike.setText(String.valueOf(weibo.getAttitudes_count()));
        } else {
            mUserInfoContainer.setVisibility(View.GONE);
            mFuncContainer.setVisibility(View.GONE);
        }
        mContentText.setMovementMethod(LinkMovementMethod.getInstance());
        SpannableWeiboText originTextBuilder = new SpannableWeiboText.Builder()
                .loadAt()
                .loadTopic()
                .build(weibo.getText());
        mContentText.setText(originTextBuilder.getStringBuilder());
    }
    
    public void setFManager(FragmentManager FManager) {
        mFManager = FManager;
    }
}
