package me.fallblank.weiworld.ui;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.fallblank.weiworld.R;
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

    @Override
    protected int setContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
        ButterKnife.bind(this);
    }

    //Trigger of SSO、OAUth authority
    @OnClick(R.id.btn_login)
    void login() {
        ToastUtil.show(this, "login");
        mWaitDialog.show();
    }

    //Take user to sina weibo register website
    @OnClick(R.id.btn_register)
    void register() {
        ToastUtil.show(this, "register");
    }

    //Take user to sina weibo get back website
    @OnClick(R.id.tv_forgot)
    void forgotPassword() {
        ToastUtil.show(this, "forgot");
    }

    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.TranslucentFullScreen);
        LayoutInflater inflater = LayoutInflater.from(this);
        builder.setView(inflater.inflate(R.layout.dialog_progress_wait,null));
        mWaitDialog = builder.create();
    }


    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }
}
