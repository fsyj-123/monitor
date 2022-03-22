package site.fsyj.monitor.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RoomControllerTest {

    @Resource
    private RoomController roomController;


    @Test
    public void electTest() {
//        ResponseEntity<String> entity = roomController.getoddl("4", "2", "2595a1f7c8cf17410c85f9e05f9cc7c3", "245");
//        Assertions.assertEquals(entity.getBody(), "144");
    }

    @Test
    public void test() {
//        ResponseEntity<List<Area>> areas = roomController.getAreas();
//        List<Area> body = areas.getBody();
//        for (Area area : body) {
//            System.out.println(area);
//        }
    }
}
