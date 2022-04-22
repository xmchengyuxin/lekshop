package com.chengyu.core.service.schedule.job;

import com.chengyu.core.service.schedule.ScheduleService;
import com.chengyu.core.service.task.TaskTriggerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @title  DemoJob
 * @author LeGreen
 * @date   2021/1/30
 */
@Slf4j
@Component
public class DemoJob extends QuartzJobBean {

	@Autowired
    private ScheduleService scheduleService;
    @Autowired
    private TaskTriggerService taskTriggerService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        Trigger trigger = jobExecutionContext.getTrigger();
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        String data = jobDataMap.getString("data");
        log.info("垫付任务超时未支付自动退出：{}",data);
        //执行业务操作
        //完成后删除触发器和任务
        scheduleService.cancelScheduleJob(trigger.getKey().getName());
        taskTriggerService.excute(trigger.getKey().getName());
    }
}
