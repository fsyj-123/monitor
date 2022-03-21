package site.fsyj.monitor.quartz;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import site.fsyj.monitor.http.service.QueryService;

/**
 * @author fsyj on 2022/3/19
 */
@Slf4j
@Setter
public class SelfTask implements Job {
    private QueryService queryService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        queryService.execute();
    }
}
