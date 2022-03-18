package site.fsyj.monitor.service;

import site.fsyj.monitor.bean.User;
public interface UserService{


    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 用户登录接口
     * @param username
     * @param password
     * @return
     */
    User regUser(String username, String password);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User loginUser(String username, String password);
}
