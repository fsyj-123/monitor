package site.fsyj.monitor.service;

import site.fsyj.monitor.bean.Area;

import java.util.List;

public interface AreaService{


    int deleteByPrimaryKey(String id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    List<Area> selectAll();
}
