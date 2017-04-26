package me.fallblank.weiworld.biz.retrofit;

import io.reactivex.Observable;
import me.fallblank.weiworld.bean.LoginUser;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fallb on 2017/4/24.
 * 获取授权用户信息
 */

public interface ILoginUser {
    @GET("2/users/show.json")
    Observable<LoginUser> getUser(@Query("access_token") String token,@Query("uid")String uid);
}