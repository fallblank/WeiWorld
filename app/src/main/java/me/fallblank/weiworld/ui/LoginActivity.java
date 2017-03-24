package me.fallblank.weiworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.presenter.LoginPresenter;
import me.fallblank.weiworld.util.ToastUtil;
import me.fallblank.weiworld.view.IWaitView;

public class LoginActivity extends BaseActivity implements IWaitView{
    @BindView(R.id.btn_login)
    Button mLogin;
    @BindView(R.id.btn_register)
    Button mRegister;
    @BindView(R.id.tv_forgot)
    TextView mForgot;

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
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    void login() {
        ToastUtil.show(this, "login");
        mLoginPresenter.login();
    }

    @OnClick(R.id.btn_register)
    void register() {
        ToastUtil.show(this, "register");
    }

    @OnClick(R.id.tv_forgot)
    void forgotPassword() {
        ToastUtil.show(this, "forgot");
    }

    private void init() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.TranslucentFullScreen);
        LayoutInflater inflater = LayoutInflater.from(this);
        builder.setView(inflater.inflate(R.layout.dialog_progress_wait,null));
        mWaitDialog = builder.create();
        mLoginPresenter =new LoginPresenter(this,this,this);
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
        mLoginPresenter.authorizeCallback(requestCode,resultCode,data);
    }
}
