package cn.whb.java.service;

import cn.whb.java.entity.CmsConfig;

import java.util.Date;
import java.util.Map;

public interface CmsConfigMng {
    public CmsConfig get();

    public Integer getContentFreshMinute();

    public void updateCountCopyTime(Date d);

    public void updateCountClearTime(Date d);

    public void updateFlowClearTime(Date d);

    public void updateChannelCountClearTime(Date d);

    public CmsConfig update(CmsConfig bean);

    public void updateSsoAttr(Map<String,String> ssoAttr);

    public void updateRewardFixAttr(Map<String,String> fixAttr);
}