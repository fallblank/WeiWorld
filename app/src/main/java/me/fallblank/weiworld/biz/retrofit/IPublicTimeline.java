package me.fallblank.weiworld.biz.retrofit;

import java.util.Map;

import io.reactivex.Observable;
import me.fallblank.weiworld.bean.ContentResponse;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by fallb on 2017/5/2.
 */

public interface IPublicTimeline {
    @GET("statuses/public_timeline.json")
    Observable<ContentResponse> getPublicContent(@QueryMap Map<String, String> queryMap);
}
