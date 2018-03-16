package cn.whb.java.dao.impl;

import cn.whb.java.dao.CmsConfigDao;
import cn.whb.java.entity.CmsConfig;
import cn.whb.java.hibernate.HibernateBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class CmsConfigDaoImpl extends HibernateBaseDao<CmsConfig, Integer>
        implements CmsConfigDao {
    public CmsConfig findById(Integer id) {
        CmsConfig entity = get(id);
        return entity;
    }

    @Override
    protected Class<CmsConfig> getEntityClass() {
        return CmsConfig.class;
    }
}