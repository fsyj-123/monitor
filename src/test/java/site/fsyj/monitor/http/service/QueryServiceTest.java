package site.fsyj.monitor.http.service;

import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class QueryServiceTest {

    @Resource
    QueryService queryService;

    @Test
    public void monitorTest() throws SchedulerException, InterruptedException {

    }
}
