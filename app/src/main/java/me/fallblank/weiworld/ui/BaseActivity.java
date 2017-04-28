package me.fallblank.weiworld.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fallblank.weiworld.R;

/**
 * Created by fallb on 2017/3/8.
 */

public abstract class BaseActivity extends AppCompatActivity {
    
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    
    //Load content view by resource id
    protected abstract int setContentView();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        ButterKnife.bind(this);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
        }
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
}
