package me.fallblank.weiworld.ui;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.BindView;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.ui.fragment.ContactFragment;
import me.fallblank.weiworld.ui.fragment.ContentFragment;
import me.fallblank.weiworld.ui.receiver.NetworkReceiver;
import me.fallblank.weiworld.util.Constant;

public class HomeActivity extends BaseActivity {

    private static final String TAG_CONTENT = "ContentFragment";
    private static final String TAG_CONTACT = "ContactFragment";

    private BroadcastReceiver mNetworkReceiver;


    @BindView(R.id.container)
    FrameLayout mContainer;

    @BindView(R.id.bn_navigation)
    BottomNavigationView mNavigation;

    private FragmentManager mFManager;
    /**
     * 微博动态fragment
     */
    private ContentFragment mContentFragment;

    /**
     * 微博联系人fragment
     */
    private ContactFragment mContactFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction transaction = mFManager.beginTransaction();
            hideAllFragment(transaction);
            switch (item.getItemId()) {
                case R.id.navigation_refresh:
                    setTitle("动态");
                    showContent(transaction);
                    break;
                case R.id.navigation_dashboard:
                    setTitle("消息");
                    showContact(transaction);
                    break;
                case R.id.navigation_notifications:
                    //setting
                    break;
            }
            transaction.commit();
            return true;
        }

    };

    @Override
    protected int setContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册网络变化的广播
        IntentFilter filter = new IntentFilter(Constant.ACTION_CONNECTIVITY_CHANGE);
        mNetworkReceiver = new NetworkReceiver();
        registerReceiver(mNetworkReceiver, filter);
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mFManager = getSupportFragmentManager();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (null != mContentFragment) {
            transaction.hide(mContentFragment);
        }
        if (null != mContactFragment){
            transaction.hide(mContactFragment);
        }
    }

    private void showContent(FragmentTransaction transaction) {
        if (null == mContentFragment) {
            mContentFragment = ContentFragment.newInstance();
            transaction.add(R.id.container, mContentFragment, TAG_CONTENT);
        } else {
            transaction.show(mContentFragment);
        }
    }

    private void showContact(FragmentTransaction transaction) {
        if (null == mContactFragment) {
            mContactFragment = ContactFragment.newInstance();
            transaction.add(R.id.container, mContactFragment, TAG_CONTENT);
        } else {
            transaction.show(mContactFragment);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetworkReceiver);
    }

    //Avoid application exit on back button pressed
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }
}
