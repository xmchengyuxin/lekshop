package com.chengyu.core.service.report;

import com.chengyu.core.exception.ServiceException;

import java.util.Date;
/**
 * @title  报表
 * @author LeGreen
 * @date   2022/5/23
 */
public interface ReportService {
	
	/**
	 * 门店日报
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  beginTime
	 * @param  endTime
	 * @throws ServiceException 业务异常
	 */
	void genShopDayReport(Date beginTime, Date endTime) throws ServiceException;
	
	/**
	 * 平台日报
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  beginTime
	 * @param  endTime
	 * @return void
	 * @throws ServiceException 业务异常
	 */
	void genPlatformDayReport(Date beginTime, Date endTime) throws ServiceException;
	
	/**
	 * 顾客日报表
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  beginTime
	 * @param  endTime
	 * @throws ServiceException 业务异常
	 */
	void genMemberDayReport(Date beginTime, Date endTime) throws ServiceException;
	
	/**
	 * 转化日报表
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  beginTime
	 * @param  endTime
	 * @throws ServiceException 业务异常
	 */
	void genConversionDayReport(Date beginTime, Date endTime) throws ServiceException;
	
	/**
	 * 门店月报表
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  beginTime
	 * @param  endTime
	 * @throws ServiceException 业务异常
	 */
	void genShopMonthReport(Date beginTime, Date endTime) throws ServiceException;
	
	/**
	 * 平台月报
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  beginTime
	 * @param  endTime
	 * @return void
	 * @throws ServiceException 业务异常
	 */
	void genPlatformMonthReport(Date beginTime, Date endTime) throws ServiceException;
	
	/**
	 * 顾客月报表
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  beginTime
	 * @param  endTime
	 * @throws ServiceException 业务异常
	 */
	void genMemberMonthReport(Date beginTime, Date endTime) throws ServiceException;
	
	/**
	 * 记录打开次数
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  type
	 * @throws ServiceException 业务异常
	 */
	void updateConversion(String type) throws ServiceException;

	/**
	 * 重新生成门店/平台日报表
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  shopId
	 * @param  now
	 * @throws ServiceException 业务异常
	 */
    void reGenDayReport(Integer shopId, Integer platformId, Date now) throws ServiceException;
}