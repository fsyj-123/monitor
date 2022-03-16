package site.fsyj.monitor.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import site.fsyj.monitor.util.CronUtils;

/**
 * @author fsyj on 2022/3/16
 */
@Data
@Slf4j
@EnableConfigurationProperties(AppConfig.class)
@ConfigurationProperties(value = "app", ignoreInvalidFields = true)
public class AppConfig {



    private String defaultCron = "0 0 0,13 * * ?";

    /**
     * 查询的cron表达式
     */
    private String cron;

    public void setCron(String cron) {
        if (CronUtils.isValid(cron)) {
            this.cron = cron;
        } else {
            log.warn("cron表达式不合理，使用默认表达式");
            this.cron = defaultCron;
        }
    }
}

