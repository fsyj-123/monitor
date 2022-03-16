package site.fsyj.monitor.http.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.fsyj.monitor.bean.LoginUser;
import site.fsyj.monitor.bean.MonitorJob;
import site.fsyj.monitor.config.HeaderConfig;
import site.fsyj.monitor.mapper.JobMapper;
import site.fsyj.monitor.util.JsonUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author fsyj on 2022/3/15
 */
@Slf4j
@Service
public class QueryService implements Job {

    @Resource
    private JobMapper jobMapper;

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private LoginUser loginUser;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<MonitorJob> jobs = jobMapper.selectAllByEnable();
        for (MonitorJob job : jobs) {
            // 如果该任务没有执行则执行
            if (!job.getStatus()) {
                HttpPost post = getQueryPost();
                try {
                    post.setEntity(new StringEntity(JsonUtil.mapToStr(job.getPostBody()), StandardCharsets.UTF_8));
                    String response = httpClient.execute(post, new BasicHttpClientResponseHandler());
                    log.info("剩余电量：{}", response);
                } catch (IOException e) {
                    log.error("IO异常：{}", e.getMessage());
                }
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
