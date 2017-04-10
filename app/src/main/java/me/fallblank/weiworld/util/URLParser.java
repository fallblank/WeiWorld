package me.fallblank.weiworld.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by fallb on 2017/4/8.
 */

public class URLParser {

    //网址分隔符
    private static final String URL_SEPARATOR = "/";

    public static String getURLFilename(String url) {
        int index = url.lastIndexOf("/");
        if (index < 0) {
            return url;
        }
        return url.substring(index+1,url.length());
    }

    /**
     * 获取网址的基础地址，用于拼接
     *
     * @return 基础地址
     */
    @Nullable
    public static String getBaseUrl(String url) {
        if (null == url) {
            return null;
        }
        int last = url.lastIndexOf(URL_SEPARATOR);
        if (last < 0) {
            return null;
        }
        //保留最后一个分隔符
        String base = url.substring(0, last + 1);
        return base;
    }
}
