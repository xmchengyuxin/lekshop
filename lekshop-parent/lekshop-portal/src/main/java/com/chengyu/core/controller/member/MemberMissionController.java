package com.chengyu.core.controller.member;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberMissionLog;
import com.chengyu.core.service.funds.MemberMissionLogService;
import com.chengyu.core.service.member.MemberRechargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @title  佣金
 * @author LeGreen
 * @date   2021/1/9
 */
@Api(tags = "佣金")
@Controller
@RequestMapping("/member")
public class MemberMissionController extends UserBaseController {
	
	@Autowired
	private MemberRechargeService rechargeService;
	@Autowired
	private MemberMissionLogService missionLogService;

	@ApiOperation(value = "佣金明细")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderNo", value = "流水号"),
		@ApiImplicitParam(name = "dateFrom", value = "开始时间"),
		@ApiImplicitParam(name = "dateTo", value = "结束时间"),
		@ApiImplicitParam(name = "inOut", value = "1收入>>-1支出")
	})
	@ResponseBody
	@RequestMapping(value="/mission/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberMissionLog>> getList(String orderNo, Date dateFrom , Date dateTo, Integer inOut, Integer page, Integer pageSize) throws ServiceException {
		AccountSearchForm form = new AccountSearchForm();
		form.setMemberId(getCurrentMemberId());
		form.setOrderNo(orderNo);
		form.setDateFrom(dateFrom);
		form.setDateTo(dateTo);
		form.setInOut(inOut);
		List<UmsMemberMissionLog> list = missionLogService.getAccountLog(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "兑换资金")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mission", value = "兑换点数")
	})
	@ResponseBody
	@RequestMapping(value="/mission/pointToBalance", method=RequestMethod.POST)
	public CommonResult<Object> pointToBalance(BigDecimal mission) throws Exception {
		if(mission.compareTo(BigDecimal.ZERO) <= 0){
			return CommonResult.failed("兑换点数必须大于0!");
		}

		UmsMember member = getCurrentMember();
		rechargeService.pointToBalance(member, mission);
		return CommonResult.success(null);
	}

}