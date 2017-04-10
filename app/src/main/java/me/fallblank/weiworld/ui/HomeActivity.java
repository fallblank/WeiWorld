package me.fallblank.weiworld.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.BindView;
import me.fallblank.weiworld.R;

public class HomeActivity extends BaseActivity {

    private static final String TAG_CONTENT = "HomeActivity.ContentFragment";

    @BindView(R.id.fl_container) FrameLayout mContainer;
    @BindView(R.id.bn_navigation) BottomNavigationView mNavigation;
    private FragmentManager mFManager;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Fragment content = mFManager.findFragmentByTag(TAG_CONTENT);
                    if (null == content){
                        content = ContentFragment.newInstance();
                        mFManager.beginTransaction()
                                .add(R.id.fl_container,content,TAG_CONTENT)
                                .commit();
                    }else {
                        mFManager.beginTransaction()
                                .replace(R.id.fl_container,content,TAG_CONTENT)
                                .commit();
                    }
                    return true;
                case R.id.navigation_dashboard:
                    //contact
                    return true;
                case R.id.navigation_notifications:
                    //setting
                    return true;
            }
            return false;
        }

    };

    @Override
    protected int setContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFManager = getFragmentManager();
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    //Avoid application exit on back button pressed
    @Override
    public void onBackPressed() {
        Intent i= new Intent(Intent.ACTION_MAIN);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }

}
