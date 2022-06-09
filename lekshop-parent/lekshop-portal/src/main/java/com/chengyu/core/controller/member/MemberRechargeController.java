package com.chengyu.core.controller.member;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.form.PayBaseForm;
import com.chengyu.core.domain.form.RechargeSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberRecharge;
import com.chengyu.core.service.config.ConfigAccountService;
import com.chengyu.core.service.member.MemberRechargeService;
import com.chengyu.core.service.pay.PayService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title  充值中心
 * @author LeGreen
 * @date   2021/1/9
 */
@Api(tags = "充值中心")
@Controller
@RequestMapping("/member")
public class MemberRechargeController extends UserBaseController {
	
	@Autowired
	private MemberRechargeService rechargeService;
	@Autowired
	private ConfigAccountService configAccountService;
	@Autowired
	private PayService payService;

	@OperationLog
	@ApiOperation(value = "充值")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "accountId", value = "收款账号ID"),
		@ApiImplicitParam(name = "amount", value = "充值金额"),
		@ApiImplicitParam(name = "payMethod", value = "支付方式"),
		@ApiImplicitParam(name = "remitBank", value = "打款银行名称"),
		@ApiImplicitParam(name = "remitRealname", value = "打款人姓名"),
		@ApiImplicitParam(name = "remitAccount", value = "打款银行后四位"),
		@ApiImplicitParam(name = "img", value = "转账记录截图"),
	})
	@ResponseBody
	@RequestMapping(value="/recharge/save", method=RequestMethod.POST)
	public CommonResult<Map<String,Object>> save(Integer accountId, String payMethod, BigDecimal amount, String remitBank, String remitRealname, String remitAccount, String img) throws Exception {
		if(amount.compareTo(BigDecimal.ZERO) <= 0){
			throw new ServiceException("member.recharge.amount");
		}
		
		UmsMember member = getCurrentMember();
		super.validateRepeat("do-recharge-time-between-" + member.getId(), 5000L, "platform.validate.repeat");
		rechargeService.recharge(member, payMethod, amount, remitBank, remitRealname, remitAccount, img);

		if(accountId != null){
			configAccountService.updateAmount(accountId, amount, true);
		}
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "在线充值")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "amount", value = "充值金额"),
	})
	@ResponseBody
	@RequestMapping(value="/recharge/online", method=RequestMethod.POST)
	public CommonResult<Map<String,Object>> online(PayBaseForm payBaseForm, BigDecimal amount) throws Exception {
		if(amount.compareTo(BigDecimal.ZERO) <= 0){
			throw new ServiceException("member.recharge.amount");
		}

		UmsMember member = getCurrentMember();
		super.validateRepeat("do-recharge-time-between-" + member.getId(), 5000L, "platform.validate.repeat");
		String orderNo = rechargeService.rechargeOnline(member, payBaseForm.getPayMethod(), amount);

		//调用支付接口
		payBaseForm.setIp(this.getRequestIp());
		String message = payService.recharge(member, orderNo, payBaseForm);

		Map<String,Object> result = new HashMap<>();
		result.put("payOrderNo", orderNo);
		result.put("message", message);
		return CommonResult.success(result);
	}

	@ApiOperation(value = "充值列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderNo", value = "流水号")
	})
	@ResponseBody
	@RequestMapping(value="/recharge/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberRecharge>> getList(String orderNo, Integer page, Integer pageSize) throws ServiceException {
		RechargeSearchForm form = new RechargeSearchForm();
		form.setMemberId(getCurrentMember().getId());
		form.setOrderNo(orderNo);
		List<UmsMemberRecharge> list = rechargeService.getRechargeLog(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "取消充值")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderNo", value = "订单编号"),
	})
	@ResponseBody
	@RequestMapping(value="/recharge/cancel", method=RequestMethod.POST)
	public CommonResult<String> cancel(String orderNo) throws Exception {
		rechargeService.cancel(orderNo);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "充值模板列表")
	@ResponseBody
	@RequestMapping(value="/recharge/getTemplateList", method=RequestMethod.GET)
	public CommonResult<List<UmsMemberRecharge>> getTemplateList() throws ServiceException {
		List<UmsMemberRecharge> list = rechargeService.getRechargeTemplateList(getCurrentMember().getId());
		return CommonResult.success(list);
	}
}