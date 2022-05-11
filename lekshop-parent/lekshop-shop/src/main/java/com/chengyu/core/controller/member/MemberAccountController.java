package com.chengyu.core.controller.member;

import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMemberAccountLog;
import com.chengyu.core.service.funds.MemberAccountLogService;
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

/**
 * @title  资金明细
 * @author LeGreen
 * @date   2021/1/9
 */
@Api(tags = "资金明细")
@Controller
@RequestMapping("/member")
public class MemberAccountController extends ShopBaseController {
	
	@Autowired
	private MemberAccountLogService memberAccountLogService;

	@ApiOperation(value = "资金明细")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderNo", value = "流水号"),
		@ApiImplicitParam(name = "dateFrom", value = "开始时间"),
		@ApiImplicitParam(name = "dateTo", value = "结束时间"),
		@ApiImplicitParam(name = "inOut", value = "1收入>>-1支出")
	})
	@ResponseBody
	@RequestMapping(value="/account/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberAccountLog>> getList(String orderNo, Date dateFrom , Date dateTo, Integer inOut, Integer page, Integer pageSize) throws ServiceException {
		AccountSearchForm form = new AccountSearchForm();
		form.setMemberId(getCurrentMemberId());
		form.setOrderNo(orderNo);
		form.setDateFrom(dateFrom);
		form.setDateTo(dateTo);
		form.setInOut(inOut);
		List<UmsMemberAccountLog> list = memberAccountLogService.getAccountLog(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
}