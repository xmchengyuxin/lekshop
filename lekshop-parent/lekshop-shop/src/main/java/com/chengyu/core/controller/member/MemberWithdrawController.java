package com.chengyu.core.controller.member;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.crypto.SecureUtil;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.BankForm;
import com.chengyu.core.domain.form.WithdrawSearchForm;
import com.chengyu.core.domain.result.WithdrawCountResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberBank;
import com.chengyu.core.model.UmsMemberWithdraw;
import com.chengyu.core.service.member.MemberBankService;
import com.chengyu.core.service.member.MemberWithdrawService;
import com.chengyu.core.service.system.VerifyCodeService;
import com.chengyu.core.utils.StringUtils;
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

/**
 * @title  提现
 * @author LeGreen
 * @date   2021/1/9
 */
@Api(tags = "提现管理")
@Controller
@RequestMapping("/member")
public class MemberWithdrawController extends ShopBaseController {
	
	@Autowired
	private MemberWithdrawService withdrawService;
	@Autowired
	private VerifyCodeService verifyCodeService;
	@Autowired
	private MemberBankService memberBankService;

	@ApiOperation(value = "统计已提现总额")
	@ResponseBody
	@RequestMapping(value="/withdraw/count", method=RequestMethod.GET)
	public CommonResult<WithdrawCountResult> count() throws ServiceException {
		WithdrawSearchForm form = new WithdrawSearchForm();
		form.setMemberId(getCurrentMemberId());
		WithdrawCountResult result = withdrawService.countWithdraw(form);
		return CommonResult.success(result);
	}

	@ApiOperation(value = "提现列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "提现方式>>1银行>>2支付宝>>3微信", required=true),
	})
	@ResponseBody
	@RequestMapping(value="/withdraw/getList", method=RequestMethod.GET)
    public CommonResult<CommonPage<UmsMemberWithdraw>> getWithdrawLog(Integer type, Integer page, Integer pageSize) throws ServiceException {
		WithdrawSearchForm form = new WithdrawSearchForm();
		form.setMemberId(getCurrentMemberId());
		form.setType(type);
		List<UmsMemberWithdraw> list = withdrawService.getWithdrawLog(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
    }

	@ApiOperation(value = "最近的提现记录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "提现方式>>1银行>>2支付宝>>3微信", required=true),
	})
	@ResponseBody
	@RequestMapping(value="/withdraw/getLast", method=RequestMethod.GET)
	public CommonResult<UmsMemberWithdraw> getLastWithdraw(Integer type) throws ServiceException {
		//先获取绑定的银行卡, 支付宝, 微信
		UmsMemberBank bank = memberBankService.getMemberBankByMemberId(getCurrentMemberId());
		if(bank != null){
			UmsMemberWithdraw withdraw = new UmsMemberWithdraw();
			withdraw.setRealname(bank.getAccountName());
			if(type == 1 && StringUtils.isNotBlank(bank.getBankAccount())){
				withdraw.setBank(bank.getBank());
				withdraw.setBankName(bank.getBank());
				withdraw.setBankAccount(bank.getBankAccount());
				return CommonResult.success(withdraw);
			}else if(type == 2 && StringUtils.isNotBlank(bank.getZfbAccount())){
				withdraw.setBankAccount(bank.getZfbAccount());
				withdraw.setBank(bank.getZfbErweima());
				return CommonResult.success(withdraw);
			}else if(type == 3 && StringUtils.isNotBlank(bank.getWxAccount())){
				withdraw.setBankAccount(bank.getWxErweima());
				withdraw.setBank(bank.getWxAccount());
				return CommonResult.success(withdraw);
			}
		}
		WithdrawSearchForm form = new WithdrawSearchForm();
		form.setMemberId(getCurrentMemberId());
		form.setType(type);
		form.setStatus(CommonConstant.SUS);
		List<UmsMemberWithdraw> list = withdrawService.getWithdrawLog(form, 1, 1);
		return CommonResult.success(CollectionUtil.isEmpty(list) ? null : list.get(0));
	}

	@ApiOperation(value = "申请提现")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "method", value = "出款方式>>1账户资金>>2佣金资金", required=true),
		@ApiImplicitParam(name = "type", value = "提现方式>>1银行>>2支付宝>>3微信", required=true),
		@ApiImplicitParam(name = "amount", value = "提现金额", required=true),
	})
	@ResponseBody
	@RequestMapping(value="/withdraw/apply", method=RequestMethod.POST)
    public CommonResult<String> apply(BigDecimal amount, Integer type, Integer method, BankForm bankForm) throws Exception{
		UmsMember member = memberService.getMemberById(getCurrentMemberId());
		super.validateRepeat("do-withdraw-time-between-" + member.getId(), 5000L, "请勿重复提现,5秒后再试!");

		withdrawService.withdraw(member, type, method, amount, bankForm);
		return CommonResult.success(null);
    }
	
}