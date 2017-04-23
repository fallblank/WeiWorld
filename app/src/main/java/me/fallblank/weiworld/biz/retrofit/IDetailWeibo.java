package me.fallblank.weiworld.biz.retrofit;

import io.reactivex.Observable;
import me.fallblank.weiworld.bean.Weibo;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fallb on 2017/4/18.
 */

public interface IDetailWeibo {
    @GET("2/statuses/show.json")
    Observable<Weibo> getWeiboById(@Query("access_token") String accessToken, @Query("id") String idStr);
}
