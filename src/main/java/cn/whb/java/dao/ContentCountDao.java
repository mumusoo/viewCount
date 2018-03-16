package cn.whb.java.dao;

import cn.whb.java.entity.ContentCount;
import cn.whb.java.hibernate.Updater;
import net.sf.ehcache.Ehcache;



public interface ContentCountDao {
    public int clearCount(boolean week, boolean month);

    public int copyCount();

    public int freshCacheToDB(Ehcache cache);

    public int freshCacheToDBOthers(Ehcache cache);

    public ContentCount findById(Integer id);

    public ContentCount save(ContentCount bean);

    public ContentCount updateByUpdater(Updater<ContentCount> updater);
}