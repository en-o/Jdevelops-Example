package cn.tannn.springbootquart;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author tnnn
 * @version V1.0
 * @date 2023-03-09 14:26
 */
@Slf4j
@Component
public class TestQuartzJobBean extends QuartzJobBean {


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext)  {
        Trigger trigger = jobExecutionContext.getTrigger();
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        log.info("定时开始trigger：{}, job: {}",trigger.getKey(),jobDetail.getKey());

    }
}
