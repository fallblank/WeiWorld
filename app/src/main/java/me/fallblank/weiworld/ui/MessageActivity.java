package me.fallblank.weiworld.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.ui.fragment.BilateralFragment;
import me.fallblank.weiworld.ui.fragment.CommentFragment;
import me.fallblank.weiworld.ui.fragment.MentionFragment;

/**
 * Created by fallb on 2017/5/3.
 */

public class MessageActivity extends BaseActivity {
    
    @BindView(R.id.tab_mention)
    ViewGroup mTabMention;
    
    @BindView(R.id.tab_comment)
    ViewGroup mTabComment;
    
    @BindView(R.id.tab_bilateral)
    ViewGroup mTabBilateral;
    
    ViewGroup mSelectedTab;
    
    FragmentManager mFManager;
    
    CommentFragment mCommentFragment;
    MentionFragment mMentionFragment;
    BilateralFragment mBilateralFragment;
    
    private static final String TAG_COMMENT = "comment";
    private static final String TAG_MENTION = "mention";
    private static final String TAG_BILATERAL = "bilateral";
    
    @Override
    protected int setContentView() {
        return R.layout.activity_message;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mFManager = getSupportFragmentManager();
        initTabs();
        mSelectedTab = mTabMention;
        showMention(mFManager.beginTransaction());
    }
    
    private void initTabs() {
        mTabMention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View hintView = mTabMention.getChildAt(1);
                hintView.setBackgroundColor(getResources().getColor(R.color.colorTabSelected));
                if (mSelectedTab != null) {
                    mSelectedTab.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.colorTransparent));
                }
                mSelectedTab = mTabMention;
                showMention(mFManager.beginTransaction());
            }
        });
        mTabBilateral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View hintView = mTabBilateral.getChildAt(1);
                hintView.setBackgroundColor(getResources().getColor(R.color.colorTabSelected));
                if (mSelectedTab != null) {
                    mSelectedTab.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.colorTransparent));
                }
                mSelectedTab = mTabBilateral;
                showBilateral(mFManager.beginTransaction());
            }
        });
        mTabComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View hintView = mTabComment.getChildAt(1);
                hintView.setBackgroundColor(getResources().getColor(R.color.colorTabSelected));
                if (mSelectedTab != null) {
                    mSelectedTab.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.colorTransparent));
                }
                mSelectedTab = mTabComment;
                showComment(mFManager.beginTransaction());
            }
        });
        
    }
    
    private void hideAllFragment(FragmentTransaction transaction) {
        if (mCommentFragment != null) {
            transaction.hide(mCommentFragment);
        }
        if (mMentionFragment != null) {
            transaction.hide(mMentionFragment);
        }
        if (mBilateralFragment != null) {
            transaction.hide(mBilateralFragment);
        }
    }
    
    private void showComment(FragmentTransaction transaction) {
        hideAllFragment(transaction);
        if (mCommentFragment == null) {
            mCommentFragment = CommentFragment.newInstance();
            transaction.add(R.id.content_container, mCommentFragment, TAG_COMMENT);
        } else {
            transaction.show(mCommentFragment);
        }
        transaction.commit();
    }
    
    private void showMention(FragmentTransaction transaction) {
        hideAllFragment(transaction);
        if (mMentionFragment == null) {
            mMentionFragment = MentionFragment.newInstance();
            transaction.add(R.id.content_container, mMentionFragment, TAG_MENTION);
        } else {
            transaction.show(mMentionFragment);
        }
        transaction.commit();
    }
    
    private void showBilateral(FragmentTransaction transaction) {
        hideAllFragment(transaction);
        if (mBilateralFragment == null) {
            mBilateralFragment = BilateralFragment.newInstance();
            transaction.add(R.id.content_container, mBilateralFragment, TAG_BILATERAL);
        } else {
            transaction.show(mBilateralFragment);
        }
        transaction.commit();
    }
}
