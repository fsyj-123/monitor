package site.fsyj.monitor.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import site.fsyj.monitor.config.AppConfig;
import site.fsyj.monitor.http.service.QueryService;
import site.fsyj.monitor.quartz.SelfTask;

import javax.annotation.Resource;

/**
 * @author fsyj on 2022/3/21
 */
@Slf4j
@Component
public class ApplicationListener {

    @Resource
    QueryService queryService;

    @Autowired
    AppConfig appConfig;


    @EventListener(ApplicationReadyEvent.class)
    public void taskStart() throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("queryService", queryService);
        JobDetail jobDetail = JobBuilder.newJob(SelfTask.class).setJobData(jobDataMap).build();

        log.info("Cron:{}", appConfig.getCron());

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(appConfig.getCron()))
                .forJob(jobDetail)
                .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
