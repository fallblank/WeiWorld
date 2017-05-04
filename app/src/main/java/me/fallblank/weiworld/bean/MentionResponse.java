package me.fallblank.weiworld.bean;

import java.util.List;

/**
 * Created by fallb on 2017/5/4.
 */

public class MentionResponse {
    
    private List<Weibo> statuses;
    private boolean hasvisible;
    private long previous_cursor;
    private long next_cursor;
    private int total_number;
    private int interval;
    
    public List<Weibo> getStatuses() {
        return statuses;
    }
    
    public void setStatuses(List<Weibo> statuses) {
        this.statuses = statuses;
    }
    
    public boolean isHasvisible() {
        return hasvisible;
    }
    
    public void setHasvisible(boolean hasvisible) {
        this.hasvisible = hasvisible;
    }
    
    public long getPrevious_cursor() {
        return previous_cursor;
    }
    
    public void setPrevious_cursor(long previous_cursor) {
        this.previous_cursor = previous_cursor;
    }
    
    public long getNext_cursor() {
        return next_cursor;
    }
    
    public void setNext_cursor(long next_cursor) {
        this.next_cursor = next_cursor;
    }
    
    public int getTotal_number() {
        return total_number;
    }
    
    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }
    
    public int getInterval() {
        return interval;
    }
    
    public void setInterval(int interval) {
        this.interval = interval;
    }
}
