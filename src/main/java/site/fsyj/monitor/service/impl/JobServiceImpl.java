package site.fsyj.monitor.service.impl;

import org.springframework.stereotype.Service;
import site.fsyj.monitor.bean.MonitorJob;
import site.fsyj.monitor.bean.User;
import site.fsyj.monitor.mapper.JobMapper;
import site.fsyj.monitor.mapper.UserMapper;
import site.fsyj.monitor.service.JobService;

import javax.annotation.Resource;

@Service
public class JobServiceImpl implements JobService {

    @Resource
    private JobMapper jobMapper;

    @Resource
    private UserMapper userMapper;

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

    @Override
    public MonitorJob selectByUser(User loginUser) {
        return jobMapper.selectByPrimaryKey(loginUser.getJobId());
    }

    @Override
    public MonitorJob addTask(User loginUser, MonitorJob job) {
        insert(job);
        loginUser.setJobId(job.getId());
        userMapper.updateByPrimaryKey(loginUser);
        return job;
    }

}




