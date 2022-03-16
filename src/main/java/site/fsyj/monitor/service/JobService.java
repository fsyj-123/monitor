package site.fsyj.monitor.service;

import site.fsyj.monitor.bean.MonitorJob;

public interface JobService {


    int deleteByPrimaryKey(String id);

    int insert(MonitorJob record);

    int insertSelective(MonitorJob record);

    MonitorJob selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MonitorJob record);

    int updateByPrimaryKey(MonitorJob record);

}


