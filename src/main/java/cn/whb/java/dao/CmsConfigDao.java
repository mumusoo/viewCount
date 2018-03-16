package cn.whb.java.dao;

import cn.whb.java.entity.CmsConfig;
import cn.whb.java.hibernate.Updater;

public interface CmsConfigDao {
    public CmsConfig findById(Integer id);

    public CmsConfig updateByUpdater(Updater<CmsConfig> updater);
}