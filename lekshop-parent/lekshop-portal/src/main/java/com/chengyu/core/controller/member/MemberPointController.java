package com.chengyu.core.controller.member;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMemberPointLog;
import com.chengyu.core.service.funds.MemberPointLogService;
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

@Api(tags = "积分明细")
@Controller
@RequestMapping("/member")
public class MemberPointController extends UserBaseController {
	
	@Autowired
	private MemberPointLogService memberPointLogService;

	@ApiOperation(value = "积分明细")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderNo", value = "流水号"),
			@ApiImplicitParam(name = "dateFrom", value = "开始时间"),
			@ApiImplicitParam(name = "dateTo", value = "结束时间"),
			@ApiImplicitParam(name = "inOut", value = "1收入>>-1支出")
	})
	@ResponseBody
	@RequestMapping(value="/point/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberPointLog>> getList(String orderNo, Date dateFrom , Date dateTo, Integer inOut, Integer page, Integer pageSize) throws ServiceException {
		AccountSearchForm form = new AccountSearchForm();
		form.setMemberId(getCurrentMemberId());
		form.setOrderNo(orderNo);
		form.setDateFrom(dateFrom);
		form.setDateTo(dateTo);
		form.setInOut(inOut);
		List<UmsMemberPointLog> list = memberPointLogService.getAccountLog(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

}