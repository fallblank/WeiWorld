package me.fallblank.weiworld.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import butterknife.BindView;
import butterknife.OnClick;
import me.fallblank.weiworld.App;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.LoginUser;
import me.fallblank.weiworld.ui.text.SpannableWeiboText;
import me.fallblank.weiworld.util.AccessTokenKeeper;
import me.fallblank.weiworld.util.AppUtil;
import me.fallblank.weiworld.util.UserInfoLoader;

import static me.fallblank.weiworld.util.AppUtil.APP_METADATA;
import static me.fallblank.weiworld.util.AppUtil.KEY_FIRST_LAUNCH;
import static me.fallblank.weiworld.util.AppUtil.KEY_VERSION;

public class BootActivity extends BaseActivity {

    private final int DELAY_TIME_SECOND = 3 * 1000;


    private Handler mHandler = new Handler();

    @BindView(R.id.tv_text)
    TextView mText;

    @Override
    protected int setContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SpannableWeiboText weiboText = new SpannableWeiboText.Builder()
                .loadAt()
                .loadTopic()
                .build("@简界 :#启动啦#");
        mText.setText(weiboText.getStringBuilder());
        autologin();
    }


    private void autologin() {
        Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(this);
        if (accessToken == null || !accessToken.isSessionValid()) {
            startLoginActivity();
            return;
        }
        App application = (App) this.getApplication();
        application.setAccessToken(accessToken);
        UserInfoLoader.asynLoadUserInfo(this, accessToken, new UserInfoLoader.LoadListener() {
            @Override
            public void onComplete(LoginUser userInfo) {
                App app = (App) getApplicationContext();
                app.setUser(userInfo);
                startMainActivity();

            }

            @Override
            public void error(Throwable throwable) {
                startLoginActivity();
            }
        });
    }

    private void startLoginActivity() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(BootActivity.this, LoginActivity.class);
                startActivity(i);
                BootActivity.this.finish();
            }
        };
        mHandler.postDelayed(task, DELAY_TIME_SECOND);
    }

    private void startMainActivity() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(BootActivity.this, MainActivity.class);
                startActivity(i);
                BootActivity.this.finish();
            }
        };
        mHandler.postDelayed(task, DELAY_TIME_SECOND);
    }

}
