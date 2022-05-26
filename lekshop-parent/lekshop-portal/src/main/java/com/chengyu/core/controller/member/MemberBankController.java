package com.chengyu.core.controller.member;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.form.BaseSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberBank;
import com.chengyu.core.service.member.MemberBankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  银行卡认证
 * @author LeGreen
 * @date   2021/1/8
 */
@Api(tags = "银行卡认证")
@Controller
@RequestMapping("/member")
public class MemberBankController extends UserBaseController {
	
	@Autowired
	private MemberBankService memberBankService;

	@ApiOperation(value = "获取银行卡列表")
	@ResponseBody
	@RequestMapping(value={"/bank/getList"}, method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberBank>> getList(Integer page, Integer pageSize) throws ServiceException {
		BaseSearchForm form = new BaseSearchForm();
		form.setMemberId(getCurrentMemberId());
		List<UmsMemberBank> bankList = memberBankService.getMemberBankList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(bankList));
	}


	@OperationLog
	@ApiOperation(value = "绑定银行卡")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "ID(修改时必传)"),
		@ApiImplicitParam(name = "accountName", value = "真实姓名"),
		@ApiImplicitParam(name = "bank", value = "银行"),
		@ApiImplicitParam(name = "bankAccount", value = "银行账号"),
		@ApiImplicitParam(name = "zfbAccount", value = "支付宝收款账号"),
		@ApiImplicitParam(name = "wxErweima", value = "微信收款二维码"),
	})
	@ResponseBody
	@RequestMapping(value={"/bank/bind"}, method=RequestMethod.POST)
	public CommonResult<String> applyBank(
			Integer id,
			String accountName,
			String bank, String bankAccount,
			String zfbAccount, String wxErweima) throws Exception {
		//校验短信验证码
		UmsMember member = getCurrentMember();
		UmsMemberBank memberBank = new UmsMemberBank();
		memberBank.setMemberId(member.getId());
		memberBank.setMemberName(member.getCode());
		memberBank.setId(id);
		memberBank.setAccountName(accountName);
		memberBank.setBank(bank);
		memberBank.setBankAccount(bankAccount);
		memberBank.setZfbAccount(zfbAccount);
		memberBank.setWxErweima(wxErweima);
		memberBankService.update(memberBank);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除银行卡")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID"),
	})
	@ResponseBody
	@RequestMapping(value={"/bank/delete"}, method=RequestMethod.POST)
	public CommonResult<String> delete(Integer id) {
		memberBankService.deleteMemberBank(id);
		return CommonResult.success(null);
	}

}