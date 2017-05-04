package me.fallblank.weiworld.bean;

import java.util.Comparator;

/**
 * Created by fallb on 2017/4/28.
 */

public class WeiboReverseComparator implements Comparator<Weibo> {
    @Override
    public int compare(Weibo first, Weibo second) {
        long r = first.getId() - second.getId();
        if (r > 0) {
            return -1;
        } else if (r == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
