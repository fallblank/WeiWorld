package me.fallblank.weiworld;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.fallblank.weiworld.bean.ContentsResponse;
import me.fallblank.weiworld.biz.retrofit.IWeiboContent;
import me.fallblank.weiworld.impl.retrofit.RetrofitCenter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by fallb on 2017/3/31.
 */

public class WeiboContentTest {
    IWeiboContent mWeiboContent;
    Map<String,String> mQueryMap;

    @Before
    public void setup(){
        RetrofitCenter impl = new RetrofitCenter();
        mWeiboContent = impl.getWeiboContent();
        mQueryMap = new HashMap<>();
        mQueryMap.put("access_token","2.00LsUikFibNiyB492a953cfa6boMdC");
        mQueryMap.put("count","20");
        mQueryMap.put("page","1");
    }

    @Test
    public void testWeiboContent(){
        Call<ResponseBody> response = mWeiboContent.testListLastWeibo(mQueryMap);
        try {
            Response<ResponseBody> responseBody = response.execute();
            String content = responseBody.body().string();
            Assert.assertNotNull(content);
            Assert.assertFalse(content.contains("error"));
            Assert.assertTrue(content.contains("statuses"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWeiboContentRetrofit(){
        Observable<ContentsResponse> observable = mWeiboContent.listLastWeibo(mQueryMap);
        observable.subscribe(new Observer<ContentsResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("begin");
            }

            @Override
            public void onNext(ContentsResponse contentResponse) {
                Assert.assertNotNull(contentResponse);
                System.out.println("size:"+contentResponse.getStatuses().size());
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("completed");
            }
        });
    }
}
