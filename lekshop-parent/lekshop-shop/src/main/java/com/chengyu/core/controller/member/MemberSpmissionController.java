package com.chengyu.core.controller.member;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberSpmissionLog;
import com.chengyu.core.service.funds.MemberSpmissionLogService;
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
 * @title  佣金明细
 * @author LeGreen
 * @date   2021/1/9
 */
@Api(tags = "推广佣金明细")
@Controller
@RequestMapping("/member")
public class MemberSpmissionController extends UserBaseController {
	
	@Autowired
	private MemberSpmissionLogService spmissionLogService;

	@ApiOperation(value = "佣金明细")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderNo", value = "流水号"),
			@ApiImplicitParam(name = "dateFrom", value = "开始时间"),
			@ApiImplicitParam(name = "dateTo", value = "结束时间"),
			@ApiImplicitParam(name = "inOut", value = "1收入>>-1支出")
	})
	@ResponseBody
	@RequestMapping(value="/spmission/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberSpmissionLog>> getList(String orderNo, Date dateFrom , Date dateTo, Integer inOut, Integer page, Integer pageSize) throws ServiceException {
		AccountSearchForm form = new AccountSearchForm();
		form.setMemberId(getCurrentMemberId());
		form.setOrderNo(orderNo);
		form.setDateFrom(dateFrom);
		form.setDateTo(dateTo);
		form.setInOut(inOut);
		List<UmsMemberSpmissionLog> list = spmissionLogService.getAccountLog(form, page, pageSize);
		if(CollectionUtil.isNotEmpty(list)){
			for(UmsMemberSpmissionLog log : list){
				if(log.getDevoteId() != null){
					log.setDevoteName(memberService.getMemberById(log.getDevoteId()).getUid().toString());
				}
			}
		}
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "佣金转余额")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "spmission", value = "推广佣金")
	})
	@ResponseBody
	@RequestMapping(value="/spmission/spmissionToBalance", method=RequestMethod.POST)
	public CommonResult<Object> spmissionToBalance(BigDecimal spmission) throws Exception {
		if(spmission.compareTo(BigDecimal.ZERO) <= 0){
			return CommonResult.failed("兑换佣金必须大于0!");
		}

		UmsMember member = getCurrentMember();
		super.validateRepeat("do-spmissionToBalance-time-between-" + member.getId(), 5000L, "请勿重复操作,5秒后再试!");
		spmissionLogService.spmissionToBalance(member, spmission);
		return CommonResult.success(null);
	}
}