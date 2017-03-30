package me.fallblank.weiworld.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.fallblank.weiworld.App;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.util.AccessTokenKeeper;

import static android.R.attr.onClick;
import static me.fallblank.weiworld.util.AccessTokenKeeper.readAccessToken;

public class SplashActivity extends BaseActivity {

    private static final int DELAY_TIME_SECOND = 3 * 1000;

    private Handler mHandler = new Handler();

    @BindView(R.id.btn_escape) Button mEscape;

    @Override
    protected int setContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler.postDelayed(mLauncher,DELAY_TIME_SECOND);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_escape)
    void escape(){
        mHandler.removeCallbacks(mLauncher);
        mHandler.post(mLauncher);
    }

    private final Runnable mLauncher = new Runnable() {
        @Override
        public void run() {
            Intent i = null;
            if (checkLogin()){
                i = new Intent(SplashActivity.this,HomeActivity.class);
            }else {
                i = new Intent(SplashActivity.this,LoginActivity.class);
            }
            startActivity(i);
            SplashActivity.this.finish();
        }
    };

    private boolean checkLogin(){
        Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(this);
        if (accessToken == null || !accessToken.isSessionValid()){
            return false;
        }
        App application = (App) this.getApplication();
        application.setAccessToken(accessToken);
        return true;
    }

    //检查token有效期未做

}
