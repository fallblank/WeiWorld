package me.fallblank.weiworld;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.fallblank.weiworld.bean.MentionResponse;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.biz.retrofit.IMention;
import me.fallblank.weiworld.impl.retrofit.RetrofitCenter;

/**
 * Created by fallb on 2017/5/4.
 */

public class MentionResponseTest {
    private IMention mIMention;
    private Map<String, String> queryMap = new HashMap<>();
    
    @Before
    public void setup() {
        mIMention = new RetrofitCenter().getMention();
        queryMap.put("access_token", Constant.ACCESS_TOKEN);
    }
    
    @Test
    public void testGetMention() {
        mIMention.getMention(queryMap)
                .subscribe(new Observer<MentionResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("开始");
                    }
                    
                    @Override
                    public void onNext(MentionResponse mentionResponse) {
                        System.out.println("next cursor: " + mentionResponse.getNext_cursor());
                        for (Weibo weibo : mentionResponse.getStatuses()) {
                            System.out.println(weibo.getUser().getScreen_name() + ":" + weibo.getText());
                            System.out.println("************************");
                        }
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        System.out.println("失败");
                    }
                    
                    @Override
                    public void onComplete() {
                        System.out.println("完成");
                    }
                });
    }
}
