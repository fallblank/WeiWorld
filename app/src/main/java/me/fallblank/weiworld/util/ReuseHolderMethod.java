package me.fallblank.weiworld.util;

import android.view.View;

import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.bean.WeiboType;
import me.fallblank.weiworld.ui.adapter.holder.BaseWeiboHolder;
import me.fallblank.weiworld.ui.adapter.holder.PictureWeiboHolder;
import me.fallblank.weiworld.ui.adapter.holder.RetPictureWeiboHolder;
import me.fallblank.weiworld.ui.adapter.holder.RetTextWeiboHolder;
import me.fallblank.weiworld.ui.adapter.holder.RetWeiboHolder;
import me.fallblank.weiworld.ui.adapter.holder.TextWeiboHolder;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.i;

/**
 * Created by fallb on 2017/4/18.
 */

public class ReuseHolderMethod {
    public static int getWeiboType(final Weibo weibo) {
        boolean isRet = weibo.isRetweet();
        if (isRet == false) {
            boolean isPic = weibo.isContainPic();
            if (isPic) {
                return WeiboType.ORIGINAL_PICTURE;
            } else {
                return WeiboType.ORIGINAL;
            }
        } else {
            Weibo retWeibo = weibo.getRetweeted_status();
            boolean isPic = retWeibo.isContainPic();
            if (isPic) {
                return WeiboType.RETWEET_PICTURE;
            } else {
                return WeiboType.RETWEET;
            }
        }
    }

    public static BaseWeiboHolder getWeiboHolder(final int type, final View view) {
        BaseWeiboHolder holder = null;
        switch (type) {
            case WeiboType.ORIGINAL:
                holder = new BaseWeiboHolder(view);
                break;
            case WeiboType.ORIGINAL_PICTURE:
                holder = new PictureWeiboHolder(view);
                break;
            case WeiboType.ORIGINAL_PLAIN_TEXT:
                holder = new TextWeiboHolder(view);
                break;
            case WeiboType.RETWEET:
                holder = new RetWeiboHolder(view);
                break;
            case WeiboType.RETWEET_PICTURE:
                holder = new RetPictureWeiboHolder(view);
                break;
            case WeiboType.RETWEET_PLAIN_TEXT:
                holder = new RetTextWeiboHolder(view);
                break;
            default:
                holder = new BaseWeiboHolder(view);
        }
        return holder;
    }
}
