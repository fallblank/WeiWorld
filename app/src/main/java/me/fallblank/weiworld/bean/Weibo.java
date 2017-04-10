package me.fallblank.weiworld.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import me.fallblank.weiworld.util.URLParser;


/**
 * Created by fallb on 2017/3/31.
 */

public class Weibo extends BaseBean {

    /**
     * created_at : Thu Mar 30 15:19:53 +0800 2017
     * id : 4091018972064051
     * mid : 4091018972064051
     * idstr : 4091018972064051
     * text : test http://t.cn/8kdKSdH ​
     * textLength : 24
     * source_allowclick : 1
     * source_type : 2
     * source : <a href="http://weibo.com/" rel="nofollow">荣耀6</a>
     * favorited : false
     * truncated : false
     * in_reply_to_status_id :
     * in_reply_to_user_id :
     * in_reply_to_screen_name :
     * pic_urls : [{"thumbnail_pic":"http://wx2.sinaimg.cn/thumbnail/eba694c2ly1fe4w5et1byj205a04kq2u.jpg"}]
     * thumbnail_pic : http://wx2.sinaimg.cn/thumbnail/eba694c2ly1fe4w5et1byj205a04kq2u.jpg
     * bmiddle_pic : http://wx2.sinaimg.cn/bmiddle/eba694c2ly1fe4w5et1byj205a04kq2u.jpg
     * original_pic : http://wx2.sinaimg.cn/large/eba694c2ly1fe4w5et1byj205a04kq2u.jpg
     * geo : {"type":"Point","coordinates":[34.122504663004,108.82896051717]}
     * annotations : [{"place":{"lon":108.82896051717,"poiid":"B2094656D26DA0F44892","title":"西安电子科技大学长安校区","type":"checkin","lat":34.122504663004},"client_mblogid":"8bc2fe73-de93-428a-b592-30af83b62734"},{"mapi_request":true}]
     * reposts_count : 0
     * comments_count : 0
     * attitudes_count : 0
     * isLongText : false
     * mlevel : 0
     * visible : {"type":0,"list_id":0}
     * biz_ids : [100101]
     * biz_feature : 4294967300
     * hasActionTypeCard : 0
     * darwin_tags : []
     * hot_weibo_tags : []
     * text_tag_tips : []
     * rid : 1_0_1_2789534374911340798
     * userType : 0
     * positive_recom_flag : 0
     * gif_ids :
     * is_show_bulletin : 2
     */

    private Date created_at;
    private long id;
    private String mid;
    private String idstr;
    private String text;
    private int textLength;
    private int source_allowclick;
    private int source_type;
    private String source;
    private boolean favorited;
    private boolean truncated;
    private String thumbnail_pic;
    private String bmiddle_pic;
    private String original_pic;
    private GeoBean geo;
    @SerializedName("user")
    private User user;

    //当微博是转发内容时，存在该字段
    private Weibo retweeted_status;
    private int reposts_count;
    private int comments_count;
    private int attitudes_count;
    private boolean isLongText;
    private VisibleBean visible;
    private long biz_feature;
    private int hasActionTypeCard;
    private String rid;
    private int userType;
    private int positive_recom_flag;
    private String gif_ids;
    private int is_show_bulletin;
    private List<PicUrlsBean> pic_urls;
    private List<AnnotationsBean> annotations;

