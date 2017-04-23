package me.fallblank.weiworld;

import org.junit.Assert;
import org.junit.Test;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.biz.retrofit.IDetailWeibo;
import me.fallblank.weiworld.impl.retrofit.RetrofitCenter;

/**
 * Created by fallb on 2017/4/18.
 */

public class DetailWeiboTest {

    @Test
    public void testGetWeiboById(){
        RetrofitCenter center = new RetrofitCenter();
        IDetailWeibo detailWeibo = center.getDetailWeibo();
        detailWeibo.getWeiboById(Constant.ACCESS_TOKEN,"4097887484297067")
                .subscribe(new Observer<Weibo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("开始");
                    }

                    @Override
                    public void onNext(Weibo weibo) {
                        System.out.println("weibo: "+weibo.getText());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Assert.fail();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("完成");
                    }
                });
    }
}
