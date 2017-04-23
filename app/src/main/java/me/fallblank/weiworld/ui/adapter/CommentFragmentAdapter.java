package me.fallblank.weiworld.ui.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.fallblank.weiworld.ui.fragment.CommentFragment;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.r;

/**
 * Created by fallb on 2017/4/20.
 */

public class CommentFragmentAdapter extends FragmentPagerAdapter {
    private long mWeiboId;

    public CommentFragmentAdapter(FragmentManager fm,long id) {
        super(fm);
        this.mWeiboId = id;
    }


    @Override
    public Fragment getItem(int position) {
        return CommentFragment.newInstance(mWeiboId);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "评论";
    }
}
