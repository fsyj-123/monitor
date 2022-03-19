package site.fsyj.monitor.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.fsyj.monitor.bean.MonitorJob;

import java.util.List;

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
    int insert(MonitorJob record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(MonitorJob record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    MonitorJob selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(MonitorJob record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(MonitorJob record);

    /**
     * 查询所有开启的任务
     *
     * @return
     */
    List<MonitorJob> selectAllByEnable();
}
