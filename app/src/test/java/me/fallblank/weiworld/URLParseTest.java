package me.fallblank.weiworld;

import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;

import me.fallblank.weiworld.util.URLParser;

/**
 * Created by fallb on 2017/4/8.
 */

public class URLParseTest {

    @Test
    public void testGetURLDir(){
        String name = URLParser.getURLFilename("http://wx2.sinaimg.cn/large/4ee36f05ly1fef0uwuwukj20hg0dqaby.jpg");
        Assert.assertEquals(name,"4ee36f05ly1fef0uwuwukj20hg0dqaby.jpg");
        String empty = URLParser.getURLFilename("/");
        Assert.assertEquals(empty,"");
    }
}
