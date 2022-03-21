package site.fsyj.monitor.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import site.fsyj.monitor.mapper.AreaMapper;
import site.fsyj.monitor.mapper.BuildMapper;
import site.fsyj.monitor.mapper.RoomMapper;
import site.fsyj.monitor.service.RoomService;

import javax.annotation.Resource;

@SpringBootTest
class DicValueServiceImplTest {

    @Resource
    AreaMapper areaMapper;

    @Resource
    RoomService roomServiceImpl;

    @Resource
    BuildMapper buildMapper;

    @Resource
    RoomMapper roomMapper;

    @Test
    public void valueTest() {
    }


    // 增加楼栋
    @Test
    public void buildTest() {
    }

    @Test
    public void roomTest() {
    }

}
