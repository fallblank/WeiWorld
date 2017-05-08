package me.fallblank.weiworld.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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
import me.fallblank.weiworld.ui.fragment.PublicFragment;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    
    public static MainActivity sActivity = null;
    public static final String ACTION_OPEN_DRAWER = "action_open_drawer";
    
    @BindView(R.id.content_container)
    FrameLayout mContentContainer;
    
    SimpleDraweeView mUserProfile;
    
    TextView mUsername;
    
    TextView mUserDesc;
    
    SimpleDraweeView mUserBackground;
    
    FragmentManager mFragmentManager = getSupportFragmentManager();
    Fragment mHomeFragment;
    Fragment mFavoriteFragment;
    Fragment mPublicFragment;
    
    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sActivity = this;
        
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                startActivity(intent);
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
        Intent intent = getIntent();
        if (intent.getAction() == MainActivity.ACTION_OPEN_DRAWER) {
            drawer.openDrawer(GravityCompat.START);
        }
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
        
        mUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
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
        if (id == R.id.nav_home) {
            hideAllFragment(transaction);
            showHomeFragment(transaction);
        } else if (id == R.id.nav_hot) {
            hideAllFragment(transaction);
            showPublicFragment(transaction);
        } else if (id == R.id.nav_favorite) {
            hideAllFragment(transaction);
            showFavoriteFragment(transaction);
        } else if (id == R.id.nav_message) {
            Intent intent = new Intent(MainActivity.this, MessageActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_SEND);
            i.putExtra(Intent.EXTRA_TEXT, "第三方轻量级微博客户端：https://raw.githubusercontent.com/fallblank/WeiWorld/master/weijie.apk");
            i.setType("text/plain");
            startActivity(i);
        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
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
        if (null != mPublicFragment) {
            transaction.hide(mPublicFragment);
        }
    }
    
    private static final String TAG_HOME = "home";
    private static final String TAG_FAVORITE = "favorite";
    private static final String TAG_PUBLIC = "public";
    
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
    
    private void showPublicFragment(FragmentTransaction transaction) {
        if (null == mPublicFragment) {
            mPublicFragment = PublicFragment.newInstance();
            transaction.add(R.id.content_container, mPublicFragment, TAG_PUBLIC);
        } else {
            transaction.show(mPublicFragment);
        }
        mToolbar.setTitle("热门");
    }
}
