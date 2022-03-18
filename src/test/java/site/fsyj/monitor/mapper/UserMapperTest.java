package site.fsyj.monitor.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import site.fsyj.monitor.bean.User;
import site.fsyj.monitor.util.IDUtils;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {
    @Resource
    UserMapper userMapper;

    @Test
    void insert() {
        userMapper.insert(new User(IDUtils.getUUID(), "123456", "123465"));
    }
}
