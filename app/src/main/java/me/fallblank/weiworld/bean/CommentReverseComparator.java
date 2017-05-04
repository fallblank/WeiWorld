package me.fallblank.weiworld.bean;

import java.util.Comparator;

/**
 * Created by fallb on 2017/5/4.
 */

public class CommentReverseComparator implements Comparator<Comment> {
    @Override
    public int compare(Comment first, Comment second) {
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
