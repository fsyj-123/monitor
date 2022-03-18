package site.fsyj.monitor.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import site.fsyj.monitor.bean.User;
import site.fsyj.monitor.mapper.UserMapper;
import site.fsyj.monitor.service.UserService;
import site.fsyj.monitor.util.IDUtils;
import site.fsyj.monitor.util.MD5Util;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public User regUser(String username, String password) {
        User user = null;
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            throw new IllegalArgumentException("用户名或密码不能为空！");
        }
        if (username.length() < 6 || username.length() > 20) {
            throw new IllegalArgumentException("用户名必须为6-20位！");
        } else if (password.length() < 8 || password.length() > 20) {
            throw new IllegalArgumentException("密码长度需在8-20位之间！");
        }
        if (!userMapper.selectByUsername(username).isEmpty()) {
            throw new IllegalArgumentException("用户名已经存在！");
        }
        password = MD5Util.encode(password);
        user = new User(IDUtils.getUUID(), username, password);
        userMapper.insert(user);
        return user;
    }

    @Override
    public User loginUser(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, MD5Util.encode(password));
    }
}
