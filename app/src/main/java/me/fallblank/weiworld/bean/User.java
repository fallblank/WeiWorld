package me.fallblank.weiworld.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by fallb on 2017/3/31.
 * @author fallblank
 * 完成对微博用户包含信息的封装。
 */

public class User implements Serializable {


    /**
     * id : 5191033181
     * idstr : 5191033181
     * class : 1
     * screen_name : 开发者头条
     * name : 开发者头条
     * province : 32
     * city : 1
     * location : 江苏 南京
     * description : http://toutiao.io/ 开发者头条 - 开发者的首选阅读分享平台 微信号：kaifazhetoutiao
     * url : http://toutiao.io/
     * profile_image_url : http://tva2.sinaimg.cn/crop.0.0.1022.1022.50/005Fj2RDgw1ex5pinpg65j30sg0sgdg9.jpg
     * cover_image : http://ww1.sinaimg.cn/crop.0.126.980.300/005Fj2RDgw1ekvmn8n9qaj30r80fbq8y.jpg
     * cover_image_phone : http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg
     * profile_url : kaifazhetoutiao
     * domain : kaifazhetoutiao
     * weihao :
     * gender : m
     * followers_count : 42807
     * friends_count : 4323
     * pagefriends_count : 0
     * statuses_count : 15603
     * favourites_count : 1179
     * created_at : Tue Jun 24 11:10:23 +0800 2014
     * following : true
     * allow_all_act_msg : false
     * geo_enabled : false
     * verified : true
     * verified_type : 2
     * remark :
     * insecurity : {"sexual_content":false}
     * ptype : 0
     * allow_all_comment : true
     * avatar_large : http://tva2.sinaimg.cn/crop.0.0.1022.1022.180/005Fj2RDgw1ex5pinpg65j30sg0sgdg9.jpg
     * avatar_hd : http://tva2.sinaimg.cn/crop.0.0.1022.1022.1024/005Fj2RDgw1ex5pinpg65j30sg0sgdg9.jpg
     * verified_reason : 南京无印信息技术有限公司
     * verified_trade :
     * verified_reason_url :
     * verified_source :
     * verified_source_url :
     * verified_state : 0
     * verified_level : 3
     * verified_type_ext : 0
     * pay_remind : 0
     * pay_date : 20170322
     * has_service_tel : false
     * verified_reason_modified :
     * verified_contact_name :
     * verified_contact_email :
     * verified_contact_mobile :
     * follow_me : false
     * online_status : 0
     * bi_followers_count : 3123
     * lang : zh-cn
     * star : 0
     * mbtype : 11
     * mbrank : 5
     * block_word : 1
     * block_app : 1
     * credit_score : 80
     * user_ability : 516
     * cardid : star_005
     * urank : 35
     */

    private long id;
    private String idstr;
    @SerializedName("class")
    private int classX;
    private String screen_name;
    private String name;
    private String province;
    private String city;
    private String location;
    private String description;
    private String url;
    private String profile_image_url;
    private String cover_image;
    private String cover_image_phone;
    private String profile_url;
    private String domain;
    private String weihao;
    private String gender;
    private int followers_count;
    private int friends_count;
    private int pagefriends_count;
    private int statuses_count;
    private int favourites_count;
    private String created_at;
    private boolean following;
    private boolean allow_all_act_msg;
    private boolean geo_enabled;
    private boolean verified;
    private int verified_type;
    private String remark;
    private InsecurityBean insecurity;
    private int ptype;
    private boolean allow_all_comment;
    private String avatar_large;
    private String avatar_hd;
    private String verified_reason;
    private String verified_trade;
    private String verified_reason_url;
    private String verified_source;
    private String verified_source_url;
    private int verified_state;
    private int verified_level;
    private int verified_type_ext;
    private int pay_remind;
    private String pay_date;
    private boolean has_service_tel;
    private String verified_reason_modified;
    private String verified_contact_name;
    private String verified_contact_email;
    private String verified_contact_mobile;
    private boolean follow_me;
    private int online_status;
    private int bi_followers_count;
    private String lang;
    private int star;
    private int mbtype;
    private int mbrank;
    private int block_word;
    private int block_app;
    private int credit_score;
    private int user_ability;
    private String cardid;
    private int urank;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public int getClassX() {
        return classX;
    }

