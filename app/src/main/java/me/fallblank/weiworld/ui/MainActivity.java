package me.fallblank.weiworld.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.fallblank.weiworld.R;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_login) Button mLogin;
    @BindView(R.id.btn_register) Button mRegister;
    @BindView(R.id.tv_forgot) TextView mForgot;

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    //Trigger of SSO、OAUth authority
    @OnClick(R.id.btn_login) void login(){
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
    }

    //Take user to sina weibo register website
    @OnClick(R.id.btn_register) void register(){

    }

    //Take user to sina weibo get back website
    @OnClick(R.id.tv_forgot) void forgotPassword(){

    }

}
