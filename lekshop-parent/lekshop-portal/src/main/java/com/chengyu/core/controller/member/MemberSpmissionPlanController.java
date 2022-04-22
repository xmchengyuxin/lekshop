package com.chengyu.core.controller.member;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMemberSpmissionPlanLog;
import com.chengyu.core.service.funds.MemberSpmissionPlanLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @title  预计推广佣金
 * @author LeGreen
 * @date   2021/12/3
 */
@Api(tags = "预计推广佣金明细")
@Controller
@RequestMapping("/member")
public class MemberSpmissionPlanController extends UserBaseController {
	
	@Autowired
	private MemberSpmissionPlanLogService memberSpmissionPlanLogService;

	@ApiOperation(value = "预计佣金明细")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderNo", value = "流水号"),
			@ApiImplicitParam(name = "dateFrom", value = "开始时间"),
			@ApiImplicitParam(name = "dateTo", value = "结束时间"),
	})
	@ResponseBody
	@RequestMapping(value="/spmissionPlan/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberSpmissionPlanLog>> getList(String orderNo, Date dateFrom , Date dateTo, Integer page, Integer pageSize) throws ServiceException {
		AccountSearchForm form = new AccountSearchForm();
		form.setMemberId(getCurrentMemberId());
		form.setOrderNo(orderNo);
		form.setDateFrom(dateFrom);
		form.setDateTo(dateTo);
		List<UmsMemberSpmissionPlanLog> list = memberSpmissionPlanLogService.getAccountLog(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "统计预计收入和单量")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderNo", value = "流水号"),
			@ApiImplicitParam(name = "dateFrom", value = "开始时间"),
			@ApiImplicitParam(name = "dateTo", value = "结束时间"),
	})
	@ResponseBody
	@RequestMapping(value="/spmissionPlan/count", method=RequestMethod.GET)
	public CommonResult<Map<String,Object>> getList(String orderNo, Date dateFrom , Date dateTo) throws ServiceException {
		AccountSearchForm form = new AccountSearchForm();
		form.setMemberId(getCurrentMemberId());
		form.setOrderNo(orderNo);
		form.setDateFrom(dateFrom);
		form.setDateTo(dateTo);
		Map<String,Object> result = memberSpmissionPlanLogService.countPlanIncomeAndNum(form);
		return CommonResult.success(result);
	}
}