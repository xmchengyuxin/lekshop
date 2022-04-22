package com.chengyu.core.service.task.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.mapper.TaskTriggerMapper;
import com.chengyu.core.model.TaskTrigger;
import com.chengyu.core.model.TaskTriggerExample;
import com.chengyu.core.service.schedule.ScheduleService;
import com.chengyu.core.service.task.TaskTriggerService;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @title  定时器
 * @author LeGreen
 * @date   2021/6/5
 */
@Service
public class TaskTriggerServiceImpl implements TaskTriggerService {

	@Autowired
	private TaskTriggerMapper taskTriggerMapper;
	@Autowired
	private ScheduleService scheduleService;

	@Override
	public void addTrigger(Class<? extends Job> jobBeanClass, Date startTime, String data) {
		//先删除同类型同任务未执行的定时器
		this.cancel(jobBeanClass.getSimpleName(), data);
		String jobName = scheduleService.scheduleFixTimeJob(jobBeanClass, startTime, data);

		TaskTrigger trigger = new TaskTrigger();
		trigger.setType(jobBeanClass.getSimpleName());
		trigger.setTriggerId(jobName);
		trigger.setTaskNo(data);
		trigger.setStatus(CommonConstant.NO_INT);
		trigger.setStartTime(startTime);
		trigger.setAddTime(DateUtil.date());
		trigger.setUpdTime(trigger.getAddTime());
		taskTriggerMapper.insertSelective(trigger);
	}

	@Override
	public void excute(String triggerId) {
		TaskTriggerExample example = new TaskTriggerExample();
		example.createCriteria().andTriggerIdEqualTo(triggerId);

		TaskTrigger updateTrigger = new TaskTrigger();
		updateTrigger.setTriggerId(triggerId);
		updateTrigger.setStatus(CommonConstant.YES_INT);
		updateTrigger.setUpdTime(DateUtil.date());
		taskTriggerMapper.updateByExampleSelective(updateTrigger, example);
	}

	@Override
	public void cancel(String type, String taskNo) {
		if(taskNo == null){
			return;
		}
		TaskTriggerExample example = new TaskTriggerExample();
		example.createCriteria().andTypeEqualTo(type).andTaskNoEqualTo(taskNo).andStatusEqualTo(CommonConstant.NO_INT);
		List<TaskTrigger> list = taskTriggerMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			return;
		}
		List<String> triggerIds = list.stream().map(TaskTrigger::getTriggerId).collect(Collectors.toList());
		this.cancel(triggerIds);
	}

	public void cancel(List<String> triggerIds) {
		TaskTriggerExample example = new TaskTriggerExample();
		example.createCriteria().andTriggerIdIn(triggerIds);

		TaskTrigger updateTrigger = new TaskTrigger();
		updateTrigger.setStatus(CommonConstant.FAIL_INT);
		updateTrigger.setUpdTime(DateUtil.date());
		taskTriggerMapper.updateByExampleSelective(updateTrigger, example);

		for(String triggerId : triggerIds){
			scheduleService.cancelScheduleJob(triggerId);
		}
	}
}