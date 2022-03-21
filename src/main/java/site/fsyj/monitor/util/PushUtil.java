package site.fsyj.monitor.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import site.fsyj.monitor.bean.MonitorJob;
import site.fsyj.monitor.service.MailService;

import javax.annotation.Resource;

/**
 * @author fsyj on 2022/3/19
 */
@Slf4j
@Component
public class PushUtil {

    @Resource
    MailService mailServiceImpl;

    public void push(String response, MonitorJob job) {
        int electric = ElectricResponseUtil.getRestElectric(response);
        // 后期可以根据每个定时任务的平均每天的电量消耗获取动态临界值
        int criticalValue = 10;
        if (electric < 10) {
            doPush(job, electric);
            return;
        }
        if (job.getStatus()) {
            doPush(job, electric);
        }
    }

    private void doPush(MonitorJob job, int electric) {
        mailServiceImpl.sendSimpleMail(job.getWebhook(), "电量提醒", ("任务：" + job.getName() + ",你的剩余电量：" + electric));
    }
}
