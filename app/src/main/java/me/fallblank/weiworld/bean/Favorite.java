package me.fallblank.weiworld.bean;

import java.util.List;

/**
 * Created by fallb on 2017/4/25.
 */

public class Favorite extends BaseBean {
    
    
    private int total_number;
    private List<FavoritesBean> favorites;
    
    public int getTotal_number() {
        return total_number;
    }
    
    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }
    
    public List<FavoritesBean> getFavorites() {
        return favorites;
    }
    
    public void setFavorites(List<FavoritesBean> favorites) {
        this.favorites = favorites;
    }
    
    public static class FavoritesBean extends BaseBean{
        /**
         * status : {"created_at":"Mon Apr 17 07:17:30 +0800 2017","id":4097420558115551,"mid":"4097420558115551","idstr":"4097420558115551","text":"写得不错。","source_allowclick":0,"source_type":1,"source":"<a href=\"http://app.weibo.com/t/feed/6e3owN\" rel=\"nofollow\">iPhone 7 Plus<\/a>","favorited":true,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","pic_urls":[],"geo":null,"user":{"id":1503535883,"idstr":"1503535883","class":1,"screen_name":"廖祜秋liaohuqiu_秋百万","name":"廖祜秋liaohuqiu_秋百万","province":"11","city":"5","location":"北京 朝阳区","description":"","url":"http://liaohuqiu.net","profile_image_url":"http://tva4.sinaimg.cn/crop.0.15.750.750.50/599e230bjw8f9vgmh9daoj20ku0lpta6.jpg","cover_image_phone":"http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg","profile_url":"liaohuqiu","domain":"liaohuqiu","weihao":"","gender":"m","followers_count":13218,"friends_count":802,"pagefriends_count":2,"statuses_count":2769,"favourites_count":136,"created_at":"Thu Jul 08 17:12:18 +0800 2010","following":true,"allow_all_act_msg":true,"geo_enabled":true,"verified":true,"verified_type":0,"remark":"","insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva4.sinaimg.cn/crop.0.15.750.750.180/599e230bjw8f9vgmh9daoj20ku0lpta6.jpg","avatar_hd":"http://tva4.sinaimg.cn/crop.0.15.750.750.1024/599e230bjw8f9vgmh9daoj20ku0lpta6.jpg","verified_reason":"互联网博主","verified_trade":"1182","verified_reason_url":"","verified_source":"","verified_source_url":"","verified_state":0,"verified_level":3,"verified_type_ext":0,"has_service_tel":false,"verified_reason_modified":"","verified_contact_name":"","verified_contact_email":"","verified_contact_mobile":"","follow_me":false,"online_status":0,"bi_followers_count":533,"lang":"zh-cn","star":0,"mbtype":12,"mbrank":5,"block_word":1,"block_app":1,"credit_score":80,"user_ability":2572,"urank":37},"retweeted_status":{"created_at":"Sun Apr 16 23:15:23 +0800 2017","id":4097299230200157,"mid":"4097299230200157","idstr":"4097299230200157","text":"我在简书新发表了文章《拆 JakeWharton 系列之 ButterKnife》http://t.cn/RXVvSpQ @googdev @廖祜秋liaohuqiu_秋百万 @Trinea @代码家 \u200b","textLength":125,"source_allowclick":0,"source_type":1,"source":"<a href=\"http://app.weibo.com/t/feed/4rvb1w\" rel=\"nofollow\">简书iPhone客户端<\/a>","favorited":false,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","pic_urls":[],"geo":null,"user":{"id":2663228541,"idstr":"2663228541","class":1,"screen_name":"geniusmart","name":"geniusmart","province":"35","city":"1","location":"福建 福州","description":"Androooooooooooooooid","url":"http://www.jianshu.com/users/9fa7fc2f3733/","profile_image_url":"http://tva2.sinaimg.cn/crop.364.84.576.576.50/9ebda47dgw1eqtoyod62gj20qo0qnjvk.jpg","profile_url":"geniusmart","domain":"geniusmart","weihao":"","gender":"m","followers_count":354,"friends_count":273,"pagefriends_count":8,"statuses_count":175,"favourites_count":880,"created_at":"Wed Mar 14 22:10:05 +0800 2012","following":true,"allow_all_act_msg":false,"geo_enabled":true,"verified":false,"verified_type":-1,"remark":"","insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva2.sinaimg.cn/crop.364.84.576.576.180/9ebda47dgw1eqtoyod62gj20qo0qnjvk.jpg","avatar_hd":"http://tva2.sinaimg.cn/crop.364.84.576.576.1024/9ebda47dgw1eqtoyod62gj20qo0qnjvk.jpg","verified_reason":"","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","follow_me":false,"online_status":0,"bi_followers_count":18,"lang":"zh-cn","star":0,"mbtype":11,"mbrank":1,"block_word":0,"block_app":1,"credit_score":80,"user_ability":0,"urank":14},"annotations":[{"shooting":1,"client_mblogid":"iPhone-E116BB71-1A68-4578-8277-04F18AF77071","mapi_request":true},{"mapi_request":true}],"reposts_count":42,"comments_count":5,"attitudes_count":7,"isLongText":false,"mlevel":0,"visible":{"type":0,"list_id":0},"biz_feature":0,"hasActionTypeCard":0,"darwin_tags":[],"hot_weibo_tags":[],"text_tag_tips":[],"userType":0,"positive_recom_flag":0,"gif_ids":"","is_show_bulletin":2},"annotations":[{"mapi_request":true}],"reposts_count":7,"comments_count":0,"attitudes_count":8,"isLongText":false,"mlevel":0,"visible":{"type":0,"list_id":0},"biz_feature":0,"hasActionTypeCard":0,"darwin_tags":[],"hot_weibo_tags":[],"text_tag_tips":[],"userType":0,"positive_recom_flag":0,"gif_ids":"","is_show_bulletin":2}
         * tags : []
         * favorited_time : Mon Apr 17 07:54:00 +0800 2017
         */
        
        private Weibo status;
        private String favorited_time;
        private List<Tag> tags;
        
        public Weibo getStatus() {
            return status;
        }
        
        public void setStatus(Weibo status) {
            this.status = status;
        }
        
        public String getFavorited_time() {
            return favorited_time;
        }
        
        public void setFavorited_time(String favorited_time) {
            this.favorited_time = favorited_time;
        }
        
        public List<Tag> getTags() {
            return tags;
        }
        
        public void setTags(List<Tag> tags) {
            this.tags = tags;
        }
        
        private static class Tag {
            /**
             * type 取值，0：普通微博，1：私密微博，3：指定分组微博，4：密友微博
             */
            public int id;
            /**
             * 分组的组号
             */
            public String tag;
            
            public int getId() {
                return id;
            }
            
            public void setId(int id) {
                this.id = id;
            }
            
            public String getTag() {
                return tag;
            }
            
            public void setTag(String tag) {
                this.tag = tag;
            }
        }
    }
}
