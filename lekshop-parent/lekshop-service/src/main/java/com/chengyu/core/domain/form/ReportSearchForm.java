package com.chengyu.core.domain.form;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.exception.ServiceException;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReportSearchForm {
	
	private Integer shopId;
	
	private String shopName;
	
	private String goodsName;
	
	private Integer cateId;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	public void validateDayRange() throws ServiceException {
		if(dateFrom == null){
			throw new ServiceException("请选择开始日期");
		}
		if(dateTo == null){
			throw new ServiceException("请选择结束日期");
		}
		if(dateFrom.after(dateTo)){
			throw new ServiceException("结束日期不能早于开始日期");
		}
		if(DateUtil.betweenDay(dateFrom, dateTo, true) > 31){
			throw new ServiceException("查询日期跨度不能超过31天");
		}
	} 
	
	public void validateMonthRange() throws ServiceException{
		if(dateFrom == null){
			throw new ServiceException("请选择开始月份");
		}
		if(dateTo == null) {
			throw new ServiceException("请选择结束月份");
		}
		if(DateUtil.betweenMonth(dateFrom, dateTo, true) > 12){
			throw new ServiceException("查询月份跨度不能超过12个月");
		}
	} 
}