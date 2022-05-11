package com.chengyu.core.controller.member;

import cn.hutool.crypto.SecureUtil;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberAccount;
import com.chengyu.core.service.config.ConfigGzhService;
import com.chengyu.core.service.member.MemberAccountService;
import com.chengyu.core.service.system.QiniuService;
import com.chengyu.core.service.system.VerifyCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @title  个人中心
 * @author LeGreen
 * @date   2021/1/8
 */
@Api(tags = "个人中心")
@Controller
@RequestMapping("/member")
public class MemberController extends ShopBaseController {
	
	@Autowired
	private QiniuService qiniuService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private MemberAccountService accountService;
	@Autowired
	private VerifyCodeService verifyCodeService;
	@Autowired
	private ConfigGzhService configGzhService;

	@ApiOperation(value = "获取当前登录用户")
	@ResponseBody
	@RequestMapping(value={"/getUser"}, method=RequestMethod.GET)
	public CommonResult<UmsMember> getUser() {
		return CommonResult.success(memberService.getCurrentMemberOrNull());
	}

	@ApiOperation(value = "获取用户信息")
	@ResponseBody
	@RequestMapping(value={"/getUserById"}, method=RequestMethod.GET)
	public CommonResult<UmsMember> getUserById(Integer memberId) {
		return CommonResult.success(memberService.getMemberById(memberId));
	}

	@ApiOperation(value = "获取用户账户")
	@ResponseBody
	@RequestMapping(value={"/getAccount"}, method=RequestMethod.GET)
	public CommonResult<UmsMemberAccount> getAccount() throws ServiceException {
		return CommonResult.success(accountService.getMemberAccount(getCurrentMemberId()));
	}

