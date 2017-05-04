package me.fallblank.weiworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.StatusesAPI;

import butterknife.BindView;
import me.fallblank.weiworld.App;
import me.fallblank.weiworld.BuildConfig;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.LoginUser;
import me.fallblank.weiworld.ui.fragment.WaitDialogFragment;
import me.fallblank.weiworld.util.LogUtil;
import me.fallblank.weiworld.util.ToastUtil;

public class PostActivity extends BaseActivity {
    
    public static final String EXTRA_CONTENT = "content";
    
    @Override
    protected int setContentView() {
        return R.layout.activity_post;
    }
    
    @BindView(R.id.tv_cancel)
    View mCancel;
    
    @BindView(R.id.tv_username)
    TextView mUsername;
    
    @BindView(R.id.tv_post)
    TextView mPost;
    
    @BindView(R.id.et_content)
    EditText mContentEditor;
    
    private StatusesAPI mAPI;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String content = intent.getStringExtra(EXTRA_CONTENT);
        if (content != null && !content.equals("")) {
            mContentEditor.setText(content);
        }
        init();
    }
    
    private void init() {
        App app = (App) getApplicationContext();
        mAPI = new StatusesAPI(this, BuildConfig.APP_KEY, app.getAccessToken());
        LoginUser user = app.getUser();
        mUsername.setText(user.getScreen_name());
        
        mContentEditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                
            }
            
            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !s.toString().equals("")) {
                    mPost.setClickable(true);
                    mPost.setTextColor(getResources().getColor(R.color.colorClickableText));
                } else {
                    mPost.setClickable(false);
                    mPost.setTextColor(getResources().getColor(R.color.colorUnclickableText));
                }
            }
        });
        
        mPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送逻辑
                final WaitDialogFragment waitDialog = new WaitDialogFragment();
                waitDialog.setHint("微博发送中...");
                waitDialog.show(getSupportFragmentManager(), "waitdialog");
                String content = mContentEditor.getText().toString();
                mAPI.update(content, "0.0", "0.0", new RequestListener() {
                    @Override
                    public void onComplete(String s) {
                        waitDialog.dismiss();
                        ToastUtil.show(PostActivity.this, "发送成功");
                        PostActivity.super.onBackPressed();
                    }
                    
                    @Override
                    public void onWeiboException(WeiboException e) {
                        waitDialog.dismiss();
                        ToastUtil.show(PostActivity.this, "发送失败");
                        LogUtil.d("PostActivity", e);
                    }
                });
            }
        });
        
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostActivity.super.onBackPressed();
            }
        });
    }
}
