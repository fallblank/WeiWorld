package me.fallblank.weiworld.bean;

/**
 * Created by fallb on 2017/5/8.
 */

public class SourceBean extends BaseBean {
    private String url;
    private String name;
    private String author;
    private String license;
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getLicense() {
        return license;
    }
    
    public void setLicense(String license) {
        this.license = license;
    }
}
