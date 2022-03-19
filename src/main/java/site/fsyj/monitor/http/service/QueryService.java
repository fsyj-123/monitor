package site.fsyj.monitor.http.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import site.fsyj.monitor.bean.LoginUser;
import site.fsyj.monitor.bean.MonitorJob;
import site.fsyj.monitor.config.HeaderConfig;
import site.fsyj.monitor.mapper.JobMapper;
import site.fsyj.monitor.util.JsonUtil;
import site.fsyj.monitor.util.PushUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fsyj on 2022/3/15
 */
@Slf4j
@Service
public class QueryService implements Serializable {


    private static final long serialVersionUID = -1108394074390494724L;
    @Resource
    private LoginService loginService;

    @Resource
    private JobMapper jobMapper;

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private PushUtil pushUtil;

    private LoginUser loginUser;


    @Autowired
    ApplicationContext applicationContext;

    private ExecutorService service = Executors.newCachedThreadPool();

    public void execute() {
        loginService.login();
        loginUser = applicationContext.getBean(LoginUser.class);

        List<MonitorJob> jobs = jobMapper.selectAllByEnable();
        for (MonitorJob job : jobs) {
            System.out.println();
            System.out.println(job);
            System.out.println();
            // 如果该任务没有执行则执行
            HttpPost post = getQueryPost();
            try {
                post.setEntity(new StringEntity(JsonUtil.mapToStr(job.getPostBody()), StandardCharsets.UTF_8));
                String response = httpClient.execute(post, new BasicHttpClientResponseHandler());
                service.submit(() -> {
                    pushUtil.push(response, job);
                });
            } catch (IOException e) {
                log.error("IO异常：{}", e.getMessage());
            }
        }
    }

    private HttpPost getQueryPost() {
        HttpPost post = new HttpPost(HeaderConfig.QUERY_WEBSITE);
        post.addHeader("Host", HeaderConfig.HOST);
        post.setHeader("Content-Length", HeaderConfig.CONTENT_LENGTH);
        post.addHeader("Accept", HeaderConfig.ACCEPT);
        post.addHeader("X-Token", loginUser.getXToken());
        post.addHeader("User-Agent", HeaderConfig.USER_AGENT);
        post.addHeader("Content-Type", HeaderConfig.CONTENT_TYPE);
        post.addHeader("Cookie", loginUser.getCookie());
        return post;
    }
}
