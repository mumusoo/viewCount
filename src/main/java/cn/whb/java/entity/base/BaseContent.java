package cn.whb.java.entity.base;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * This is an object that contains data related to the jc_content table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_content"
 */

public abstract class BaseContent  implements Serializable {

    public static String REF = "Content";
    public static String PROP_STATUS = "status";
    public static String PROP_TYPE = "type";
    public static String PROP_RECOMMEND = "recommend";
    public static String PROP_SITE = "site";
    public static String PROP_USER = "user";
    public static String PROP_HAS_TITLE_IMG = "hasTitleImg";
    public static String PROP_SORT_DATE = "sortDate";
    public static String PROP_TOP_LEVEL = "topLevel";
    public static String APPPROP_TOP_LEVEL = "appTopLevel";
    public static String PROP_COMMENTS_DAY = "commentsDay";
    public static String PROP_CONTENT_EXT = "contentExt";
    public static String PROP_VIEWS_DAY = "viewsDay";
    public static String PROP_UPS_DAY = "upsDay";
    public static String PROP_CHANNEL = "channel";
    public static String PROP_CONTENT_COUNT = "contentCount";
    public static String PROP_ID = "id";
    public static String PROP_DOWNLOADS_DAY = "downloadsDay";

    // TODO
    public static String PROP_OWEN = "owen";		// 独家
    public static String PROP_SPECIAL = "special";	// 特稿

    // constructors
    public BaseContent () {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseContent (java.lang.Integer id) {
        this.setId(id);
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public BaseContent (
            java.lang.Integer id,
            java.util.Date sortDate,
            java.lang.Byte topLevel,
            java.lang.Byte appTopLevel,
            java.lang.Boolean hasTitleImg,
            java.lang.Boolean recommend,
            java.lang.Byte status,
            java.lang.Integer viewsDay,
            java.lang.Short commentsDay,
            java.lang.Short downloadsDay,
            java.lang.Short upsDay,
            java.lang.Boolean owen,
            java.lang.Boolean special) {

        this.setId(id);
        this.setSortDate(sortDate);
        this.setTopLevel(topLevel);
        this.setTopLevel(appTopLevel);
        this.setHasTitleImg(hasTitleImg);
        this.setRecommend(recommend);
        this.setStatus(status);
        this.setViewsDay(viewsDay);
        this.setCommentsDay(commentsDay);
        this.setDownloadsDay(downloadsDay);
        this.setUpsDay(upsDay);
        this.setOwen(owen);
        this.setSpecial(special);
        initialize();
    }

    protected void initialize () {}



    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.Integer id;

    /**
     * 内容第一次发布时间，统计使用该时间
     */
    private Timestamp firstPublisTime;
    /**
     * 推送标志
     */
    private Boolean pushFlag;
    // 采编平台发布状态
    private Short thirdpartState;
    // 是否是第三方稿件
    private Boolean isThirdpart;

    // fields
    private java.util.Date sortDate;
    private java.lang.Byte topLevel;
    private java.lang.Byte appTopLevel;
    private Byte channelTopLevel;
    private java.lang.Boolean hasTitleImg;
    private java.lang.Boolean recommend;
    private java.lang.Byte status;
    private java.lang.Integer viewsDay;
    private java.lang.Short commentsDay;
    private java.lang.Short downloadsDay;
    private java.lang.Short upsDay;
    private java.lang.Integer score;
    private java.lang.Byte recommendLevel;
    private java.lang.Boolean owen;
    private java.lang.Boolean special;
    private String thirdpartId;
    private Integer relationChannelId;
    private Integer channelId;

    private cn.whb.java.entity.ContentCount contentCount;




    /**
     * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="content_id"
     */
    public java.lang.Integer getId () {
        return id;
    }

    /**
     * Set the unique identifier of this class
     * @param id the new ID
     */
    public void setId (java.lang.Integer id) {
        this.id = id;
        this.hashCode = Integer.MIN_VALUE;
    }




    /**
     * Return the value associated with the column: sort_date
     */
    public java.util.Date getSortDate () {
        return sortDate;
    }

    /**
     * Set the value related to the column: sort_date
     * @param sortDate the sort_date value
     */
    public void setSortDate (java.util.Date sortDate) {
        this.sortDate = sortDate;
    }


    /**
     * Return the value associated with the column: top_level
     */
    public java.lang.Byte getTopLevel () {
        return topLevel;
    }

    /**
     * Set the value related to the column: top_level
     * @param topLevel the top_level value
     */
    public void setTopLevel (java.lang.Byte topLevel) {
        this.topLevel = topLevel;
    }


    /**
     * Return the value associated with the column: has_title_img
     */
    public java.lang.Boolean getHasTitleImg () {
        return hasTitleImg;
    }

    /**
     * Set the value related to the column: has_title_img
     * @param hasTitleImg the has_title_img value
     */
    public void setHasTitleImg (java.lang.Boolean hasTitleImg) {
        this.hasTitleImg = hasTitleImg;
    }


    /**
     * Return the value associated with the column: is_recommend
     */
    public java.lang.Boolean getRecommend () {
        return recommend;
    }

    /**
     * Set the value related to the column: is_recommend
     * @param recommend the is_recommend value
     */
    public void setRecommend (java.lang.Boolean recommend) {
        this.recommend = recommend;
    }


    /**
     * Return the value associated with the column: status
     */
    public java.lang.Byte getStatus () {
        return status;
    }

    /**
     * Set the value related to the column: status
     * @param status the status value
     */
    public void setStatus (java.lang.Byte status) {
        this.status = status;
    }


    /**
     * Return the value associated with the column: views_day
     */
    public java.lang.Integer getViewsDay () {
        return viewsDay;
    }

    /**
     * Set the value related to the column: views_day
     * @param viewsDay the views_day value
     */
    public void setViewsDay (java.lang.Integer viewsDay) {
        this.viewsDay = viewsDay;
    }


    /**
     * Return the value associated with the column: comments_day
     */
    public java.lang.Short getCommentsDay () {
        return commentsDay;
    }

    /**
     * Set the value related to the column: comments_day
     * @param commentsDay the comments_day value
     */
    public void setCommentsDay (java.lang.Short commentsDay) {
        this.commentsDay = commentsDay;
    }




    /**
     * Return the value associated with the column: downloads_day
     */
    public java.lang.Short getDownloadsDay () {
        return downloadsDay;
    }

    /**
     * Set the value related to the column: downloads_day
     * @param downloadsDay the downloads_day value
     */
    public void setDownloadsDay (java.lang.Short downloadsDay) {
        this.downloadsDay = downloadsDay;
    }


    /**
     * Return the value associated with the column: ups_day
     */
    public java.lang.Short getUpsDay () {
        return upsDay;
    }

    /**
     * Set the value related to the column: ups_day
     * @param upsDay the ups_day value
     */
    public void setUpsDay (java.lang.Short upsDay) {
        this.upsDay = upsDay;
    }


    public java.lang.Integer getScore() {
        return score;
    }

    public void setScore(java.lang.Integer score) {
        this.score = score;
    }

    public java.lang.Byte getRecommendLevel() {
        return recommendLevel;
    }

    public void setRecommendLevel(java.lang.Byte recommendLevel) {
        this.recommendLevel = recommendLevel;
    }



    /**
     * Return the value associated with the column: contentCount
     */
    public cn.whb.java.entity.ContentCount getContentCount () {
        return contentCount;
    }

    /**
     * Set the value related to the column: contentCount
     * @param contentCount the contentCount value
     */
    public void setContentCount (cn.whb.java.entity.ContentCount contentCount) {
        this.contentCount = contentCount;
    }

    public Integer getRelationChannelId() {
        return relationChannelId;
    }

    public void setRelationChannelId(Integer relationChannelId) {
        this.relationChannelId = relationChannelId;
    }

    public Boolean getPushFlag() {
        return pushFlag;
    }

    public void setPushFlag(Boolean pushFlag) {
        this.pushFlag = pushFlag;
    }

    public boolean equals (Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof cn.whb.java.entity.Content)) return false;
        else {
            cn.whb.java.entity.Content content = (cn.whb.java.entity.Content) obj;
            if (null == this.getId() || null == content.getId()) return false;
            else return (this.getId().equals(content.getId()));
        }
    }

