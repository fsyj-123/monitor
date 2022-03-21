package site.fsyj.monitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import site.fsyj.monitor.http.service.QueryService;

import javax.annotation.Resource;

@SpringBootApplication
@MapperScan("site.fsyj.monitor.mapper")
@ConfigurationPropertiesScan(basePackages = {"site.fsyj.monitor.config", "site.fsyj.monitor.http.config"})
public class MonitorApplication {

    @Resource
    QueryService queryService;

    public static void main(String[] args) {
        try {
            ApplicationContext context = SpringApplication.run(MonitorApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
