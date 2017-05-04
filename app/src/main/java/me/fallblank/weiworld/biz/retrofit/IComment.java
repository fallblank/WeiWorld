package me.fallblank.weiworld.biz.retrofit;

import java.util.Map;

import io.reactivex.Observable;
import me.fallblank.weiworld.bean.CommentResponse;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by fallb on 2017/5/3.
 */

public interface IComment {
    @GET("comments/to_me.json")
    Observable<CommentResponse> getComment(@QueryMap Map<String, String> query);
}
