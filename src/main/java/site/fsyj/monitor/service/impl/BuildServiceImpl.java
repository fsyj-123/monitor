package site.fsyj.monitor.service.impl;

import org.springframework.stereotype.Service;
import site.fsyj.monitor.bean.Build;
import site.fsyj.monitor.mapper.BuildMapper;
import site.fsyj.monitor.service.BuildService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BuildServiceImpl implements BuildService {

    @Resource
    private BuildMapper buildMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return buildMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Build record) {
        return buildMapper.insert(record);
    }

    @Override
    public int insertSelective(Build record) {
        return buildMapper.insertSelective(record);
    }

    @Override
    public Build selectByPrimaryKey(String id) {
        return buildMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Build record) {
        return buildMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Build record) {
        return buildMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Build> selectByParent(String areaId) {
        return buildMapper.selectByAreaParent(areaId);
    }

}

