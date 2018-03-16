package cn.whb.java.entity;

import cn.whb.java.entity.base.BaseContent;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;


public class Content extends BaseContent  {
    private static final long serialVersionUID = 1L;
    //内容信息返回简化格式
    public static final int CONTENT_INFO_SIMPLE=1;
    //内容信息返回更全
    public static final int CONTENT_INFO_WHOLE=0;
    /**
     * 内容查询模式 1 共享他站内容
     */
    public static final Integer CONTENT_QUERY_SHARE=1;
    /**
     * 本站内容
     */
    public static final Integer CONTENT_QUERY_NOT_SHARE=0;
    /**
     * 查询数据
     */
    public static final Integer QUERY_DATA=1;
    /**
     * 查询分页数据
     */
    public static final Integer QUERY_PAGE=0;

    /**
     * 删除副栏目
     */
    public static final Integer CONTENT_CHANNEL_DEL=1;
    /**
     * 添加副栏目
     */
    public static final Integer CONTENT_CHANNEL_ADD=0;
    /**
     * 状态
     */
    public enum ContentStatus {
        /**
         * 所有
         */
        all,
        /**
         * 草稿，个人 0
         */
        draft,
        /**
         * 待审核，待提交
         */
        prepared,
        /**
         * 待二审（已经被一审）
         */
        check_step_one,
        /**
         * 二审预审
         */
        check_step_second_checking,
        /**
         * 待三审（已经被二审）
         */
        check_step_two,
        /**
         * 三审预审
         */
        check_step_third_checking,
        /**
         * 待发布
         */
        check_step_waiting_publish,
        /**
         * 已审
         */
        passed,
        /**
         * 终审
         */
        checked,
        /**
         * 已发布
         */
        published,
        /**
         * 退回
         */
        rejected,
        /**
         * 回收
         */
        recycle,
        /**
         * 转交
         */
        transfer,
        /**
         * 投稿
         */
        contribute,
        /**
         * 归档
         */
        pigeonhole,

        isPC,
        isAPP
    };

    public static int DATA_CONTENT=0;

    private DateFormat df = new SimpleDateFormat("/yyyyMMdd");







    public void init() {
        short zero = 0;
        byte bzero = 0;
        if (getViewsDay() == null) {
            setViewsDay(0);
        }
        if (getCommentsDay() == null) {
            setCommentsDay(zero);
        }
        if (getDownloadsDay() == null) {
            setDownloadsDay(zero);
        }
        if (getUpsDay() == null) {
            setUpsDay(zero);
        }
        if (getHasTitleImg() == null) {
            setHasTitleImg(false);
        }
        if (getRecommend() == null) {
            setRecommend(false);
        }
        if (getSortDate() == null) {
            setSortDate(new Timestamp(System.currentTimeMillis()));
        }
        if (getTopLevel() == null) {
            setTopLevel(bzero);
        }

        if(getScore()==null){
            setScore(0);
        }
        if(getRecommendLevel()==null){
            setRecommendLevel(bzero);
        }

        // TODO
        if(getOwen() == null) {
            setOwen(false);
        }
        if(getSpecial() == null) {
            setSpecial(false);
        }
    }




    public Integer getViews() {
        ContentCount count = getContentCount();
        if (count != null) {
            return count.getViews();
        } else {
            return null;
        }
    }

    public Integer getViewsMonth() {
        ContentCount count = getContentCount();
        if (count != null) {
            return count.getViewsMonth();
        } else {
            return null;
        }
    }
    public Integer getViewsWeek() {
        ContentCount count = getContentCount();
        if (count != null) {
            return count.getViewsWeek();
        } else {
            return null;
        }
    }
    public Integer getViewDay() {
        ContentCount count = getContentCount();
        if (count != null) {
            return count.getViewsDay();
        } else {
            return null;
        }
    }

    public Integer getCommentsCount() {
        ContentCount count = getContentCount();
        if (count != null) {
            return count.getComments();
        } else {
            return null;
        }
    }



    public Integer getUps() {
        ContentCount count = getContentCount();
        if (count != null) {
            return count.getUps();
        } else {
            return null;
        }
    }

    public Integer getDowns() {
        ContentCount count = getContentCount();
        if (count != null) {
            return count.getDowns();
        } else {
            return null;
        }
    }






    public Content cloneWithoutSet() {
        Content content = new Content();
        setSortDate(getSortDate());
        content.setTopLevel(getTopLevel());
        content.setHasTitleImg(getHasTitleImg());
        content.setRecommend(getRecommend());
        content.setStatus(getStatus());
        content.setViewsDay(getViewDay());
        content.setCommentsDay(getCommentsDay());
        content.setDownloadsDay(getDownloadsDay());
        content.setUpsDay(getUpsDay());


        return content;
    }







    /* [CONSTRUCTOR MARKER BEGIN] */
    public Content() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public Content(java.lang.Integer id) {
        super(id);
    }

    /**
     * Constructor for required fields
     */
    public Content(java.lang.Integer id, java.util.Date sortDate,
                   java.lang.Byte topLevel,java.lang.Byte appTopLevel,java.lang.Boolean hasTitleImg,
                   java.lang.Boolean recommend, java.lang.Byte status,
                   java.lang.Integer viewsDay, java.lang.Short commentsDay,
                   java.lang.Short downloadsDay, java.lang.Short upsDay,
                   java.lang.Boolean owen, java.lang.Boolean special) {

        super(id, sortDate, topLevel,appTopLevel,hasTitleImg, recommend, status,
                viewsDay, commentsDay, downloadsDay, upsDay,owen, special);
    }

	/* [CONSTRUCTOR MARKER END] */

}