    public int hashCode () {
        if (Integer.MIN_VALUE == this.hashCode) {
            if (null == this.getId()) return super.hashCode();
            else {
                String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
                this.hashCode = hashStr.hashCode();
            }
        }
        return this.hashCode;
    }


    public String toString () {
        return super.toString();
    }

    public java.lang.Boolean getOwen() {
        return owen;
    }

    public void setOwen(java.lang.Boolean owen) {
        this.owen = owen;
    }

    public java.lang.Boolean getSpecial() {
        return special;
    }

    public void setSpecial(java.lang.Boolean special) {
        this.special = special;
    }
    public String getThirdpartId() {
        return thirdpartId;
    }

    public void setThirdpartId(String thirdpartId) {
        this.thirdpartId = thirdpartId;
    }

    public java.lang.Byte getAppTopLevel() {
        return appTopLevel;
    }

    public void setAppTopLevel(java.lang.Byte appTopLevel) {
        this.appTopLevel = appTopLevel;
    }

    public Byte getChannelTopLevel() {
        return channelTopLevel;
    }

    public void setChannelTopLevel(Byte channelTopLevel) {
        this.channelTopLevel = channelTopLevel;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Boolean getIsThirdpart() {
        return isThirdpart;
    }

    public void setIsThirdpart(Boolean isThirdpart) {
        this.isThirdpart = isThirdpart;
    }
    public Short getThirdpartState() {
        return thirdpartState;
    }

    public void setThirdpartState(Short thirdpartState) {
        this.thirdpartState = thirdpartState;
    }

    public Timestamp getFirstPublisTime() {
        return firstPublisTime;
    }

    public void setFirstPublisTime(Timestamp firstPublisTime) {
        this.firstPublisTime = firstPublisTime;
    }
}