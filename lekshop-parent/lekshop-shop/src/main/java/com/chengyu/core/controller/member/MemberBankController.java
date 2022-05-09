package com.chengyu.core.controller.member;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberBank;
import com.chengyu.core.service.member.MemberBankService;
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
	@Autowired
	private VerifyCodeService verifyCodeService;
	
	@ApiOperation(value = "获取银行卡认证")
	@ResponseBody
	@RequestMapping(value={"/getBank"}, method=RequestMethod.GET)
	public CommonResult<UmsMemberBank> getBank() throws ServiceException {
		UmsMemberBank bank = memberBankService.getMemberBankByMemberId(getCurrentMemberId());
		return CommonResult.success(bank);
	}

	@ApiOperation(value = "获取他人银行卡信息")
	@ResponseBody
	@RequestMapping(value={"/getBankByMemberId"}, method=RequestMethod.GET)
	public CommonResult<UmsMemberBank> getBankByMemberId(Integer memberId) {
		UmsMemberBank bank = memberBankService.getMemberBankByMemberId(memberId);
		return CommonResult.success(bank);
	}

	@OperationLog
	@ApiOperation(value = "银行卡认证")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "realname", value = "真实姓名"),
		@ApiImplicitParam(name = "idCard", value = "身份证"),
		@ApiImplicitParam(name = "bank", value = "银行"),
		@ApiImplicitParam(name = "bankSub", value = "支行"),
		@ApiImplicitParam(name = "bankAccount", value = "银行账号"),
		@ApiImplicitParam(name = "zfbAccount", value = "支付宝收款账号"),
		@ApiImplicitParam(name = "zfbErweima", value = "支付宝收款二维码"),
		@ApiImplicitParam(name = "wxAccount", value = "微信收款账号"),
		@ApiImplicitParam(name = "wxErweima", value = "微信收款二维码"),
		@ApiImplicitParam(name = "province", value = "省份"),
		@ApiImplicitParam(name = "city", value = "市"),
		@ApiImplicitParam(name = "area", value = "区县"),
		@ApiImplicitParam(name = "provinceCode", value = "省份代码"),
		@ApiImplicitParam(name = "cityCode", value = "市代码"),
		@ApiImplicitParam(name = "areaCode", value = "区县代码"),
		@ApiImplicitParam(name = "code", value = "手机验证码"),
	})
	@ResponseBody
	@RequestMapping(value={"/applyBank"}, method=RequestMethod.POST)
	public CommonResult<UmsMemberBank> applyBank(
			String realname, String idCard,
			String bank, String bankSub, String bankAccount,
			String zfbAccount, String wxAccount, String zfbErweima, String wxErweima,
			String province, String city, String area, String provinceCode, String cityCode, String areaCode,
			String code) throws Exception {
		//校验短信验证码
		UmsMember member = getCurrentMember();
		if(StringUtils.isNotBlank(code)){
			verifyCodeService.validateCode(member.getPhone(), code);
		}

		UmsMemberBank memberBank = new UmsMemberBank();
		memberBank.setAccountName(realname);
		memberBank.setIdCard(idCard);
		memberBank.setBank(bank);
		memberBank.setBankSub(bankSub);
		memberBank.setBankAccount(bankAccount);
		memberBank.setZfbAccount(zfbAccount);
		memberBank.setZfbErweima(zfbErweima);
		memberBank.setWxAccount(wxAccount);
		memberBank.setWxErweima(wxErweima);
		memberBank.setProvince(province);
		memberBank.setProvinceCode(provinceCode);
		memberBank.setCity(city);
		memberBank.setCityCode(cityCode);
		memberBank.setArea(area);
		memberBank.setAreaCode(areaCode);
		UmsMemberBank mbank = memberBankService.applyBank(member, memberBank);
		return CommonResult.success(mbank);
	}

}