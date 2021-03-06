package me.fallblank.weiworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.ui.adapter.holder.weibo.BaseWeiboHolder;
import me.fallblank.weiworld.util.ReuseHolderMethod;

public class WeiboDetailActivity extends BaseActivity {
    public static final String EXTRA_WEIBO = "WeiboDetailActivity.weibo";
    

    @Override
    protected int setContentView() {
        return R.layout.activity_weibo_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        Intent intent = getIntent();
        Weibo weibo = (Weibo) intent.getSerializableExtra(WeiboDetailActivity.EXTRA_WEIBO);
        int type = ReuseHolderMethod.getWeiboType(weibo);
        //委托holder处理一起具体细节
        BaseWeiboHolder holder = ReuseHolderMethod.getWeiboHolder(type, findViewById(android.R.id.content));
        holder.setFManager(getSupportFragmentManager());
        holder.setContent(weibo);
        //取消掉原本的转发、点赞、评论栏
        findViewById(R.id.bottombar_layout).setVisibility(View.GONE);
    }
    
}
