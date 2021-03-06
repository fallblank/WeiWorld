package me.fallblank.weiworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.biz.AuthComplete;
import me.fallblank.weiworld.biz.OnFetchListener;
import me.fallblank.weiworld.iview.IWaitView;
import me.fallblank.weiworld.presenter.LoginPresenter;
import me.fallblank.weiworld.util.ToastUtil;

public class LoginActivity extends BaseActivity implements IWaitView, AuthComplete, OnFetchListener {
    @BindView(R.id.btn_login)
    Button mLogin;
    @BindView(R.id.btn_register)
    TextView mRegister;
    @BindView(R.id.tv_forgot)
    TextView mForgot;
    
    TextView mHint;
    
    private AlertDialog mWaitDialog;
    private LoginPresenter mLoginPresenter;
    
    @Override
    protected int setContentView() {
        return R.layout.activity_login;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    
    @OnClick(R.id.btn_login)
    void login() {
        mLoginPresenter.login();
    }
    
    @OnClick(R.id.btn_register)
    void register() {
        mLoginPresenter.register();
    }
    
    @OnClick(R.id.tv_forgot)
    void forgotPassword() {
        mLoginPresenter.forgotPassword();
    }
    
    private void init() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppTheme);
        LayoutInflater inflater = LayoutInflater.from(this);
        ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
        View view = inflater.inflate(R.layout.dialog_progress_wait, contentView, false);
        mHint = (TextView) view.findViewById(R.id.tv_hint);
        builder.setView(view);
        mWaitDialog = builder.create();
        mLoginPresenter = new LoginPresenter(this, this, this, this, this);
    }
    
    
    @Override
    public void show() {
        mWaitDialog.show();
    }
    
    @Override
    public void hide() {
        mWaitDialog.dismiss();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mLoginPresenter.authorizeCallback(requestCode, resultCode, data);
    }
    
    @Override
    public void success() {
        ToastUtil.show(this, getString(R.string.auth_success));
        mHint.setText("拉取用户信息中...");
        mLoginPresenter.fetchLoginUserInfo();
    }
    
    @Override
    public void fail() {
        ToastUtil.show(this, getString(R.string.auth_fail));
    }
    
    @Override
    public void cancel() {
        ToastUtil.show(this, getString(R.string.auth_cancle));
    }
    
    @Override
    public void exception(Throwable throwable) {
        ToastUtil.show(this, getString(R.string.auth_error));
    }
    
    @Override
    public void onFetchSuccess() {
        ToastUtil.show(this, "拉取用户信息成功");
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(MainActivity.ACTION_OPEN_DRAWER);
        startActivity(intent);
        this.finish();
    }
    
    @Override
    public void onFetchFail() {
        ToastUtil.show(this, "拉取用户信息失败");
    }
}
