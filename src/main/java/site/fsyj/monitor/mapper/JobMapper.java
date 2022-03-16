package site.fsyj.monitor.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.fsyj.monitor.bean.Job;

@Mapper
public interface JobMapper {
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
    int insert(Job record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Job record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Job selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Job record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Job record);
}