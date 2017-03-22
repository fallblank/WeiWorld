package me.fallblank.weiworld.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.util.ToastUtil;
import me.fallblank.weiworld.view.IWaitView;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.l;

public class MainActivity extends BaseActivity {


    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }
}
