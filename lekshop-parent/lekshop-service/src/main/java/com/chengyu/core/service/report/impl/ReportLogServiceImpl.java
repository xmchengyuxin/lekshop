package com.chengyu.core.service.report.impl;

import com.chengyu.core.domain.form.ReportSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.*;
import com.chengyu.core.model.*;
import com.chengyu.core.service.report.ReportLogService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ReportLogServiceImpl implements ReportLogService {

	@Autowired
	private RepShopDayMapper repShopDayMapper;
	@Autowired
	private RepShopMonthMapper repShopMonthMapper;
	@Autowired
	private RepPlatformDayMapper repPlatformDayMapper;
	@Autowired
	private RepPlatformMonthMapper repPlatformMonthMapper;
	@Autowired
	private RepPlatformGoodsDayMapper repPlatformGoodsDayMapper;
	@Autowired
	private RepPlatformGoodsMonthMapper repPlatformGoodsMonthMapper;
	@Autowired
	private RepMemberDayMapper repMemberDayMapper;
	@Autowired
	private RepMemberMonthMapper repMemberMonthMapper;
	@Autowired
	private RepConversionDayMapper repConversionMapper;
	@Autowired
	private CustomReportLogMapper reportLogMapper;
	@Autowired
	private RepShopGoodsDayMapper repShopGoodsDayMapper;
	@Autowired
	private RepShopGoodsMonthMapper repShopGoodsMonthMapper;

	@Override
	public List<RepShopDay> getRepShopDay(ReportSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		RepShopDayExample example = new RepShopDayExample();
		example.setOrderByClause("add_time desc, business_amount desc");
		RepShopDayExample.Criteria criteria = example.createCriteria();
		if(form.getShopId() != null){
			criteria.andShopIdEqualTo(form.getShopId());
		}
		if(StringUtils.isNotBlank(form.getShopName())){
			criteria.andShopNameLike("%"+form.getShopName()+"%");
		}
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		return repShopDayMapper.selectByExample(example);
	}
	
	@Override
	public List<RepShopMonth> getRepShopMonth(ReportSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		RepShopMonthExample example = new RepShopMonthExample();
		example.setOrderByClause("add_time desc, business_amount desc");
		RepShopMonthExample.Criteria criteria = example.createCriteria();
		if(form.getShopId() != null){
			criteria.andShopIdEqualTo(form.getShopId());
		}
		if(StringUtils.isNotBlank(form.getShopName())){
			criteria.andShopNameLike("%"+form.getShopName()+"%");
		}
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		return repShopMonthMapper.selectByExample(example);
	}
	
	@Override
	public List<RepPlatformDay> getRepPlatformDay(ReportSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		RepPlatformDayExample example = new RepPlatformDayExample();
		example.setOrderByClause("add_time desc, business_amount desc");
		RepPlatformDayExample.Criteria criteria = example.createCriteria();
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		return repPlatformDayMapper.selectByExample(example);
	}
	
	@Override
	public List<RepPlatformMonth> getRepPlatformMonth(ReportSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		RepPlatformMonthExample example = new RepPlatformMonthExample();
		example.setOrderByClause("add_time desc, business_amount desc");
		RepPlatformMonthExample.Criteria criteria = example.createCriteria();
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		return repPlatformMonthMapper.selectByExample(example);
	}
	
	@Override
	public List<RepPlatformGoodsDay> getRepPlatformGoodsDay(ReportSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		RepPlatformGoodsDayExample example = new RepPlatformGoodsDayExample();
		example.setOrderByClause("cate_id asc, add_time desc, sell_num desc");
		RepPlatformGoodsDayExample.Criteria criteria = example.createCriteria();
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		if(StringUtils.isNotBlank(form.getGoodsName())){
			criteria.andGoodsNameLike("%"+form.getGoodsName()+"%");
		}
		if(form.getCateId() != null){
			criteria.andCateIdEqualTo(form.getCateId());
		}
		return repPlatformGoodsDayMapper.selectByExample(example);
	}
	
	@Override
	public List<RepPlatformGoodsMonth> getRepPlatformGoodsMonth(ReportSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		RepPlatformGoodsMonthExample example = new RepPlatformGoodsMonthExample();
		example.setOrderByClause("cate_id asc, add_time desc, sell_num desc");
		RepPlatformGoodsMonthExample.Criteria criteria = example.createCriteria();
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		if(StringUtils.isNotBlank(form.getGoodsName())){
			criteria.andGoodsNameLike("%"+form.getGoodsName()+"%");
		}
		if(form.getCateId() != null){
			criteria.andCateIdEqualTo(form.getCateId());
		}
		return repPlatformGoodsMonthMapper.selectByExample(example);
	}
	
	@Override
	public List<RepMemberDay> getRepMemberDay(ReportSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		RepMemberDayExample example = new RepMemberDayExample();
		example.setOrderByClause("add_time desc");
		RepMemberDayExample.Criteria criteria = example.createCriteria();
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		return repMemberDayMapper.selectByExample(example);
	}
	
	@Override
	public List<RepMemberMonth> getRepMemberMonth(ReportSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		RepMemberMonthExample example = new RepMemberMonthExample();
		example.setOrderByClause("add_time desc");
		RepMemberMonthExample.Criteria criteria = example.createCriteria();
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		return repMemberMonthMapper.selectByExample(example);
	}
	
	@Override
	public RepConversionDay getRepConversionDay(ReportSearchForm form) throws ServiceException {
		return reportLogMapper.getRepConversionDay(form.getDateFrom(), form.getDateTo());
	}

	@Override
	public List<RepConversionDay> getRepConversionDayList(ReportSearchForm form) throws ServiceException {
		RepConversionDayExample example = new RepConversionDayExample();
		example.setOrderByClause("day desc limit 15");
		return repConversionMapper.selectByExample(example);
	}
	
	@Override
	public List<RepShopGoodsDay> getRepShopGoodsDay(ReportSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		RepShopGoodsDayExample example = new RepShopGoodsDayExample();
		example.setOrderByClause("cate_id asc, add_time desc, sell_num desc");
		RepShopGoodsDayExample.Criteria criteria = example.createCriteria();
		if(form.getShopId() != null){
			criteria.andShopIdEqualTo(form.getShopId());
		}
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		if(StringUtils.isNotBlank(form.getGoodsName())){
			criteria.andGoodsNameLike("%"+form.getGoodsName()+"%");
		}
		if(form.getCateId() != null){
			criteria.andCateIdEqualTo(form.getCateId());
		}
		return repShopGoodsDayMapper.selectByExample(example);
	}
	
	@Override
	public List<RepShopGoodsMonth> getRepShopGoodsMonth(ReportSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		RepShopGoodsMonthExample example = new RepShopGoodsMonthExample();
		example.setOrderByClause("cate_id asc, add_time desc, sell_num desc");
		RepShopGoodsMonthExample.Criteria criteria = example.createCriteria();
		if(form.getShopId() != null){
			criteria.andShopIdEqualTo(form.getShopId());
		}
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		if(StringUtils.isNotBlank(form.getGoodsName())){
			criteria.andGoodsNameLike("%"+form.getGoodsName()+"%");
		}
		if(form.getCateId() != null){
			criteria.andCateIdEqualTo(form.getCateId());
		}
		return repShopGoodsMonthMapper.selectByExample(example);
	}

	@Override
	public List<PmsGoodsCate> getGoodsCateList(Integer shopId, Date beginTime, Date endTime) {
		List<PmsGoodsCate> list;
		if(shopId != null){
			list = reportLogMapper.getGoodsCateListByShopId(shopId, beginTime, endTime);
		}else {
			list = reportLogMapper.getGoodsCateListByPlatform(beginTime, endTime);
		}
		return list;
	}

	@Override
	public List<RepShopGoodsDay> countShopGoodsByCateId(Integer shopId, Integer cateId, Date beginTime, Date endTime) {
		return reportLogMapper.countShopGoodsByCateId(shopId, cateId, beginTime, endTime);
	}

	@Override
	public List<RepPlatformGoodsDay> countPlatformGoodsByCateId(Integer cateId, Date beginTime, Date endTime) {
		return reportLogMapper.countPlatformGoodsByCateId(cateId, beginTime, endTime);
	}

	@Override
	public BigDecimal countBussinessAmount(Integer shopId, Date beginTime, Date endTime) throws ServiceException {
		BigDecimal amount = reportLogMapper.countBussinessAmount(shopId, beginTime, endTime);
		return amount == null ? BigDecimal.ZERO : amount;
	}

	@Override
	public BigDecimal countGoodsRefundAmount(Integer shopId, Date beginTime, Date endTime) throws ServiceException {
		BigDecimal amount = reportLogMapper.countGoodsRefundAmount(shopId, beginTime, endTime);
		return amount == null ? BigDecimal.ZERO : amount;
	}

}