package com.chengyu.core.component;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.service.config.ConfigAccountService;
import com.chengyu.core.service.report.ReportService;
import com.chengyu.core.service.system.ThirdWarnService;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @title  固定时间执行定时器
 * @author LeGreen
 * @date   2022/12/21
 */
@Component
public class FixedTimeHandler {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(FixedTimeHandler.class);
	
	@Autowired
	private ConfigAccountService configAccountService;

	@Autowired
	private ReportService reportService;

	@Autowired
	private ThirdWarnService thirdWarnService;

	/**
     *清除收款账户今日收款额度
     */
	@XxlJob("clearTodayIncomeHandler")
	private void clearTodayIncome(){
		LOGGER.info("执行清除额度");
		configAccountService.clearTodayIncome();
	}


	/**
	 *每日报表
	 */
	@XxlJob("dayReportHandler")
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
	@XxlJob("monthReportHandler")
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

	@XxlJob("thirdWarnHandler")
	private void thirdWarn() throws Exception {
		LOGGER.info("执行第三方接口预警");
		thirdWarnService.warn();
	}
}
