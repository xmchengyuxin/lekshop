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
	@Autowired
	private VerifyCodeService verifyCodeService;
	
	@ApiOperation(value = "获取银行卡认证")
	@ResponseBody
	@RequestMapping(value={"/bank/getList"}, method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberBank>> getList(Integer page, Integer pageSize) throws ServiceException {
		BaseSearchForm form = new BaseSearchForm();
		form.setMemberId(getCurrentMemberId());
		List<UmsMemberBank> bankList = memberBankService.getMemberBankList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(bankList));
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