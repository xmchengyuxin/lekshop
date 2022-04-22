package com.chengyu.core.service.schedule;

import java.util.Date;

import org.quartz.Job;

/**
 * @title  Quartz定时任务操作类
 * @author LeGreen
 * @date   2021/1/6
 */
public interface ScheduleService {

	/**
	 * 通过CRON表达式调度任务
	 * @author LeGreen
	 * @date   2021/1/5
	 * @param  jobBeanClass
	 * @param  cron
	 * @param  data
	 * @return java.lang.String
	 */
    String scheduleJob(Class<? extends Job> jobBeanClass, String cron, String data);

    /**
     * 调度指定时间的任务
     * @author LeGreen
     * @date   2021/1/5
     * @param  jobBeanClass
     * @param  startTime
     * @param  data
     * @return java.lang.String
     */
    String scheduleFixTimeJob(Class<? extends Job> jobBeanClass, Date startTime, String data);

    /**
     * 取消定时任务
     * @author LeGreen
     * @date   2021/1/5
     * @param  jobName
     * @return java.lang.Boolean
     */
    Boolean cancelScheduleJob(String jobName);
}
