package site.fsyj.monitor.http.service;

import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import site.fsyj.monitor.quartz.SelfTask;

import javax.annotation.Resource;

@SpringBootTest
class QueryServiceTest {

    @Resource
    QueryService queryService;

    @Test
    public void monitorTest() throws SchedulerException, InterruptedException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("queryService", queryService);
        JobDetail jobDetail = JobBuilder.newJob(SelfTask.class).setJobData(jobDataMap).build();


        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(100)
                        .withRepeatCount(10))
                .forJob(jobDetail)
                .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
        Thread.sleep(100000);
    }
}
