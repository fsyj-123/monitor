package site.fsyj.monitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@MapperScan("site.fsyj.monitor.mapper")
@ConfigurationPropertiesScan(basePackages = {"site.fsyj.monitor.config", "site.fsyj.monitor.http.config"})
public class MonitorApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(MonitorApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
