package me.fallblank.weiworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import me.fallblank.weiworld.App;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.biz.OnFetchListener;
import me.fallblank.weiworld.presenter.LoginPresenter;
import me.fallblank.weiworld.util.AccessTokenKeeper;

public class BootActivity extends BaseActivity {
    
    private final int DELAY_TIME_SECOND = 3 * 1000;
    
    private LoginPresenter mPresenter = new LoginPresenter(this);
    
    private Handler mHandler = new Handler();
    
    private Runnable mLoginTask = new Runnable() {
        @Override
        public void run() {
            Intent i = new Intent(BootActivity.this, LoginActivity.class);
            startActivity(i);
            BootActivity.this.finish();
        }
    };
    private Runnable mMainTask = new Runnable() {
        @Override
        public void run() {
            Intent i = new Intent(BootActivity.this, MainActivity.class);
            startActivity(i);
            BootActivity.this.finish();
        }
    };
    
    private Runnable mExecuteTask;
    
    @BindView(R.id.tv_time)
    TextView mTimeStamp;
    
    @BindView(R.id.btn_escape)
    Button mEscape;
    
    private static final String BOOT_TIME_FORMAT_PARTTERN = "yyyy年MM月dd日，EEEE";
    
    @Override
    protected int setContentView() {
        return R.layout.activity_boot;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleDateFormat formater = new SimpleDateFormat(BOOT_TIME_FORMAT_PARTTERN);
        String dateStr = formater.format(new Date());
        mTimeStamp.setText(dateStr);
        
        mPresenter.setOnFetchListener(new OnFetchListener() {
            @Override
            public void onFetchSuccess() {
                mExecuteTask = mMainTask;
                postTaskDelay();
            }
            
            @Override
            public void onFetchFail() {
                mExecuteTask = mLoginTask;
                postTaskDelay();
            }
        });
        autologin();
    }
    
    @OnClick(R.id.btn_escape)
    void escapeDeleay() {
        mHandler.removeCallbacks(mExecuteTask);
        mHandler.post(mExecuteTask);
    }
    
    private void autologin() {
        Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(this);
        if (accessToken == null || !accessToken.isSessionValid()) {
            mExecuteTask = mLoginTask;
            postTaskDelay();
            return;
        }
        App application = (App) this.getApplication();
        application.setAccessToken(accessToken);
        mPresenter.fetchLoginUserInfo();
    }
    
    private void postTaskDelay() {
        mHandler.postDelayed(mExecuteTask, DELAY_TIME_SECOND);
        mEscape.setVisibility(View.VISIBLE);
    }
    
}
