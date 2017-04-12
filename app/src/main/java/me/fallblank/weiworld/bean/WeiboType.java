package me.fallblank.weiworld.bean;

/**
 * Created by fallb on 2017/4/7.
 */

public final class WeiboType {
    /*100的是原创微博*/
    public static final int TYPE_ORIGIN = 1;
    public static final int ORIGINAL = 100;
    public static final int ORIGINAL_PICTURE = 101;
    public static final int  ORIGINAL_PLAIN_TEXT = 103;

    /*200开头的是带有转发的微博*/
    public static final int TYPE_RETWEET = 2;
    public static final int RETWEET = 200;
    public static final int RETWEET_PICTURE = 201;
    public static final int RETWEET_PLAIN_TEXT = 202;

}
