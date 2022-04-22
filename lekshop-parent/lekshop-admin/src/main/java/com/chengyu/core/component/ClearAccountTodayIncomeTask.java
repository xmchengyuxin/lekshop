package com.chengyu.core.component;

import com.chengyu.core.service.config.ConfigAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @title  清除收款账户今日收款额度
 * @author LeGreen
 * @date   2021/2/18
 */
@Component
public class ClearAccountTodayIncomeTask {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(ClearAccountTodayIncomeTask.class);
	
	@Autowired
	private ConfigAccountService configAccountService;

	/**
     *每日两点进行自动催评
     */
	@Scheduled(cron="0 00 00 ? * *")
    private void taskSchedule(){
		LOGGER.info("执行清除额度");
		configAccountService.clearTodayIncome();
	}
	 
}
