package site.fsyj.monitor.service;

import site.fsyj.monitor.DO.BuildDo;
import site.fsyj.monitor.DO.RoomDo;
import site.fsyj.monitor.bean.Room;

import java.util.List;

/**
 * @author fsyj
 */
public interface RoomService {

    /**
     * 通过园区获取楼栋
     *
     * @return
     */
    public List<BuildDo> getFloors(String areaId, String projectId);

    public List<RoomDo> getRooms(String areaId, String buildId, String projectId);

    int deleteByPrimaryKey(String id);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    List<Room> selectByParent(String buildId);
}


