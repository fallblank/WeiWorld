package me.fallblank.weiworld.bean;

import java.util.Comparator;

/**
 * Created by fallb on 2017/4/6.
 */

public class WeiboComparator implements Comparator<Weibo> {

    @Override
    public int compare(Weibo first, Weibo second) {
        int r = first.getCreated_at().compareTo(second.getCreated_at());
        return r;
    }
}
