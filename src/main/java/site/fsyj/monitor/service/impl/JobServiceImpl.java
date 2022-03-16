package site.fsyj.monitor.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import site.fsyj.monitor.bean.MonitorJob;
import site.fsyj.monitor.mapper.JobMapper;
import site.fsyj.monitor.service.JobService;

@Service
public class JobServiceImpl implements JobService {

    @Resource
    private JobMapper jobMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return jobMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MonitorJob record) {
        return jobMapper.insert(record);
    }

    @Override
    public int insertSelective(MonitorJob record) {
        return jobMapper.insertSelective(record);
    }

    @Override
    public MonitorJob selectByPrimaryKey(String id) {
        return jobMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MonitorJob record) {
        return jobMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MonitorJob record) {
        return jobMapper.updateByPrimaryKey(record);
    }

}


