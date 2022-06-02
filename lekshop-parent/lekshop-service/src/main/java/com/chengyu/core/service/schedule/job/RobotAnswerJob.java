package com.chengyu.core.service.schedule.job;

import com.chengyu.core.service.schedule.ScheduleService;
import com.chengyu.core.util.netty.NettyPushUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @title  自动回复
 * @author LeGreen
 * @date   2022/6/2
 */
@Slf4j
@Component
public class RobotAnswerJob extends QuartzJobBean {

	@Autowired
    private ScheduleService scheduleService;
    @Autowired
    private NettyPushUtil nettyPushUtil;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        Trigger trigger = jobExecutionContext.getTrigger();
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        String data = jobDataMap.getString("data");
        //执行业务操作
        try {
            nettyPushUtil.sendMsg(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        //完成后删除触发器和任务
        scheduleService.cancelScheduleJob(trigger.getKey().getName());
    }
}
