package site.fsyj.monitor.http.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.protocol.RequestContent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fsyj on 2022/3/16
 */
@Configuration
public class QueryHttpClient {
    @Bean
    public HttpClient queryClient() {
        return HttpClients.custom().addRequestInterceptorLast(new RequestContent(true)).build();
    }
}
