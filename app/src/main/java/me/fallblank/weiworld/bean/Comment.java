package me.fallblank.weiworld.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by fallb on 2017/4/20.
 */

public class Comment extends BaseBean {

    /**
     * created_at : Thu Apr 20 15:28:21 +0800 2017
     * id : 4098631248791718
     * rootid : 4098629541518124
     * floor_number : 0
     * text : 回复@鸡蛋_煎饼子:我也觉得啊，断电三日全宿舍出去住都够了，好吃好喝吹空调能洗澡有电视不断电不断网
     * source_allowclick : 0
     * source_type : 1
     * source : <a href="http://app.weibo.com/t/feed/9ksdit" rel="nofollow">iPhone客户端</a>
     */

    private Date created_at;
    private long id;
    private long rootid;
    private int floor_number;
    private String text;
    private int source_allowclick;
    private int source_type;
    private String source;
    private User user;
    @SerializedName("status")
    private Weibo weibo;


    public Date getCreated_at() {
        return created_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Weibo getWeibo() {
        return weibo;
    }

    public void setWeibo(Weibo weibo) {
        this.weibo = weibo;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRootid() {
        return rootid;
    }

    public void setRootid(long rootid) {
        this.rootid = rootid;
    }

    public int getFloor_number() {
        return floor_number;
    }

    public void setFloor_number(int floor_number) {
        this.floor_number = floor_number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSource_allowclick() {
        return source_allowclick;
    }

    public void setSource_allowclick(int source_allowclick) {
        this.source_allowclick = source_allowclick;
    }

    public int getSource_type() {
        return source_type;
    }

    public void setSource_type(int source_type) {
        this.source_type = source_type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
