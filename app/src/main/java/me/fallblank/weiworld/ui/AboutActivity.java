package me.fallblank.weiworld.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.ui.adapter.SourceAdapter;
import me.fallblank.weiworld.util.LicenseHelper;

public class AboutActivity extends BaseActivity {
    
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    SourceAdapter mAdapter;
    
    @Override
    protected int setContentView() {
        return R.layout.activity_about;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new SourceAdapter(this, LicenseHelper.initLicense());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
    
    
    
}
