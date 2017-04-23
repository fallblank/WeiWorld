package me.fallblank.weiworld;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.fallblank.weiworld.bean.Emotion;
import me.fallblank.weiworld.biz.retrofit.IEmotionContent;
import me.fallblank.weiworld.impl.retrofit.RetrofitCenter;

/**
 * Created by fallb on 2017/4/13.
 */

public class EmotionContentTest {
    IEmotionContent mEmotionContent;
    Map<String, String> mQueryMap;

    @Before
    public void setup() {
        RetrofitCenter center = new RetrofitCenter();
        mEmotionContent = center.getEmotionContent();
        mQueryMap = new HashMap<>();
        mQueryMap.put("access_token", "2.00LsUikFibNiyB492a953cfa6boMdC");
    }

    @Test
    public void testGetEmotionTest() {
        mEmotionContent.emotions(mQueryMap)
                .subscribe(new Observer<List<Emotion>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("开始");
                    }

                    @Override
                    public void onNext(List<Emotion> emotions) {
                        Assert.assertNotNull(emotions);
                        System.out.println("length: " + emotions.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Assert.fail();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("成功");
                    }
                });
    }
}
