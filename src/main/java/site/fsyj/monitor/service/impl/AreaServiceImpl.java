package site.fsyj.monitor.service.impl;

import org.springframework.stereotype.Service;
import site.fsyj.monitor.bean.Area;
import site.fsyj.monitor.mapper.AreaMapper;
import site.fsyj.monitor.service.AreaService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService{

    @Resource
    private AreaMapper areaMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return areaMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Area record) {
        return areaMapper.insert(record);
    }

    @Override
    public int insertSelective(Area record) {
        return areaMapper.insertSelective(record);
    }

    @Override
    public Area selectByPrimaryKey(String id) {
        return areaMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Area record) {
        return areaMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Area record) {
        return areaMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Area> selectAll() {
        return areaMapper.selectAll();
    }
}
