package cn.tannn.demo.jdevelops.frameworksquick.job;

import cn.tannn.jdevelops.quartz.Job;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * 0/2 * * * * ?
 * 0 15 0 * * ?
 * @author tnnn
 * @version V1.0
 * @date 2023-03-09 14:26
 */
@Slf4j
@Component
// 自定注册 - 也可以不用这个注解，使用接口注册
@Job(cron = "0/2 * * * * ?", jobName = "TestQuartzJobBean", isStartNow = true)
public class TestQuartzJobBean extends QuartzJobBean {


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext)  {
        Trigger trigger = jobExecutionContext.getTrigger();
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        log.info("定时开始trigger：{}, job: {}",trigger.getKey(),jobDetail.getKey());

    }
}
