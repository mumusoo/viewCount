package cn.whb.java.ehcache;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.whb.java.entity.ContentCount;
import cn.whb.java.service.ContentCountMng;

/**
 * 内容计数器缓存实现
 */
@Service
public class ContentCountCacheImpl implements ContentCountCache, DisposableBean {
    private Logger logger = LoggerFactory.getLogger(ContentCountCacheImpl.class);

    /**
     * @see ContentCountCache#viewAndGet(Integer, String)
     */
    public int[] viewAndGet(Integer id, String thirdSource) {
        ContentCount count = contentCountMng.findById(id);
        if (count == null) {
            return null;
        }
        Element e = cache.get(id);
        Integer views;
        try {
            cache.acquireWriteLockOnKey(id);
        if (e != null) {
            views = (Integer) e.getObjectValue() + 1;
        } else {
            views = 1;
        }
        logger.info("********************************");
        logger.info("创建缓存 id="+id+" views="+views);
        logger.info("来源："+thirdSource);

            cache.put(new Element(id, views));
        } finally {
            cache.releaseWriteLockOnKey(id);
        }

        //渠道判断  -guoy
        if ("APP分享".equals(thirdSource)) {
            Element e_third = cache.get(id + "_a");
            Integer views_third;
            if (e_third != null) {
                views_third = (Integer) e_third.getObjectValue() + 1;
            } else {
                views_third = 1;
            }
            cache.put(new Element(id + "_a", views_third));
            //System.out.println("创建缓存 id="+id + "_a"+" views="+views_third);
        } else if ("PC网站".equals(thirdSource)) {
            Element e_third = cache.get(id + "_b");
            Integer views_third;
            if (e_third != null) {
                views_third = (Integer) e_third.getObjectValue() + 1;
            } else {
                views_third = 1;
            }
            cache.put(new Element(id + "_b", views_third));
            //System.out.println("创建缓存 id="+id + "_b"+" views="+views_third);
        } else if ("今日头条".equals(thirdSource)) {
            Element e_third = cache.get(id + "_c");
            Integer views_third;
            if (e_third != null) {
                views_third = (Integer) e_third.getObjectValue() + 1;
            } else {
                views_third = 1;
            }
            cache.put(new Element(id + "_c", views_third));
            //System.out.println("创建缓存 id="+id + "_c"+" views="+views_third);
        } else if ("UC头条".equals(thirdSource)) {
            Element e_third = cache.get(id + "_d");
            Integer views_third;
            if (e_third != null) {
                views_third = (Integer) e_third.getObjectValue() + 1;
            } else {
                views_third = 1;
            }
            cache.put(new Element(id + "_d", views_third));
            //System.out.println("创建缓存 id="+id + "_d"+" views="+views_third);
        } else if ("一点资讯".equals(thirdSource)) {
            Element e_third = cache.get(id + "_e");
            Integer views_third;
            if (e_third != null) {
                views_third = (Integer) e_third.getObjectValue() + 1;
            } else {
                views_third = 1;
            }
            cache.put(new Element(id + "_e", views_third));
            //System.out.println("创建缓存 id="+id + "_e"+" views="+views_third);
        } else if ("ZAKER新闻".equals(thirdSource)) {
            Element e_third = cache.get(id + "_f");
            Integer views_third;
            if (e_third != null) {
                views_third = (Integer) e_third.getObjectValue() + 1;
            } else {
                views_third = 1;
            }
            cache.put(new Element(id + "_f", views_third));
            //System.out.println("创建缓存 id="+id + "_f"+" views="+views_third);
        } else if ("APP".equals(thirdSource)) {
            Element e_third = cache.get(id + "_g");
            Integer views_third;
            if (e_third != null) {
                views_third = (Integer) e_third.getObjectValue() + 1;
            } else {
                views_third = 1;
            }
            cache.put(new Element(id + "_g", views_third));
            //System.out.println("创建缓存 id="+id + "_g"+" views="+views_third);
        } else {
            Element e_third = cache.get(id + "_h");
            Integer views_third;
            if (e_third != null) {
                views_third = (Integer) e_third.getObjectValue() + 1;
            } else {
                views_third = 1;
            }
            cache.put(new Element(id + "_h", views_third));
            //System.out.println("创建缓存 id="+id + "_h"+" views="+views_third);
        }

        refreshToDB();
        return new int[]{views + count.getViews(), count.getComments(),
                count.getDownloads(), count.getUps(), count.getDowns()};
    }

    private void refreshToDB() {
        long time = System.currentTimeMillis();
        if (time > refreshTime + interval) {
            //System.out.println("********************************8");
            logger.info("refresh to DB ");
            refreshTime = time;
            int count = contentCountMng.freshCacheToDB(cache);
            // 清除缓存
            cache.removeAll();
            logger.info("refresh cache views to DB: {}", count);
        }
    }

    /**
     * 销毁BEAN时，缓存入库。
     */
    public void destroy() throws Exception {
        int count = contentCountMng.freshCacheToDB(cache);
        logger.info("Bean destroy.refresh cache views to DB: {}", count);
    }

    // 间隔时间
    private int interval = 10 * 60 * 1000; // 10分钟
    // 最后刷新时间
    private long refreshTime = System.currentTimeMillis();

    private ContentCountMng contentCountMng;

    private Ehcache cache;


    /**
     * 刷新间隔时间
     *
     * @param interval 单位分钟
     */
    public void setInterval(int interval) {
        this.interval = interval * 60 * 1000;
    }

    @Autowired
    public void setContentCountMng(ContentCountMng contentCountMng) {
        this.contentCountMng = contentCountMng;
    }

    @Autowired
    public void setCache(@Qualifier("contentCount") Ehcache cache) {
        this.cache = cache;
    }
}


