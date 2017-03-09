package me.fallblank.weiworld.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.fallblank.weiworld.R;

/**
 * Created by fallb on 2017/3/8.
 */

public abstract class BaseActivity extends AppCompatActivity {
    //Load content view by resource id
    protected abstract int setContentView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
    }
}
