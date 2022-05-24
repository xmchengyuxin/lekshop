package com.chengyu.core.component;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.service.report.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @title  报表定时器
 * @author LeGreen
 * @date   2022/5/23
 */
@Component
public class ReportTask {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportTask.class);
	
	@Autowired
	private ReportService reportService;
	
	/**
     *每日报表
     */
	@Scheduled(cron="0 15 00 ? * *")
//	@Scheduled(cron = "0 0/2 * ? * ?")
    private void dayReport(){
		Date now = com.chengyu.core.utils.DateUtil.addDay(new Date(), -1);
		Date beginTime = DateUtil.beginOfDay(now);
		Date endTime = DateUtil.endOfDay(now);
		//门店日报表
		try {
			reportService.genShopDayReport(beginTime, endTime);
		} catch (ServiceException e) {
			LOGGER.info("门店日报表定时异常:{}", e);
		}
		//平台日报表
		try {
			reportService.genPlatformDayReport(beginTime, endTime);
		} catch (ServiceException e) {
			LOGGER.info("品牌日报表定时异常:{}", e);
		}
		//顾客日报表
		try {
			reportService.genMemberDayReport(beginTime, endTime);
		} catch (ServiceException e) {
			LOGGER.info("顾客日报表定时异常:{}", e);
		}
		//转化日报表
		try {
			reportService.genConversionDayReport(beginTime, endTime);
		} catch (ServiceException e) {
			LOGGER.info("转化日报表定时异常:{}", e);
		}
    }
	
	/**
     *每月1号12点20分报表
     */
	@Scheduled(cron="0 20 0 1 * ?")
//	@Scheduled(cron = "0 0/2 * ? * ?")
    private void monthReport() throws Exception{
		Date now = com.chengyu.core.utils.DateUtil.addDay(new Date(), -1);
		Date beginTime = DateUtil.beginOfMonth(now);
		Date endTime = DateUtil.endOfMonth(now);
		//门店月报表
		reportService.genShopMonthReport(beginTime, endTime);
		//平台月报表
		reportService.genPlatformMonthReport(beginTime, endTime);
		//顾客月报表
		reportService.genMemberMonthReport(beginTime, endTime);
    }
	 
}
