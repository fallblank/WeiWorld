package me.fallblank.weiworld.biz.retrofit;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import me.fallblank.weiworld.bean.Emotion;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by fallb on 2017/4/13.
 */

public interface IEmotionContent {

    @GET("2/emotions.json")
    public Observable<List<Emotion>> emotions(@QueryMap Map<String, String> queryMap);
}
