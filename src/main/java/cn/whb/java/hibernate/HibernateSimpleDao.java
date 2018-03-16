package cn.whb.java.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;



/**
 * hibernate DAO基类
 *
 * 提供hql分页查询，不带泛型，与具体实体类无关。
 */
public abstract class HibernateSimpleDao {
    /**
     * 日志，可用于子类
     */
    protected Logger log = LoggerFactory.getLogger(getClass());
    /**
     * HIBERNATE 的 order 属性
     */
    protected static final String ORDER_ENTRIES = "orderEntries";

    /**
     * 通过HQL查询对象列表
     *
     * @param hql
     *            hql语句
     * @param values
     *            数量可变的参数
     */
    @SuppressWarnings("unchecked")
    protected List find(String hql, Object... values) {
        return createQuery(hql, values).list();
    }

    /**
     * 通过HQL查询唯一对象
     */
    protected Object findUnique(String hql, Object... values) {
        return createQuery(hql, values).uniqueResult();
    }




    /**
     * 获得Finder的记录总数
     *
     * @param finder
     * @return
     */
    protected int countSqlQueryResult(SqlFinder finder) {
        Query query = getSession().createSQLQuery(finder.getRowCountSql());
        finder.setParamsToQuery(query);
        if (finder.isCacheable()) {
            query.setCacheable(true);
        }
        Number count = (Number)query.uniqueResult();
        if(count == null)
            return 0;
        return count.intValue();
//		return ((Number) query.iterate().next()).intValue();
    }


    /**
     * 通过Finder获得列表数据
     *
     * @param finder
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List find(Finder finder) {
        Query query = finder.createQuery(getSession());
        List list = query.list();
        return list;
    }

    /**
     * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
     */
    protected Query createQuery(String queryString, Object... values) {
        Assert.hasText(queryString);
        Query queryObject = getSession().createQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject;
    }


    /**
     * 获得Finder的记录总数
     *
     * @param finder
     * @return
     */
    protected int countQueryResult(Finder finder) {
        Query query = getSession().createQuery(finder.getRowCountHql());
        finder.setParamsToQuery(query);
        if (finder.isCacheable()) {
            query.setCacheable(true);
        }
        return ((Number) query.iterate().next()).intValue();
    }

    protected int countQueryResultByGroup(Finder finder,String selectSql) {
        Query query = getSession().createQuery(finder.getRowCountTotalHql(selectSql));
        setParamsToQuery(finder, query);
        return ((Number) query.iterate().next()).intValue();
    }



    private Query setParamsToQuery(Finder finder,Query query){
        finder.setParamsToQuery(query);
        if (finder.isCacheable()) {
            query.setCacheable(true);
        }
        return query;
    }

    /**
     * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
     */
    protected Query createSQLQuery(String queryString, Object... values) {
        Assert.hasText(queryString);
        Query queryObject = getSession().createSQLQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject;
    }
    /**
     * 通过SQL查询唯一对象
     */
    protected <T> T findUniqueBySqlForTrans(String sql, Class<T> cls, Object... values) {
        return (T) createSQLQuery(sql, values).setResultTransformer(Transformers.aliasToBean(cls)).uniqueResult();
    }

    /**
     * 通过Finder获得列表数据
     *
     * @param finder
     * @return
     */
    @SuppressWarnings("rawtypes")
    protected List findBySqlForTrans(SqlFinder finder, Class cls) {
        Query query = finder.createSqlQuery(getSession());
        query.setResultTransformer(Transformers.aliasToBean(cls));
        List list = query.list();
        return list;
    }

    protected SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
