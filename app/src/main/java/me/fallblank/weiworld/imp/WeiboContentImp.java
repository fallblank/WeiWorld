package me.fallblank.weiworld.imp;

import me.fallblank.weiworld.biz.IWeiboContent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by fallb on 2017/3/31.
 */

public class WeiboContentImp {
    Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl("https://api.weibo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public IWeiboContent getWeiboContent(){
        IWeiboContent weiboContent = mRetrofit.create(IWeiboContent.class);
        return weiboContent;
    }
}
