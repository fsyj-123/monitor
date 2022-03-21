package site.fsyj.monitor.mapper;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import site.fsyj.monitor.bean.Build;

import java.util.List;

@Mapper
public interface BuildMapper {
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
    int insert(Build record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Build record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Build selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Build record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Build record);

    int insertList(@Param("list")List<Build> list);


    List<Build> selectAll();


    List<Build> selectByAreaParent(@Param("areaParent")String areaParentId);


}
