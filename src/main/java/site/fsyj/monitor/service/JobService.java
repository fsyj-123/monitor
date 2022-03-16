package site.fsyj.monitor.service;

import site.fsyj.monitor.bean.Job;

public interface JobService {


    int deleteByPrimaryKey(String id);

    int insert(Job record);

    int insertSelective(Job record);

    Job selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);

}

