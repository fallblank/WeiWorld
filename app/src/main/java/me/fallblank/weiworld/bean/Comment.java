package me.fallblank.weiworld.bean;

import java.util.Date;

/**
 * Created by fallb on 2017/5/3.
 */

public class Comment extends BaseBean {
    
    private String idstr;
    private long rootid;
    private Date created_at;
    private String mid;
    private ReplyCommentBean reply_comment;
    private String text;
    private long id;
    
    public Weibo getStatus() {
        return status;
    }
    
    public void setStatus(Weibo status) {
        this.status = status;
    }
    
    private Weibo status;
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    private User user;
    
    public String getIdstr() {
        return idstr;
    }
    
    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }
    
    public long getRootid() {
        return rootid;
    }
    
    public void setRootid(long rootid) {
        this.rootid = rootid;
    }
    
    public Date getCreated_at() {
        return created_at;
    }
    
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    
    public String getMid() {
        return mid;
    }
    
    public void setMid(String mid) {
        this.mid = mid;
    }
    
    public ReplyCommentBean getReply_comment() {
        return reply_comment;
    }
    
    public void setReply_comment(ReplyCommentBean reply_comment) {
        this.reply_comment = reply_comment;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public static class ReplyCommentBean {
        private String idstr;
        private long rootid;
        private Date created_at;
        private String mid;
        private long id;
        private String text;
        
        public User getUser() {
            return user;
        }
        
        public void setUser(User user) {
            this.user = user;
        }
        
        private User user;
        
        
        public String getIdstr() {
            return idstr;
        }
        
        public void setIdstr(String idstr) {
            this.idstr = idstr;
        }
        
        public long getRootid() {
            return rootid;
        }
        
        public void setRootid(long rootid) {
            this.rootid = rootid;
        }
        
        public Date getCreated_at() {
            return created_at;
        }
        
        public void setCreated_at(Date created_at) {
            this.created_at = created_at;
        }
        
        public String getMid() {
            return mid;
        }
        
        public void setMid(String mid) {
            this.mid = mid;
        }
        
        public long getId() {
            return id;
        }
        
        public void setId(long id) {
            this.id = id;
        }
        
        public String getText() {
            return text;
        }
        
        public void setText(String text) {
            this.text = text;
        }
    }
}