	@OperationLog
	@ApiOperation(value = "修改登录密码")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "oldPassword", value = "旧密码"),
		@ApiImplicitParam(name = "password", value = "密码"),
		@ApiImplicitParam(name = "comfirmPassword", value = "确认密码"),
		@ApiImplicitParam(name = "cToken", value = "图形验证码TOKEN"),
		@ApiImplicitParam(name = "captcha", value = "图形验证码")
	})
	@ResponseBody
	@RequestMapping(value={"/updatePassword"}, method=RequestMethod.POST)
	public CommonResult<String> updatePassword(String oldPassword, String password, String comfirmPassword, String cToken, String captcha) throws Exception {
		//校验图形验证码
		super.validateCaptcha(cToken, captcha);

		if(!password.equals(comfirmPassword)) {
			return CommonResult.failed("两次密码输入不一致");
		}
		UmsMember member = getCurrentMember();
		UserDetails userDetails = memberService.loadUserByUsername(member.getCode());
		if(!passwordEncoder.matches(oldPassword, userDetails.getPassword())){
			return CommonResult.failed("旧密码不正确");
		}

		UmsMember updateMember = new UmsMember();
		updateMember.setId(member.getId());
		updateMember.setPassword(password);
		memberService.updateMember(updateMember);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "设置登录密码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "password", value = "密码"),
			@ApiImplicitParam(name = "comfirmPassword", value = "确认密码"),
	})
	@ResponseBody
	@RequestMapping(value={"/setPassword"}, method=RequestMethod.POST)
	public CommonResult<Map<String, Object>> setPassword(String password, String comfirmPassword) throws Exception {
		if(!password.equals(comfirmPassword)) {
			return CommonResult.failed("两次密码输入不一致");
		}
		UmsMember member = getCurrentMember();

		UmsMember updateMember = new UmsMember();
		updateMember.setId(member.getId());
		updateMember.setPassword(password);
		memberService.updateMember(updateMember);

		String token = memberService.loginByNoPassword(member.getCode());
		Map<String, Object> tokenMap = new HashMap<>(16);
		tokenMap.put("token", token);
		tokenMap.put("tokenHead", tokenHead);
		UmsMember currentMember = memberService.getCurrentMember();
		tokenMap.put("member", currentMember);
		tokenMap.put("isNeedPassword", "1");
		return CommonResult.success(tokenMap);
	}

	@OperationLog
	@ApiOperation(value = "修改二级密码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "password", value = "密码"),
			@ApiImplicitParam(name = "comfirmPassword", value = "确认密码"),
			@ApiImplicitParam(name = "cToken", value = "图形验证码TOKEN"),
			@ApiImplicitParam(name = "captcha", value = "图形验证码"),
			@ApiImplicitParam(name = "code", value = "手机验证码")
	})
	@ResponseBody
	@RequestMapping(value={"/updatePayPassword"}, method=RequestMethod.POST)
	public CommonResult<String> updatePayPassword(String password, String comfirmPassword, String cToken, String captcha, String code) throws Exception {
		//校验图形验证码
		super.validateCaptcha(cToken, captcha);

		if(!password.equals(comfirmPassword)) {
			return CommonResult.failed("两次密码输入不一致");
		}

		//校验短信验证码
		UmsMember member = getCurrentMember();
		verifyCodeService.validateCode(member.getPhone(), code);

		UmsMember updateMember = new UmsMember();
		updateMember.setId(member.getId());
		updateMember.setPayPassword(password);
		memberService.updateMember(updateMember);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "修改设置密保问题")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "payPassword", value = "二级密码"),
			@ApiImplicitParam(name = "secretQuestion", value = "密保问题"),
			@ApiImplicitParam(name = "secretAnswer", value = "密保答案"),
			@ApiImplicitParam(name = "cToken", value = "图形验证码TOKEN"),
			@ApiImplicitParam(name = "captcha", value = "图形验证码")
	})
	@ResponseBody
	@RequestMapping(value={"/updateSecret"}, method=RequestMethod.POST)
	public CommonResult<String> updateSecret(String payPassword, String secretQuestion, String secretAnswer, String cToken, String captcha) throws Exception {
		//校验图形验证码
		super.validateCaptcha(cToken, captcha);

		//校验支付密码
		UmsMember member = getCurrentMember();
		if(!SecureUtil.md5(payPassword).equals(member.getPayPassword())){
			return CommonResult.failed("二级密码不正确");
		}

		UmsMember updateMember = new UmsMember();
		updateMember.setId(member.getId());
		updateMember.setSecret(secretQuestion);
		updateMember.setSecretAnswer(secretAnswer);
		memberService.updateMember(updateMember);
		return CommonResult.success(null);
	}
	
	@ApiOperation(value = "校验当前密码")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "password", value = "密码"),
	})
	@ResponseBody
	@RequestMapping(value={"/validatePassword"}, method=RequestMethod.POST)
	public CommonResult<String> validatePassword(String password) throws ServiceException {
		UmsMember member = getCurrentMember();
		UserDetails userDetails = memberService.loadUserByUsername(member.getCode());
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
        	return CommonResult.failed("密码不正确");
        }
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "注册极光registrationId")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "registrationId", value = "极光注册ID")
	})
	@RequestMapping(value={"/updateRegistrationId"}, method=RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> updateRegistrationId(String registrationId) throws ServiceException{
		UmsMember updateMember = new UmsMember();
		updateMember.setId(getCurrentMemberId());
		updateMember.setRegistrationId(registrationId);
		memberService.updateMember(updateMember);
        return CommonResult.success(null);
		
	 }

	@OperationLog
	@ApiOperation(value = "更新个人资料")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "gender", value = "性别>>1男>>2女"),
			@ApiImplicitParam(name = "remindVoice", value = "声音提醒>>1开>>0关"),
			@ApiImplicitParam(name = "birthday", value = "生日"),
			@ApiImplicitParam(name = "qq", value = "qq"),
			@ApiImplicitParam(name = "email", value = "邮箱"),
			@ApiImplicitParam(name = "province", value = "省份"),
			@ApiImplicitParam(name = "city", value = "市"),
			@ApiImplicitParam(name = "area", value = "区县"),
			@ApiImplicitParam(name = "provinceCode", value = "省份代码"),
			@ApiImplicitParam(name = "cityCode", value = "市代码"),
			@ApiImplicitParam(name = "areaCode", value = "区县代码"),
			@ApiImplicitParam(name = "address", value = "具体地址"),
			@ApiImplicitParam(name = "personSign", value = "个性签名")
	})
	@ResponseBody
	@RequestMapping(value={"/updateInfo"}, method=RequestMethod.POST)
	public CommonResult<String> updateInfo(
			String gender, Date birthday, String qq, String email,
			String province, String city, String area, String provinceCode, String cityCode, String areaCode,
			String address, String personSign, Integer remindVoice) throws Exception {
		UmsMember updateMember = new UmsMember();
		updateMember.setId(getCurrentMemberId());
		updateMember.setGender(gender);
		updateMember.setBirthday(birthday);
		updateMember.setQq(qq);
		updateMember.setEmail(email);
		updateMember.setProvince(province);
		updateMember.setProvinceCode(provinceCode);
		updateMember.setCity(city);
		updateMember.setCityCode(cityCode);
		updateMember.setArea(area);
		updateMember.setAreaCode(areaCode);
		updateMember.setAddress(address);
		updateMember.setPersonSign(personSign);
		updateMember.setRemindVoice(remindVoice);
		memberService.updateMember(updateMember);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "修改手机号码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phone", value = "新手机号码"),
			@ApiImplicitParam(name = "code", value = "验证码"),
	})
	@ResponseBody
	@RequestMapping(value={"/updatePhone"}, method=RequestMethod.POST)
	public CommonResult<String> updatePhone(
			String phone, String code) throws Exception {
		//校验短信验证码
		UmsMember member = getCurrentMember();
		verifyCodeService.validateCode(phone, code);

		UmsMember updateMember = new UmsMember();
		updateMember.setId(member.getId());
		updateMember.setPhone(phone);
		memberService.updateMember(updateMember);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "上传头像")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "file", value = "图片文件"),
	})
	@RequestMapping(value={"/updateHeadImg"}, method=RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> upload(MultipartFile file) throws Exception {
		byte[] bs = file.getBytes();
		String imgPath = null;
		if (bs.length > 0) {
		    // 将字符串转换成二进制，用于显示图片
		    // 将上面生成的图片格式字符串 imgStr，还原成图片显示
		    long currentTime = System.currentTimeMillis();
		    String filename = currentTime + ".jpeg";
		    imgPath = qiniuService.uploadByImg(bs, filename);
		}
		
		//更新头像
		UmsMember updateMember = new UmsMember();
		updateMember.setId(getCurrentMemberId());
		updateMember.setHeadImg(imgPath);
		memberService.updateMember(updateMember);
		return CommonResult.success(imgPath);
	}

}