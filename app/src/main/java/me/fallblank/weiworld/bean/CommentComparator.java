package me.fallblank.weiworld.bean;

import java.util.Comparator;

/**
 * Created by fallb on 2017/4/20.
 */

public class CommentComparator implements Comparator<Comment> {
    @Override
    public int compare(Comment first, Comment second) {
        long r = first.getId()-second.getId();
        if (r > 0){
            return -1;
        }else if (r == 0){
            return 0;
        }else {
            return 1;
        }
    }
}
