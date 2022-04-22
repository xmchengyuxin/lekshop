package com.chengyu.core.service.task;

import org.quartz.Job;

import java.util.Date;

/**
 * @title  定时任务
 * @author LeGreen
 * @date   2021/6/5
 */
public interface TaskTriggerService {

    /**
     * 添加定时器
     * @author LeGreen
     * @date   2021/6/5
     * @param  jobBeanClass
     * @param  startTime
     * @param  data
     */
    void addTrigger(Class<? extends Job> jobBeanClass, Date startTime, String data);

    /**
     * 执行定时器
     * @author LeGreen
     * @date   2021/6/5
     * @param  triggerId
     */
    void excute(String triggerId);

    /**
     * 取消定时器
     * @author LeGreen
     * @date   2021/6/5
     * @param  type
     * @param  taskNo
     */
    void cancel(String type, String taskNo);
}
