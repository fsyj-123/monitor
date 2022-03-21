package site.fsyj.monitor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.fsyj.monitor.bean.Area;
import site.fsyj.monitor.bean.Build;
import site.fsyj.monitor.bean.MonitorJob;
import site.fsyj.monitor.bean.Room;
import site.fsyj.monitor.http.service.QueryService;
import site.fsyj.monitor.service.AreaService;
import site.fsyj.monitor.service.BuildService;
import site.fsyj.monitor.service.RoomService;
import site.fsyj.monitor.util.IDUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fsyj on 2022/3/20
 */
@Slf4j
@RestController
@RequestMapping("/room")
public class RoomController {

    @Resource
    private AreaService areaServiceImpl;

    @Resource
    private BuildService buildServiceImpl;

    @Resource
    private QueryService queryService;

    @Resource
    private RoomService roomServiceImpl;


    @GetMapping("/area")
    public ResponseEntity<List<Area>> getAreas() {
        List<Area> areas = areaServiceImpl.selectAll();
        return ResponseEntity.ok(areas);
    }

    @GetMapping("/build")
    public ResponseEntity<List<Build>> getBuilds(String areaId) {
        if (IDUtils.isIdValid(areaId)) {
            List<Build> builds = buildServiceImpl.selectByParent(areaId);
            return ResponseEntity.ok(builds);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/room")
    public ResponseEntity<List<Room>> getRooms(String buildId) {
        if (IDUtils.isIdValid(buildId)) {
            List<Room> rooms = roomServiceImpl.selectByParent(buildId);
            return ResponseEntity.ok(rooms);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/oddl")
    public ResponseEntity<String> getoddl(String areaid, String buildid, String projectId, String roomid) {
        MonitorJob job = new MonitorJob(IDUtils.getUUID(), "测试", areaid, buildid, projectId, roomid, true, true, null);
        System.out.println(job);
        String rest = queryService.executeOnce(job);
        return ResponseEntity.ok(rest);
    }


}
