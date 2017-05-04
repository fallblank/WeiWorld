package me.fallblank.weiworld.biz.retrofit;

import java.util.Map;

import io.reactivex.Observable;
import me.fallblank.weiworld.bean.MentionResponse;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by fallb on 2017/5/4.
 */

public interface IMention {
    @GET("2/statuses/mentions.json")
    Observable<MentionResponse> getMention(@QueryMap Map<String, String> queryMap);
}
