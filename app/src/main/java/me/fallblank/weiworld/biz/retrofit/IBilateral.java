package me.fallblank.weiworld.biz.retrofit;

import java.util.Map;

import io.reactivex.Observable;
import me.fallblank.weiworld.bean.BilateralResponse;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by fallb on 2017/5/4.
 */

public interface IBilateral {
    @GET("2/statuses/bilateral_timeline.json")
    Observable<BilateralResponse> getBilateral(@QueryMap Map<String, String> queryMap);
}
