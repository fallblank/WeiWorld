package me.fallblank.weiworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;
import me.fallblank.weiworld.App;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.LoginUser;
import me.fallblank.weiworld.util.AccessTokenKeeper;

public class AccountActivity extends BaseActivity {
    
    public static final String KEY_START_ACTIVITY = "start_activity";
    
    @BindView(R.id.account)
    View mAccount;
    
    @BindView(R.id.account_profile)
    SimpleDraweeView mAccountProfile;
    
    @BindView(R.id.account_name)
    TextView mAccountName;
    
    @BindView(R.id.login_out)
    View mLoginOut;
    
    
    @Override
    protected int setContentView() {
        return R.layout.activity_account;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        App app = (App) getApplicationContext();
        LoginUser user = app.getUser();
        mAccountProfile.setImageURI(user.getAvatar_large() + "");
        mAccountName.setText(user.getScreen_name() + "");
    }
    
    @OnClick(R.id.login_out)
    void loginOut() {
        //清除授权信息
        App app = (App) getApplicationContext();
        app.setUser(null);
        app.setAccessToken(null);
        AccessTokenKeeper.clearToken(this);
        
        //启动登录
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        
        //杀死MainActivity
        this.finish();
        MainActivity activity = MainActivity.sActivity;
        if (activity != null) {
            MainActivity.sActivity.finish();
        }
    }
    
    @OnClick(R.id.account)
    void back() {
        super.onBackPressed();
    }
}