    public Date getCreated_at() {
        return created_at;
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

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextLength() {
        return textLength;
    }

    public void setTextLength(int textLength) {
        this.textLength = textLength;
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

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public String getThumbnail_pic() {
        return thumbnail_pic;
    }

    public void setThumbnail_pic(String thumbnail_pic) {
        this.thumbnail_pic = thumbnail_pic;
    }

    public User getUser() {
        return user;
    }

    public Weibo getRetweeted_status() {
        return retweeted_status;
    }

    public void setRetweeted_status(Weibo retweeted_status) {
        this.retweeted_status = retweeted_status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLongText() {
        return isLongText;
    }

    public void setLongText(boolean longText) {
        isLongText = longText;
    }

    public String getBmiddle_pic() {
        return bmiddle_pic;
    }

    public void setBmiddle_pic(String bmiddle_pic) {
        this.bmiddle_pic = bmiddle_pic;
    }

    public String getOriginal_pic() {
        return original_pic;
    }

    public void setOriginal_pic(String original_pic) {
        this.original_pic = original_pic;
    }

    public GeoBean getGeo() {
        return geo;
    }

    public void setGeo(GeoBean geo) {
        this.geo = geo;
    }

    public int getReposts_count() {
        return reposts_count;
    }

    public void setReposts_count(int reposts_count) {
        this.reposts_count = reposts_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getAttitudes_count() {
        return attitudes_count;
    }

    public void setAttitudes_count(int attitudes_count) {
        this.attitudes_count = attitudes_count;
    }

    public boolean isIsLongText() {
        return isLongText;
    }

    public void setIsLongText(boolean isLongText) {
        this.isLongText = isLongText;
    }

    public VisibleBean getVisible() {
        return visible;
    }

    public void setVisible(VisibleBean visible) {
        this.visible = visible;
    }

    public long getBiz_feature() {
        return biz_feature;
    }

    public void setBiz_feature(long biz_feature) {
        this.biz_feature = biz_feature;
    }

    public int getHasActionTypeCard() {
        return hasActionTypeCard;
    }

    public void setHasActionTypeCard(int hasActionTypeCard) {
        this.hasActionTypeCard = hasActionTypeCard;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getPositive_recom_flag() {
        return positive_recom_flag;
    }

    public void setPositive_recom_flag(int positive_recom_flag) {
        this.positive_recom_flag = positive_recom_flag;
    }

    public String getGif_ids() {
        return gif_ids;
    }

    public void setGif_ids(String gif_ids) {
        this.gif_ids = gif_ids;
    }

    public int getIs_show_bulletin() {
        return is_show_bulletin;
    }

    public void setIs_show_bulletin(int is_show_bulletin) {
        this.is_show_bulletin = is_show_bulletin;
    }

    public List<PicUrlsBean> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<PicUrlsBean> pic_urls) {
        this.pic_urls = pic_urls;
    }

    public List<AnnotationsBean> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationsBean> annotations) {
        this.annotations = annotations;
    }

    public static class GeoBean {
        /**
         * type : Point
         * coordinates : [34.122504663004,108.82896051717]
         */

        private String type;
        private List<Double> coordinates;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Double> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }
    }

    public static class VisibleBean {
        /**
         * type : 0
         * list_id : 0
         */

        private int type;
        private int list_id;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getList_id() {
            return list_id;
        }

        public void setList_id(int list_id) {
            this.list_id = list_id;
        }
    }

    public static class PicUrlsBean {
        /**
         * thumbnail_pic : http://wx2.sinaimg.cn/thumbnail/eba694c2ly1fe4w5et1byj205a04kq2u.jpg
         */

        private String thumbnail_pic;

        public String getThumbnail_pic() {
            return thumbnail_pic;
        }

        public void setThumbnail_pic(String thumbnail_pic) {
            this.thumbnail_pic = thumbnail_pic;
        }
    }

    public static class AnnotationsBean {
        /**
         * place : {"lon":108.82896051717,"poiid":"B2094656D26DA0F44892","title":"西安电子科技大学长安校区","type":"checkin","lat":34.122504663004}
         * client_mblogid : 8bc2fe73-de93-428a-b592-30af83b62734
         * mapi_request : true
         */

        private PlaceBean place;
        private String client_mblogid;
        private boolean mapi_request;

        public PlaceBean getPlace() {
            return place;
        }

        public void setPlace(PlaceBean place) {
            this.place = place;
        }

        public String getClient_mblogid() {
            return client_mblogid;
        }

        public void setClient_mblogid(String client_mblogid) {
            this.client_mblogid = client_mblogid;
        }

        public boolean isMapi_request() {
            return mapi_request;
        }

        public void setMapi_request(boolean mapi_request) {
            this.mapi_request = mapi_request;
        }

        public static class PlaceBean {
            /**
             * lon : 108.82896051717
             * poiid : B2094656D26DA0F44892
             * title : 西安电子科技大学长安校区
             * type : checkin
             * lat : 34.122504663004
             */

            private double lon;
            private String poiid;
            private String title;
            private String type;
            private double lat;

            public double getLon() {
                return lon;
            }

            public void setLon(double lon) {
                this.lon = lon;
            }

            public String getPoiid() {
                return poiid;
            }

            public void setPoiid(String poiid) {
                this.poiid = poiid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }
        }
    }

    /**
     * 判断该微博是否是转发
     *
     * @return true转发微博；false原创微博
     */
    public boolean isRetweet() {
        if (null != retweeted_status) {
            return true;
        }
        return false;
    }

    /**
     * 判断改微博是否包含图片。
     *
     * @return true 包含；false 不包含
     */
    public boolean isContainPic() {
        if (null == pic_urls || pic_urls.size() == 0) {
            return false;
        }
        return true;
    }

    private String baseThumnbnail;
    private String baseBMiddle;
    private String baseOrigin;

    /**
     * 获取基础地址
     */
    public String getThuBaseUrl(){
        if (null == baseThumnbnail){
            baseThumnbnail = URLParser.getBaseUrl(thumbnail_pic);
        }
        return baseThumnbnail;
    }

    public String getBmidBaseUrl(){
        if (null == baseBMiddle){
            baseBMiddle = URLParser.getBaseUrl(bmiddle_pic);
        }
        return baseBMiddle;
    }

    public String getOriBaseUrl() {
        if (null == baseOrigin){
            baseOrigin = URLParser.getBaseUrl(original_pic);
        }
        return baseOrigin;
    }
}
