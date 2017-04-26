package me.fallblank.weiworld.util;

import android.view.View;

import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.bean.WeiboType;
import me.fallblank.weiworld.ui.adapter.holder.weibo.BaseWeiboHolder;
import me.fallblank.weiworld.ui.adapter.holder.weibo.OriginWeiboHolder;
import me.fallblank.weiworld.ui.adapter.holder.weibo.RetweetWeiboHolder;

/**
 * Created by fallb on 2017/4/18.
 */

public class ReuseHolderMethod {
    public static int getWeiboType(final Weibo weibo) {
        boolean isRet = weibo.isRetweet();
        if (isRet) {
            return WeiboType.RETWEET;
        }
        return WeiboType.ORIGINAL;
    }
    
    public static BaseWeiboHolder getWeiboHolder(final int type, final View view) {
        BaseWeiboHolder holder = null;
        switch (type) {
            case WeiboType.ORIGINAL:
                holder = new OriginWeiboHolder(view);
                break;
            case WeiboType.RETWEET:
                holder = new RetweetWeiboHolder(view);
                break;
            default:
                holder = new BaseWeiboHolder(view);
        }
        return holder;
    }
}
