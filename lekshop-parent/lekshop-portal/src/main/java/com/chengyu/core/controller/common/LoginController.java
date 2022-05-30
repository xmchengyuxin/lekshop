package com.chengyu.core.controller.common;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.security.util.JwtTokenUtil;
import com.chengyu.core.service.member.MemberImeIdService;
import com.chengyu.core.service.system.VerifyCodeService;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @title  登录
 * @author LeGreen
 * @date   2021/1/8
 */
@Api(tags = "登录")
@Controller
@RequestMapping("/common")
public class LoginController extends UserBaseController {
	
	@Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
	private VerifyCodeService verifyCodeService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private MemberImeIdService memberImeIdService;

	@ApiOperation(value = "密码登录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "账号"),
		@ApiImplicitParam(name = "password", value = "密码"),
		@ApiImplicitParam(name = "type", value = "1买号登录>>2卖家登录"),
		@ApiImplicitParam(name = "registrationId", value = "极光注册ID"),
		@ApiImplicitParam(name = "imeId", value = "设备ID"),
		@ApiImplicitParam(name = "phoneType", value = "设备型号"),
		@ApiImplicitParam(name = "cToken", value = "图形验证码TOKEN"),
		@ApiImplicitParam(name = "captcha", value = "图形验证码")
	})
	@RequestMapping(value={"/login"}, method=RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, Object>> login(String username, String password, String imeId, String phoneType, Integer type, String registrationId, String cToken, String captcha) throws ServiceException{
		//校验图形验证码
		super.validateCaptcha(cToken, captcha);

		//进行登录
		String token = memberService.login(username, password, this.getRequestIp());
        if (token == null) {
        	return CommonResult.validateFailed("用户名或密码错误");
        }
        
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        UmsMember member = memberService.getCurrentMember();
		memberImeIdService.loginByImeId(member, imeId, phoneType);
        tokenMap.put("member", member);

        if(StringUtils.isNotBlank(registrationId)){
			UmsMember updateMember = new UmsMember();
			updateMember.setId(member.getId());
			updateMember.setRegistrationId(registrationId);
			memberService.updateMember(updateMember);
		}
        return CommonResult.success(tokenMap);
		
	 }

	@ApiOperation(value = "根据用户名获取登录token")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "用户名"),
	})
	@RequestMapping(value={"/loginByUsername"}, method=RequestMethod.POST)
	@ResponseBody
	public CommonResult<Map<String, Object>> loginByUsername(String username) throws ServiceException{
		String token = memberService.loginByNoPassword(username);
		//进行登录
		if (token == null) {
			return CommonResult.validateFailed("用户名或密码错误");
		}

		Map<String, Object> tokenMap = new HashMap<>(16);
		tokenMap.put("token", token);
		tokenMap.put("tokenHead", tokenHead);
		tokenMap.put("member", getCurrentMember());
		return CommonResult.success(tokenMap);

	}
	
	@ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = jwtTokenUtil.refreshHeadToken(token);
        if (refreshToken == null) {
            return CommonResult.unauthorized("token已经过期！");
        }
        return CommonResult.success(refreshToken);
    }
	
	@ApiOperation(value = "忘记密码校验,校验通过后默认登录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "phone", value = "手机"),
		@ApiImplicitParam(name = "code", value = "验证码"),
	})
	@ResponseBody
	@RequestMapping(value={"/forgetPassword"}, method=RequestMethod.POST)
	public CommonResult<Map<String, Object>> forgetPassword(String phone, String code) throws Exception {
		verifyCodeService.validateCode(phone, code);

		String token = memberService.loginByNoPassword(phone);
		Map<String, Object> tokenMap = new HashMap<>(16);
		tokenMap.put("token", token);
		tokenMap.put("tokenHead", tokenHead);
		return CommonResult.success(tokenMap);
	}

}