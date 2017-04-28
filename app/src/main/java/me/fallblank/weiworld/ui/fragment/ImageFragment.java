package me.fallblank.weiworld.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.network.ProgressResponseBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by fallb on 2017/4/27.
 */

public class ImageFragment extends BaseFragment {
    
    @BindView(R.id.progress_count)
    TextView mProgressCount;
    
    @BindView(R.id.photo_view)
    PhotoView mImage;
    
    @BindView(R.id.wait_view)
    View mWait;
    
    private final static String KEY_URL = "image_url";
    
    private MyDownloadTask mDownloadTask;
    
    private Handler mHandler = new Handler();
    
    public ImageFragment() {
    }
    
    public static ImageFragment newInstance(String url) {
        ImageFragment fragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    @Override
    protected int setLayout() {
        return R.layout.item_picture_gallery_item;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        String url = getArguments().getString(KEY_URL);
        mDownloadTask = new MyDownloadTask();
        mDownloadTask.execute(url);
        return root;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    
    private class MyDownloadTask extends AsyncTask<String, Double, Bitmap>
            implements ProgressResponseBody.ProgressListener {
        
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());
                        return response
                                .newBuilder()
                                .body(new ProgressResponseBody(response.body(), MyDownloadTask.this))
                                .build();
                    }
                })
                .build();
        
        public MyDownloadTask() {
            super();
        }
        
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mImage.setVisibility(View.GONE);
            mWait.setVisibility(View.VISIBLE);
        }
        
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mWait.setVisibility(View.GONE);
            mImage.setVisibility(View.VISIBLE);
            if (bitmap != null) {
                mImage.setImageBitmap(bitmap);
            } else {
                mImage.setImageResource(R.drawable.placeholder_fail_image);
            }
        }
        
        @Override
        protected void onProgressUpdate(Double... values) {
            super.onProgressUpdate(values);
            final int progress = (int) (values[0] * 100.00);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mProgressCount.setText(progress + "%");
                }
            });
            
        }
        
        
        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            Bitmap result = null;
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = httpClient.newCall(request).execute();
                byte[] bytes = response.body().bytes();
                result = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
        
        @Override
        public void update(long bytesRead, long contentLength, boolean done) {
            double rate = (double) bytesRead / (double) contentLength;
            onProgressUpdate(rate);
        }
    }
}