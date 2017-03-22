package me.fallblank.weiworld.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.fallblank.weiworld.R;

import static android.R.attr.onClick;

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
                i = new Intent(SplashActivity.this,MainActivity.class);
            }else {
                i = new Intent(SplashActivity.this,LoginActivity.class);
            }
            startActivity(i);
            SplashActivity.this.finish();
        }
    };

    private boolean checkLogin(){
        //故意返回false，测试使用
        return false;
    }

}
