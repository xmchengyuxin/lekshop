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
		if(form.getDateTo() != null){
			form.setDateTo(DateUtil.endOfMonth(form.getDateTo()));
		}
		List<RepShopMonth> list = reportLogService.getRepShopMonth(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@ApiOperation(value = "平台营业日报表")
	@ResponseBody
	@RequestMapping(value="/platformReport/getDayList", method=RequestMethod.GET)
	public CommonResult<CommonPage<RepPlatformDay>> getPlatformDayList(
			ReportSearchForm form, 
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		form.validateDayRange();
		List<RepPlatformDay> list = reportLogService.getRepPlatformDay(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@ApiOperation(value = "平台营业月报表")
	@ResponseBody
	@RequestMapping(value="/platformReport/getMonthList", method=RequestMethod.GET)
	public CommonResult<CommonPage<RepPlatformMonth>> getPlatformMonthList(
			ReportSearchForm form, 
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		form.validateMonthRange();
		if(form.getDateTo() != null){
			form.setDateTo(DateUtil.endOfMonth(form.getDateTo()));
		}
		List<RepPlatformMonth> list = reportLogService.getRepPlatformMonth(form, page, pageSize);
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
		if(form.getShopId() == null){
			List<RepPlatformGoodsDay> list = reportLogService.getRepPlatformGoodsDay(form, page, pageSize);
			return CommonResult.success(CommonPage.restPage(list));
		}else{
			List<RepShopGoodsDay> list = reportLogService.getRepShopGoodsDay(form, page, pageSize);
			return CommonResult.success(CommonPage.restPage(list));
		}
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
		if(form.getDateTo() != null){
			form.setDateTo(DateUtil.endOfMonth(form.getDateTo()));
		}
		if(form.getShopId() == null){
			List<RepPlatformGoodsMonth> list = reportLogService.getRepPlatformGoodsMonth(form, page, pageSize);
			return CommonResult.success(CommonPage.restPage(list));
		}else{
			List<RepShopGoodsMonth> list = reportLogService.getRepShopGoodsMonth(form, page, pageSize);
			return CommonResult.success(CommonPage.restPage(list));
		}
	}

	@ApiOperation(value = "顾客人数日报表")
	@ResponseBody
	@RequestMapping(value="/memberReport/getDayList", method=RequestMethod.GET)
	public CommonResult<CommonPage<RepMemberDay>> getMemberDayList(
			ReportSearchForm form, 
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		form.validateDayRange();
		List<RepMemberDay> list = reportLogService.getRepMemberDay(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@ApiOperation(value = "顾客人数月报表")
	@ResponseBody
	@RequestMapping(value="/memberReport/getMonthList", method=RequestMethod.GET)
	public CommonResult<CommonPage<RepMemberMonth>> getMemberMonthList(
			ReportSearchForm form, 
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		
		form.validateMonthRange();
		if(form.getDateTo() != null){
			form.setDateTo(DateUtil.endOfMonth(form.getDateTo()));
		}
		List<RepMemberMonth> list = reportLogService.getRepMemberMonth(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@ApiOperation(value = "转化率报表")
	@ResponseBody
	@RequestMapping(value="/conversionReport/getList", method=RequestMethod.GET)
	public CommonResult<RepConversionDay> getRepConversionDay(ReportSearchForm form) throws Exception {
		RepConversionDay repconversionDay = reportLogService.getRepConversionDay(form);
		return CommonResult.success(repconversionDay);
	}
	
}