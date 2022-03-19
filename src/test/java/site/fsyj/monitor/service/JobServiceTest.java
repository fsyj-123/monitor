package site.fsyj.monitor.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import site.fsyj.monitor.bean.MonitorJob;
import site.fsyj.monitor.util.IDUtils;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobServiceTest {

    @Resource
    JobService jobServiceImpl;

    @Test
    void insert() {
        MonitorJob job = new MonitorJob(IDUtils.getUUID(), "珙桐3-105" , "1", "56",
                "bb62312911b282f57d03568c998776e2", "1037", true, false, "123");
        jobServiceImpl.insert(job);
    }
}
