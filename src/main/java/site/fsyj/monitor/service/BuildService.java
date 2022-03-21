package site.fsyj.monitor.service;

import site.fsyj.monitor.bean.Build;

import java.util.List;

public interface BuildService {


    int deleteByPrimaryKey(String id);

    int insert(Build record);

    int insertSelective(Build record);

    Build selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Build record);

    int updateByPrimaryKey(Build record);

    List<Build> selectByParent(String areaId);
}

