package cn.tannn.springbootquart;

import cn.jdevelops.quartz.quick.ScheduleService;
import cn.jdevelops.quartz.quick.dao.bo.JobAndTriggerBO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tnnn
 * @version V1.0
 * @date 2023-03-09 14:19
 */
@RestController
public class QzController {

    private final ScheduleService scheduleService;

    private final static String jName="test", jGroup="test";

    public QzController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("add")
    public String add(String cron){
        scheduleService.addScheduleJob(TestQuartzJobBean.class,jName,jGroup,"test","test",cron);
        return "新增成功";
    }

    @GetMapping("page")
    public Page<JobAndTriggerBO> page(){
        return scheduleService.getJobAndTriggerDetails(1, 100);
    }

    @GetMapping("delete")
    public String delete(){
        scheduleService.deleteScheduleJob(jGroup,jName);
        return "删除成功";
    }

    @GetMapping("pause")
    public String pause(){
        scheduleService.pauseScheduleJob(jGroup,jName);
        return "暂停成功";
    }

    @GetMapping("resume")
    public String resume(){
        scheduleService.resumeScheduleJob(jGroup,jName);
        return "重启成功";
    }


    @GetMapping("res")
    public String resume(String cron){
        scheduleService.resScheduleJob(jGroup,jName, cron);
        return "重置成功";
    }

}
