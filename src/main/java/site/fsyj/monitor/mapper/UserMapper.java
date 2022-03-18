package site.fsyj.monitor.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.fsyj.monitor.bean.User;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(String id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(User record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(User record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    User selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(User record);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    List<User> selectByUsername(@Param("username")String username);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    User selectByUsernameAndPassword(@Param("username")String username,@Param("password")String password);




}
