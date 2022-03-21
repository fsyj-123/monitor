package site.fsyj.monitor.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.fsyj.monitor.bean.Room;

import java.util.List;

@Mapper
public interface RoomMapper {
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
    int insert(Room record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Room record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Room selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Room record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Room record);

    int insertList(@Param("list")List<Room> list);


    List<Room> selectAllByBuildParent(@Param("buildParent")String buildParent);


}
