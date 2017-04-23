package me.fallblank.weiworld.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.CommentsAPI;

import butterknife.BindView;
import me.fallblank.weiworld.App;
import me.fallblank.weiworld.BuildConfig;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.ui.adapter.ContactFragmentAdapter;
import me.fallblank.weiworld.util.LogUtil;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.m;

/**
 * Created by fallb on 2017/4/21.
 */

public class ContactFragment extends BaseFragment {


    @BindView(R.id.vp_pager)
    ViewPager mPager;

    @BindView(R.id.tabs)
    PagerSlidingTabStrip mTabStrip;

    ContactFragmentAdapter mAdapter;

    public ContactFragment() {
    }

    public static ContactFragment newInstance() {
        ContactFragment fragment = new ContactFragment();
        // TODO: 2017/4/21 init bundle
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_contact;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        mAdapter = new ContactFragmentAdapter(getActivity().getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mTabStrip.setViewPager(mPager);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        App app = (App) mContext.getApplicationContext();
        CommentsAPI commentsAPI = new CommentsAPI(mContext, BuildConfig.APP_KEY, app.getAccessToken());
        commentsAPI.toME(0, 0, 2, 1, CommentsAPI.AUTHOR_FILTER_ALL, CommentsAPI.SRC_FILTER_WEIBO, mListener);
    }

    private RequestListener mListener = new RequestListener() {
        @Override
        public void onComplete(String s) {
            LogUtil.d(s);
        }

        @Override
        public void onWeiboException(WeiboException e) {
            LogUtil.d("error", e);
        }
    };
}
