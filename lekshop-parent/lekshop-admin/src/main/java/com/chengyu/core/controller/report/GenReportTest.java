package com.chengyu.core.controller.report;

import com.chengyu.core.service.order.OrderService;
import com.chengyu.core.service.report.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GenReportTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GenReportTest.class);
	
	@Autowired
	private ReportService reportService;

	//门店日报表
	public void genShopDayReport(Date beginTime, Date endTime){
		try {
			reportService.genShopDayReport(beginTime, endTime);
		} catch (Exception e) {
			LOGGER.info("门店日报表定时异常:{}", e);
		}
	}
	
	//品牌日报表
	public void genPlatformDayReport(Date beginTime, Date endTime){
		try {
			reportService.genPlatformDayReport(beginTime, endTime);
		} catch (Exception e) {
			LOGGER.info("品牌日报表定时异常:{}", e);
		}
	}
	
	//顾客日报表
	public void genMemberDayReport(Date beginTime, Date endTime){
		try {
			reportService.genMemberDayReport(beginTime, endTime);
		} catch (Exception e) {
			LOGGER.info("顾客日报表定时异常:{}", e);
		}
	}
	
	//转化日报表
	public void genConversionDayReport(Date beginTime, Date endTime){
		try {
			reportService.genConversionDayReport(beginTime, endTime);
		} catch (Exception e) {
			LOGGER.info("转化日报表定时异常:{}", e);
		}
	}
	
	//门店月报表
	public void genShopMonthReport(Date beginTime, Date endTime){
		try {
			reportService.genShopMonthReport(beginTime, endTime);
		} catch (Exception e) {
			LOGGER.info("门店月报表定时异常:{}", e);
		}
	}
	
	//品牌月报表
	public void genPlatformMonthReport(Date beginTime, Date endTime){
		try {
			reportService.genPlatformMonthReport(beginTime, endTime);
		} catch (Exception e) {
			LOGGER.info("品牌月报表定时异常:{}", e);
		}
	}
	
	//顾客月报表
	public void genMemberMonthReport(Date beginTime, Date endTime){
		try {
			reportService.genMemberMonthReport(beginTime, endTime);
		} catch (Exception e) {
			LOGGER.info("顾客月报表定时异常:{}", e);
		}
	}
	
	public void reGenShopPlatformDayReport(Integer shopId, Integer brandId, Date date){
		try {
			reportService.reGenDayReport(shopId, brandId, date);
		} catch (Exception e) {
			LOGGER.info("重新生成日报表异常:{}", e);
		}
	}
}