    public void setClassX(int classX) {
        this.classX = classX;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getCover_image_phone() {
        return cover_image_phone;
    }

    public void setCover_image_phone(String cover_image_phone) {
        this.cover_image_phone = cover_image_phone;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getWeihao() {
        return weihao;
    }

    public void setWeihao(String weihao) {
        this.weihao = weihao;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public void setFriends_count(int friends_count) {
        this.friends_count = friends_count;
    }

    public int getPagefriends_count() {
        return pagefriends_count;
    }

    public void setPagefriends_count(int pagefriends_count) {
        this.pagefriends_count = pagefriends_count;
    }

    public int getStatuses_count() {
        return statuses_count;
    }

    public void setStatuses_count(int statuses_count) {
        this.statuses_count = statuses_count;
    }

    public int getFavourites_count() {
        return favourites_count;
    }

    public void setFavourites_count(int favourites_count) {
        this.favourites_count = favourites_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public boolean isAllow_all_act_msg() {
        return allow_all_act_msg;
    }

    public void setAllow_all_act_msg(boolean allow_all_act_msg) {
        this.allow_all_act_msg = allow_all_act_msg;
    }

    public boolean isGeo_enabled() {
        return geo_enabled;
    }

    public void setGeo_enabled(boolean geo_enabled) {
        this.geo_enabled = geo_enabled;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public int getVerified_type() {
        return verified_type;
    }

    public void setVerified_type(int verified_type) {
        this.verified_type = verified_type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public InsecurityBean getInsecurity() {
        return insecurity;
    }

    public void setInsecurity(InsecurityBean insecurity) {
        this.insecurity = insecurity;
    }

    public int getPtype() {
        return ptype;
    }

    public void setPtype(int ptype) {
        this.ptype = ptype;
    }

    public boolean isAllow_all_comment() {
        return allow_all_comment;
    }

    public void setAllow_all_comment(boolean allow_all_comment) {
        this.allow_all_comment = allow_all_comment;
    }

    public String getAvatar_large() {
        return avatar_large;
    }

    public void setAvatar_large(String avatar_large) {
        this.avatar_large = avatar_large;
    }

    public String getAvatar_hd() {
        return avatar_hd;
    }

    public void setAvatar_hd(String avatar_hd) {
        this.avatar_hd = avatar_hd;
    }

    public String getVerified_reason() {
        return verified_reason;
    }

    public void setVerified_reason(String verified_reason) {
        this.verified_reason = verified_reason;
    }

    public String getVerified_trade() {
        return verified_trade;
    }

    public void setVerified_trade(String verified_trade) {
        this.verified_trade = verified_trade;
    }

    public String getVerified_reason_url() {
        return verified_reason_url;
    }

    public void setVerified_reason_url(String verified_reason_url) {
        this.verified_reason_url = verified_reason_url;
    }

    public String getVerified_source() {
        return verified_source;
    }

    public void setVerified_source(String verified_source) {
        this.verified_source = verified_source;
    }

    public String getVerified_source_url() {
        return verified_source_url;
    }

    public void setVerified_source_url(String verified_source_url) {
        this.verified_source_url = verified_source_url;
    }

    public int getVerified_state() {
        return verified_state;
    }

    public void setVerified_state(int verified_state) {
        this.verified_state = verified_state;
    }

    public int getVerified_level() {
        return verified_level;
    }

    public void setVerified_level(int verified_level) {
        this.verified_level = verified_level;
    }

    public int getVerified_type_ext() {
        return verified_type_ext;
    }

    public void setVerified_type_ext(int verified_type_ext) {
        this.verified_type_ext = verified_type_ext;
    }

    public int getPay_remind() {
        return pay_remind;
    }

    public void setPay_remind(int pay_remind) {
        this.pay_remind = pay_remind;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public boolean isHas_service_tel() {
        return has_service_tel;
    }

    public void setHas_service_tel(boolean has_service_tel) {
        this.has_service_tel = has_service_tel;
    }

    public String getVerified_reason_modified() {
        return verified_reason_modified;
    }

    public void setVerified_reason_modified(String verified_reason_modified) {
        this.verified_reason_modified = verified_reason_modified;
    }

    public String getVerified_contact_name() {
        return verified_contact_name;
    }

    public void setVerified_contact_name(String verified_contact_name) {
        this.verified_contact_name = verified_contact_name;
    }

    public String getVerified_contact_email() {
        return verified_contact_email;
    }

    public void setVerified_contact_email(String verified_contact_email) {
        this.verified_contact_email = verified_contact_email;
    }

    public String getVerified_contact_mobile() {
        return verified_contact_mobile;
    }

    public void setVerified_contact_mobile(String verified_contact_mobile) {
        this.verified_contact_mobile = verified_contact_mobile;
    }

    public boolean isFollow_me() {
        return follow_me;
    }

    public void setFollow_me(boolean follow_me) {
        this.follow_me = follow_me;
    }

    public int getOnline_status() {
        return online_status;
    }

    public void setOnline_status(int online_status) {
        this.online_status = online_status;
    }

    public int getBi_followers_count() {
        return bi_followers_count;
    }

    public void setBi_followers_count(int bi_followers_count) {
        this.bi_followers_count = bi_followers_count;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getMbtype() {
        return mbtype;
    }

    public void setMbtype(int mbtype) {
        this.mbtype = mbtype;
    }

    public int getMbrank() {
        return mbrank;
    }

    public void setMbrank(int mbrank) {
        this.mbrank = mbrank;
    }

    public int getBlock_word() {
        return block_word;
    }

    public void setBlock_word(int block_word) {
        this.block_word = block_word;
    }

    public int getBlock_app() {
        return block_app;
    }

    public void setBlock_app(int block_app) {
        this.block_app = block_app;
    }

    public int getCredit_score() {
        return credit_score;
    }

    public void setCredit_score(int credit_score) {
        this.credit_score = credit_score;
    }

    public int getUser_ability() {
        return user_ability;
    }

    public void setUser_ability(int user_ability) {
        this.user_ability = user_ability;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public int getUrank() {
        return urank;
    }

    public void setUrank(int urank) {
        this.urank = urank;
    }

    public static class InsecurityBean implements Serializable{
        /**
         * sexual_content : false
         */

        private boolean sexual_content;

        public boolean isSexual_content() {
            return sexual_content;
        }

        public void setSexual_content(boolean sexual_content) {
            this.sexual_content = sexual_content;
        }
    }
}
