package me.fallblank.weiworld;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import me.fallblank.weiworld.bean.Weibo;

/**
 * Created by fallb on 2017/4/10.
 */

public class WeiboPictureTest {
    Weibo mWeibo;
    @Before
    public void setup(){
        mWeibo = new Weibo();
        mWeibo.setThumbnail_pic("http://wx2.sinaimg.cn/thumbnail/eba694c2ly1fe4w5et1byj205a04kq2u.jpg");
        mWeibo.setBmiddle_pic("http://wx2.sinaimg.cn/bmiddle/eba694c2ly1fe4w5et1byj205a04kq2u.jpg");
        mWeibo.setOriginal_pic("http://wx2.sinaimg.cn/large/eba694c2ly1fe4w5et1byj205a04kq2u.jpg");
    }

    @Test
    public void testBaseURL(){
        Assert.assertEquals(mWeibo.getThuBaseUrl(),"http://wx2.sinaimg.cn/thumbnail/");
        Assert.assertEquals(mWeibo.getBmidBaseUrl(),"http://wx2.sinaimg.cn/bmiddle/");
        Assert.assertEquals(mWeibo.getOriBaseUrl(),"http://wx2.sinaimg.cn/large/");
    }
}
