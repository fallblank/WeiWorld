package me.fallblank.weiworld.ui;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import me.fallblank.weiworld.App;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.LoginUser;
import me.fallblank.weiworld.ui.fragment.ContentFragment;
import me.fallblank.weiworld.ui.fragment.FavoriteFragment;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    
    @BindView(R.id.content_container)
    FrameLayout mContentContainer;
    
    SimpleDraweeView mUserProfile;
    
    TextView mUsername;
    
    TextView mUserDesc;
    
    SimpleDraweeView mUserBackground;
    
    FragmentManager mFragmentManager = getSupportFragmentManager();
    Fragment mHomeFragment;
    Fragment mFavoriteFragment;
    
    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        ColorStateList colorStateList = getResources().getColorStateList(R.color.drawer_state_list);
        navigationView.setItemTextColor(colorStateList);
        navigationView.setNavigationItemSelectedListener(this);
        loadUserInfo();
        showIndexFragment();
    }
    
    /**
     * 加载登录用户的信息
     */
    public void loadUserInfo() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View root = navigationView.getHeaderView(0);
        mUserBackground = (SimpleDraweeView) root.findViewById(R.id.user_background);
        mUserProfile = (SimpleDraweeView) root.findViewById(R.id.user_profile);
        mUsername = (TextView) root.findViewById(R.id.user_name);
        mUserDesc = (TextView) root.findViewById(R.id.user_desc);
        LoginUser user = ((App) getApplicationContext()).getUser();
        mUserBackground.setImageURI(user.getCover_image_phone());
        mUserProfile.setImageURI(user.getAvatar_large());
        mUsername.setText(user.getScreen_name());
        mUserDesc.setText(user.getDescription());
    }
    
    /**
     * 显示首页fragment
     */
    private void showIndexFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideAllFragment(transaction);
        showHomeFragment(transaction);
        transaction.commit();
    }
    
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideAllFragment(transaction);
        if (id == R.id.nav_home) {
            showHomeFragment(transaction);
        } else if (id == R.id.nav_gallery) {
            
        } else if (id == R.id.nav_slideshow) {
            
        } else if (id == R.id.nav_favorite) {
            showFavoriteFragment(transaction);
        } else if (id == R.id.nav_share) {
            
        } else if (id == R.id.nav_send) {
            
        }
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    
    private void hideAllFragment(FragmentTransaction transaction) {
        if (null != mHomeFragment) {
            transaction.hide(mHomeFragment);
        }
        if (null != mFavoriteFragment) {
            transaction.hide(mFavoriteFragment);
        }
    }
    
    private static final String TAG_HOME = "home";
    private static final String TAG_FAVORITE = "favorite";
    
    private void showHomeFragment(FragmentTransaction transaction) {
        if (null == mHomeFragment) {
            mHomeFragment = ContentFragment.newInstance();
            transaction.add(R.id.content_container, mHomeFragment, TAG_HOME);
        } else {
            transaction.show(mHomeFragment);
        }
        mToolbar.setTitle("首页");
    }
    
    private void showFavoriteFragment(FragmentTransaction transaction) {
        if (null == mFavoriteFragment) {
            mFavoriteFragment = FavoriteFragment.newInstance();
            transaction.add(R.id.content_container, mFavoriteFragment, TAG_FAVORITE);
        } else {
            transaction.show(mFavoriteFragment);
        }
        mToolbar.setTitle("收藏");
    }
}
