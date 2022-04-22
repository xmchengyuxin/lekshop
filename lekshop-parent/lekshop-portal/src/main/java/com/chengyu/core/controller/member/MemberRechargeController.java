package com.chengyu.core.controller.member;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.form.RechargeSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberRecharge;
import com.chengyu.core.service.config.ConfigAccountService;
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
			return CommonResult.failed("充值金额必须大于0!");
		}
		
		UmsMember member = getCurrentMember();
		super.validateRepeat("do-recharge-time-between-" + member.getId(), 5000L, "请勿重复操作,5秒后再试!");
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
		@ApiImplicitParam(name = "payMethod", value = "支付方式>>1支付宝>>2微信>>3QQ钱包"),
		@ApiImplicitParam(name = "returnUrl", value = "同步回调地址"),
	})
	@ResponseBody
	@RequestMapping(value="/recharge/online", method=RequestMethod.POST)
	public CommonResult<String> online(String payMethod, BigDecimal amount, String returnUrl) throws Exception {
		if(amount.compareTo(BigDecimal.ZERO) <= 0){
			return CommonResult.failed("充值金额必须大于0!");
		}

		UmsMember member = getCurrentMember();
		super.validateRepeat("do-recharge-time-between-" + member.getId(), 5000L, "请勿重复操作,5秒后再试!");
		String url = rechargeService.rechargeOnline(member, payMethod, amount, returnUrl);
		return CommonResult.success(url);
	}

	@ApiOperation(value = "充值回调")
	@ResponseBody
	@RequestMapping(value="/recharge/callback", method=RequestMethod.GET)
	public CommonResult<String> callback(String out_trade_no, String trade_no, String trade_status, String sign, String type) throws ServiceException {
		/*if(StringUtils.isNotBlank(trade_status) && CodePay.SUCCESS.equals(trade_status)){
			//校验签名
			UmsMemberRecharge recharge = rechargeService.getRechargeLogByOrderNo(out_trade_no);
			//codePay.checkSign(recharge.getRealAmount(), out_trade_no, trade_no, type, sign) &&
			if(CommonConstant.WAIT.equals(recharge.getStatus())){
				//充值成功
				rechargeService.rechargSusByCallback(recharge);
			}
		}*/
		return CommonResult.success(null);
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