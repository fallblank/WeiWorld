package me.fallblank.weiworld.bean;

import java.util.List;

/**
 * Created by fallb on 2017/4/20.
 */

public class CommentsResponse extends BaseBean {
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
