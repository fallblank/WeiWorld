package me.fallblank.weiworld.biz.retrofit;

import java.util.Map;

import io.reactivex.Observable;
import me.fallblank.weiworld.bean.ContentResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by fallb on 2017/3/31.
 * @author fallblnk
 * retrofit使用的微博内容获取接口
 */

public interface IWeiboContent {
    @GET("2/statuses/home_timeline.json")
    Observable<ContentResponse> listLastWeibo(@QueryMap Map<String,String> query);

    @GET("2/statuses/home_timeline.json")
    Call<ResponseBody> testListLastWeibo(@QueryMap Map<String,String> query);
}
