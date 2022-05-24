package com.chengyu.core.controller.report;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.form.ReportSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.*;
import com.chengyu.core.service.report.ReportLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "报表管理")
@Controller
@RequestMapping("/member")
public class ReportController extends ShopBaseController {
	
	@Autowired
	private ReportLogService reportLogService;

	@ApiOperation(value = "门店营业日报表")
	@ResponseBody
	@RequestMapping(value="/shopReport/getDayList", method=RequestMethod.GET)
	public CommonResult<CommonPage<RepShopDay>> getShopDayList(
			ReportSearchForm form,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {

		form.validateDayRange();
		if(form.getDateFrom() != null){
			form.setDateFrom(DateUtil.beginOfDay(form.getDateFrom()));
		}
		if(form.getDateTo() != null){
			form.setDateTo(DateUtil.endOfDay(form.getDateTo()));
		}
		form.setShopId(getCurrentShop().getId());
		List<RepShopDay> list = reportLogService.getRepShopDay(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@ApiOperation(value = "门店营业月报表")
	@ResponseBody
	@RequestMapping(value="/shopReport/getMonthList", method=RequestMethod.GET)
	public CommonResult<CommonPage<RepShopMonth>> getShopMonthList(
			ReportSearchForm form, 
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		
		form.validateMonthRange();
		if(form.getDateFrom() != null){
			form.setDateFrom(DateUtil.beginOfMonth(form.getDateFrom()));
		}
		if(form.getDateTo() != null){
			form.setDateTo(DateUtil.endOfMonth(form.getDateTo()));
		}
		form.setShopId(getCurrentShop().getId());
		List<RepShopMonth> list = reportLogService.getRepShopMonth(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "商品统计日报表")
	@ResponseBody
	@RequestMapping(value="/goodsReport/getDayList", method=RequestMethod.GET)
	public CommonResult<CommonPage> getGoodsDayList(
			ReportSearchForm form, 
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		
		form.validateDayRange();
		if(form.getDateFrom() != null){
			form.setDateFrom(DateUtil.beginOfDay(form.getDateFrom()));
		}
		if(form.getDateTo() != null){
			form.setDateTo(DateUtil.endOfDay(form.getDateTo()));
		}
		form.setShopId(getCurrentShop().getId());
		List<RepShopGoodsDay> list = reportLogService.getRepShopGoodsDay(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "商品统计月报表")
	@ResponseBody
	@RequestMapping(value="/goodsReport/getMonthList", method=RequestMethod.GET)
	public CommonResult<CommonPage> getGoodsMonthList(
			ReportSearchForm form, 
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		form.validateMonthRange();
		if(form.getDateFrom() != null){
			form.setDateFrom(DateUtil.beginOfMonth(form.getDateFrom()));
		}
		if(form.getDateTo() != null){
			form.setDateTo(DateUtil.endOfMonth(form.getDateTo()));
		}
		form.setShopId(getCurrentShop().getId());
		List<RepShopGoodsMonth> list = reportLogService.getRepShopGoodsMonth(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

}