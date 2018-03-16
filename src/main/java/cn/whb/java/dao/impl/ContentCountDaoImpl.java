package cn.whb.java.dao.impl;

import cn.whb.java.dao.ContentCountDao;
import cn.whb.java.ehcache.ContentCountCacheImpl;
import cn.whb.java.entity.ContentCount;
import cn.whb.java.hibernate.HibernateBaseDao;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContentCountDaoImpl extends
        HibernateBaseDao<ContentCount, Integer> implements ContentCountDao {
    @SuppressWarnings("unchecked")
    private Logger logger = LoggerFactory.getLogger(ContentCountDaoImpl.class);
    public int freshCacheToDB(Ehcache cache) {
        List<Object> keys = cache.getKeys();
        logger.info("缓存数据 size "+keys.size());
        if (keys.size() <= 0) {
            return 0;
        }
        Element e;
        Integer views;
        int i = 0;
        String hql = "update ContentCount bean"
                + " set bean.views=bean.views+:views"
                + ",bean.viewsMonth=bean.viewsMonth+:views"
                + ",bean.viewsWeek=bean.viewsWeek+:views"
                + ",bean.viewsDay=bean.viewsDay+:views" + " where bean.id=:id";
        Query query = getSession().createQuery(hql);
        for (Object id : keys) {
            String idStr=id+"";
            logger.info("缓存数据："+idStr);
            if(idStr.contains("_")){
                String hql2 = "update ContentCount bean";
                e = cache.get(id);
                if (e != null) {
                    views = (Integer) e.getObjectValue();
                    if (views != null) {
                        logger.info("渠道:"+idStr+" 点击量增加:"+views);
                        if(idStr.contains("a")){
                            hql2=hql2+" set bean.viewsAppShare=bean.viewsAppShare+:views" ;
                        }else if(idStr.contains("b")){
                            hql2=hql2+" set bean.viewsPc=bean.viewsPc+:views" ;
                        }else if(idStr.contains("c")){
                            hql2=hql2+" set bean.viewsJinri=bean.viewsJinri+:views" ;
                        }else if(idStr.contains("d")){
                            hql2=hql2+" set bean.viewsUc=bean.viewsUc+:views" ;

                        }else if(idStr.contains("e")){
                            hql2=hql2+" set bean.viewsYidian=bean.viewsYidian+:views" ;
                        }else if(idStr.contains("f")){
                            hql2=hql2+" set bean.viewsZaker=bean.viewsZaker+:views" ;
                        }else if(idStr.contains("g")){
                            hql2=hql2+" set bean.viewsApp=bean.viewsApp+:views" ;
                        }else if(idStr.contains("h")){
                            hql2=hql2+" set bean.viewsOther=bean.viewsOther+:views" ;
                        }
                        hql2+=  " where bean.id=:id";
                        Query query2 = getSession().createQuery(hql2);
                        query2.setParameter("views", views);

                        try {
                            idStr=idStr.substring(0,idStr.indexOf("_"));
                            query2.setParameter("id",Integer.parseInt(idStr));
                        } catch (NumberFormatException e1) {
                            e1.printStackTrace();
                        }

                         query2.executeUpdate();
                    }
                }
            }else{
                e = cache.get(id);
                if (e != null) {
                    views = (Integer) e.getObjectValue();
                    if (views != null) {
                        logger.info("文章:"+idStr+" 点击量增加:"+views);
                        query.setParameter("views", views);
                        query.setParameter("id", id);
                        i += query.executeUpdate();
                    }
                }
            }

        }
        return i;
    }


    public int freshCacheToDBOthers(Ehcache cache) {
        List<Object> keys = cache.getKeys();
        System.out.println("第三方缓存数据 size"+keys.size());
        if (keys.size() <= 0) {
            return 0;
        }
        Element e;
        Integer views;
        int i = 0;
        for (Object id : keys) {
            String idStr=id+"";
            System.out.println("缓存数据："+idStr);
            if(idStr.contains("_")){
                String hql = "update ContentCount bean";
                e = cache.get(id);
                if (e != null) {
                    views = (Integer) e.getObjectValue();
                    if (views != null) {
                        if(idStr.contains("a")){
                            hql+=" set bean.viewsAppShare=bean.viewsAppShar+:views" ;
                        }else if(idStr.contains("b")){
                            hql+=" set bean.viewsPc=bean.viewsPc+:views" ;
                        }else if(idStr.contains("c")){
                            hql+=" set bean.viewsJinri=bean.viewsJinri+:views" ;
                        }else if(idStr.contains("d")){
                            hql+=" set bean.viewsUc=bean.viewsUc+:views" ;

                        }else if(idStr.contains("e")){
                            hql+=" set bean.viewsYidian=bean.viewsYidian+:views" ;
                        }else if(idStr.contains("f")){
                            hql+=" set bean.viewsZaker=bean.viewsZaker+:views" ;
                        }else if(idStr.contains("g")){
                            hql+=" set bean.viewsApp=bean.viewsApp+:views" ;
                        }else if(idStr.contains("h")){
                            hql+=" set bean.viewsOther=bean.viewsOther+:views" ;
                        }
                        hql+=  " where bean.id=:id";
                        System.out.println("hql："+hql+" id="+Integer.parseInt(idStr)+"views"+views);
                        Query query = getSession().createQuery(hql);
                        query.setParameter("views", views);

                        try {
                            idStr=idStr.substring(0,idStr.indexOf("_"));
                            query.setParameter("id",Integer.parseInt(idStr));
                        } catch (NumberFormatException e1) {
                            e1.printStackTrace();
                        }

                        i += query.executeUpdate();
                    }
                }
            }
        }
        return i;
    }


    public int clearCount(boolean week, boolean month) {
        StringBuilder hql = new StringBuilder("update ContentCount bean");
        hql.append(" set bean.viewsDay=0,commentsDay=0,upsDay=0,downloadsDay=0");
        if (week) {
            hql.append(",bean.viewsWeek=0,commentsWeek=0,upsWeek=0,downloadsWeek=0");
        }
        if (month) {
            hql.append(",bean.viewsMonth=0,commentsMonth=0,upsMonth=0,downloadsMonth=0");
        }
        return getSession().createQuery(hql.toString()).executeUpdate();
    }

    public int copyCount() {
        String hql = "update Content a set"
                + " a.viewsDay=(select b.viewsDay from ContentCount b where a.id=b.id)"
                + ",a.commentsDay=(select b.commentsDay from ContentCount b where a.id=b.id)"
                + ",a.downloadsDay=(select b.downloadsDay from ContentCount b where a.id=b.id)"
                + ",a.upsDay=(select b.upsDay from ContentCount b where a.id=b.id)";
        return getSession().createQuery(hql).executeUpdate();
    }

    public ContentCount findById(Integer id) {
        ContentCount entity = get(id);
        return entity;
    }

    public ContentCount save(ContentCount bean) {
        getSession().save(bean);
        return bean;
    }

    @Override
    protected Class<ContentCount> getEntityClass() {
        return ContentCount.class;
    }
}