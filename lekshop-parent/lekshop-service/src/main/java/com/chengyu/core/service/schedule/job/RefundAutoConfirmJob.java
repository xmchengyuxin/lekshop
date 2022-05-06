package com.chengyu.core.service.schedule.job;

import com.chengyu.core.service.order.OrderRefundService;
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
 * @title  卖家超时未确认收货自动确认
 * @author LeGreen
 * @date   2022/5/6
 */
@Slf4j
@Component
public class RefundAutoConfirmJob extends QuartzJobBean {

	@Autowired
    private ScheduleService scheduleService;
    @Autowired
    private TaskTriggerService taskTriggerService;
    @Autowired
    private OrderRefundService orderRefundService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        Trigger trigger = jobExecutionContext.getTrigger();
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        String data = jobDataMap.getString("data");
        log.info("卖家超时未确认收货自动确认：{}",data);
        //执行业务操作
        try {
            orderRefundService.autoConfirmRefund(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        //完成后删除触发器和任务
        scheduleService.cancelScheduleJob(trigger.getKey().getName());
        taskTriggerService.excute(trigger.getKey().getName());
    }
}
