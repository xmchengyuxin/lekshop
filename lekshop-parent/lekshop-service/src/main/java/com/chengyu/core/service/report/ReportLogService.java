package com.chengyu.core.service.report;

import com.chengyu.core.domain.form.ReportSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @title  报表记录
 * @author LeGreen
 * @date   2022/5/23
 */
public interface ReportLogService {
	
	/**
	 * 获取门店日报表
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<RepShopDay>
	 * @throws ServiceException 业务异常
	 */
	List<RepShopDay> getRepShopDay(ReportSearchForm form, Integer page, Integer pageSize) throws ServiceException;
	
	/**
	 * 获取门店月报表
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<RepShopMonth>
	 * @throws ServiceException 业务异常
	 */
	List<RepShopMonth> getRepShopMonth(ReportSearchForm form, Integer page, Integer pageSize) throws ServiceException;
	
	/**
	 * 获取平台日报表
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<RepPlatformDay>
	 * @throws ServiceException 业务异常
	 */
	List<RepPlatformDay> getRepPlatformDay(ReportSearchForm form, Integer page, Integer pageSize) throws ServiceException;
	
	/**
	 * 获取平台月报表
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<RepPlatformMonth>
	 * @throws ServiceException 业务异常
	 */
	List<RepPlatformMonth> getRepPlatformMonth(ReportSearchForm form, Integer page, Integer pageSize) throws ServiceException;
	
	/**
	 * 商品日报
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<RepPlatformGoodsDay>
	 * @throws ServiceException 业务异常
	 */
	List<RepPlatformGoodsDay> getRepPlatformGoodsDay(ReportSearchForm form, Integer page, Integer pageSize) throws ServiceException;

	/**
	 * 商品月报
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<RepPlatformGoodsMonth>
	 * @throws ServiceException 业务异常
	 */
	List<RepPlatformGoodsMonth> getRepPlatformGoodsMonth(ReportSearchForm form, Integer page, Integer pageSize) throws ServiceException;

	/**
	 * 顾客人数报表
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<RepMemberDay>
	 * @throws ServiceException 业务异常
	 */
	List<RepMemberDay> getRepMemberDay(ReportSearchForm form, Integer page, Integer pageSize) throws ServiceException;

	/**
	 * 顾客人数报表
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<RepMemberMonth>
	 * @throws ServiceException 业务异常
	 */
	List<RepMemberMonth> getRepMemberMonth(ReportSearchForm form, Integer page, Integer pageSize) throws ServiceException;

	/**
	 * 转化率统计
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  form
	 * @return RepConversionDay
	 * @throws ServiceException 业务异常
	 */
	RepConversionDay getRepConversionDay(ReportSearchForm form) throws ServiceException;

	/**
	 * 转化率报表
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  form
	 * @return List<RepConversionDay>
	 * @throws ServiceException 业务异常
	 */
	List<RepConversionDay> getRepConversionDayList(ReportSearchForm form) throws ServiceException;
	
	/**
	 * 店铺商品报表
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<RepShopGoodsDay>
	 * @throws ServiceException 业务异常
	 */
	List<RepShopGoodsDay> getRepShopGoodsDay(ReportSearchForm form, Integer page, Integer pageSize) throws ServiceException;

	/**
	 * 店铺商品月报
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<RepShopGoodsMonth>
	 * @throws ServiceException 业务异常
	 */
	List<RepShopGoodsMonth> getRepShopGoodsMonth(ReportSearchForm form, Integer page, Integer pageSize) throws ServiceException;

	/**
	 * 统计营业额
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  shopId
	 * @param  beginTime
	 * @param  endTime
	 * @return BigDecimal
	 * @throws ServiceException 业务异常
	 */
	BigDecimal countBussinessAmount(Integer shopId, Date beginTime, Date endTime) throws ServiceException;
	
	/**
	 * 统计商品退款金额
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  shopId
	 * @param  beginTime
	 * @param  endTime
	 * @return BigDecimal
	 * @throws ServiceException 业务异常
	 */
	BigDecimal countGoodsRefundAmount(Integer shopId, Date beginTime, Date endTime) throws ServiceException;
	
	/**
	 * 商品大类汇总统计
	 * @author LeGreen
	 * @date   2022/5/23
	 * @param  shopId
	 * @param  beginTime
	 * @param  endTime
	 * @return List<PmsGoodsCate>
	 * @throws ServiceException 业务异常
	 */
	List<PmsGoodsCate> getGoodsCateList(Integer shopId, Date beginTime, Date endTime) throws ServiceException;
	
	List<RepShopGoodsDay> countShopGoodsByCateId(Integer shopId, Integer cateId, Date beginTime, Date endTime) throws ServiceException;
	
	List<RepPlatformGoodsDay> countPlatformGoodsByCateId(Integer cateId, Date beginTime, Date endTime) throws ServiceException;
}