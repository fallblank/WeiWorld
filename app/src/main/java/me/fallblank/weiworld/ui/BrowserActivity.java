package me.fallblank.weiworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import me.fallblank.weiworld.R;

public class BrowserActivity extends BaseActivity {
    public static final String KEY_URL = "me.fallblank.weiworld.ui.BrowserActivity_URL";

    @BindView(R.id.pb_progress)
    ProgressBar mProgress;

    @BindView(R.id.wv_browser)
    WebView mBrowser;

    @Override
    protected int setContentView() {
        return R.layout.activity_browser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        Intent intent = getIntent();
        String url = intent.getStringExtra(KEY_URL);
        mBrowser.loadUrl(url);
    }

    private void initBrowser(){
        WebSettings webSettings = mBrowser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mBrowser.setWebViewClient(new MyWebViewClient());
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}
