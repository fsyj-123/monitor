package site.fsyj.monitor.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
        // 首先判断当前用户是否已经添加过任务，如果添加过，则直接返回
        User verifiedUser = userMapper.selectByPrimaryKey(loginUser.getId());
        if (StringUtils.hasLength(verifiedUser.getJobId())) {
            return null;
        }
        insert(job);
        loginUser.setJobId(job.getId());
        userMapper.updateByPrimaryKey(loginUser);
        return job;
    }
}




