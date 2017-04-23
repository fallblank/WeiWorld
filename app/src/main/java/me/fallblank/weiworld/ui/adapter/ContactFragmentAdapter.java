package me.fallblank.weiworld.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import me.fallblank.weiworld.ui.fragment.ContactCommentFragment;

/**
 * Created by fallb on 2017/4/21.
 */

public class ContactFragmentAdapter extends FragmentPagerAdapter {

    public ContactFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new ContactCommentFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "test";
    }
}
