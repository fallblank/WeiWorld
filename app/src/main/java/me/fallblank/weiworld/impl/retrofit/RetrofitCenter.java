package me.fallblank.weiworld.impl.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import me.fallblank.weiworld.biz.retrofit.IDetailWeibo;
import me.fallblank.weiworld.biz.retrofit.IEmotionContent;
import me.fallblank.weiworld.biz.retrofit.IWeiboContent;
import me.fallblank.weiworld.util.TimeFormatter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by fallb on 2017/3/31.
 */

public class RetrofitCenter {
    private static final int CONNECT_TIMEOUT = 5000 * 1;
    private static final int READ_TIMEOUT = 1000 * 5;
    private static final int WRITE_TIMEOUT = 1000 * 5;

    //实现超时设定
    OkHttpClient mClient = new OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .build();

    //实现格式化日期
    // TODO: 2017/4/6 未检验Local相关的设置是否影响 
    Gson mGson = new GsonBuilder()
            .setDateFormat(TimeFormatter.TIME_FORMAT_PARTTERN)
            .create();

    Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl("https://api.weibo.com/")
            .addConverterFactory(GsonConverterFactory.create(mGson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(mClient)
            .build();

    public IWeiboContent getWeiboContent() {
        return mRetrofit.create(IWeiboContent.class);
    }

    public IEmotionContent getEmotionContent() {
        return mRetrofit.create(IEmotionContent.class);
    }

    public IDetailWeibo getDetailWeibo() {
        return mRetrofit.create(IDetailWeibo.class);
    }
}
