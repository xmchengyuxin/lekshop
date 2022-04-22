package com.chengyu.core.controller.common;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.UmsMemberRegParam;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysInviteCode;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.service.system.SysInviteCodeService;
import com.chengyu.core.service.system.VerifyCodeService;
import com.chengyu.core.util.ip.IpSearch;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @title  注册管理
 * @author LeGreen
 * @date   2021/1/8
 */
@Api(tags = "注册管理")
@Controller
public class RegisterController extends UserBaseController {
	
	@Value("${jwt.tokenHead}")
    private String tokenHead;
	@Autowired
	private VerifyCodeService verifyCodeService;
	@Autowired
	private SysInviteCodeService sysInviteCodeService;

	@ApiOperation(value = "注册")
	@ResponseBody
	@RequestMapping(value={"/common/register"}, method=RequestMethod.POST)
	public CommonResult<Map<String, Object>> regSubmit(@Valid UmsMemberRegParam regParam) throws Exception {
		if(!regParam.getPassword().equals(regParam.getConfirmPassword())) {
			return CommonResult.failed("两次密码输入不一致");
		}
		
		//校验短信验证码
		verifyCodeService.validateCode(regParam.getPhone(), regParam.getCode());

		UmsMember member = new UmsMember();
		member.setType(regParam.getType());
		member.setCode(regParam.getUsername().trim());
		member.setNickname(regParam.getUsername().trim());
		member.setPhone(regParam.getPhone());
		member.setPassword(regParam.getPassword());
		member.setQq(regParam.getQq());
		if(StringUtils.isNotBlank(regParam.getInviteCode())){
			if(CommonConstant.FIRST_INVITE_CODE.equals(regParam.getInviteCode())){
				member.setTjrId(0);
			}else{
				SysInviteCode config = sysInviteCodeService.getConfigByInviteCode(regParam.getInviteCode());
				if(config == null){
					return CommonResult.failed("邀请码错误");
				}
				if(config.getType() == CommonConstant.INVITE_CODE_MEMBER){
					UmsMember tjr = memberService.getMemberById(config.getUserId());
					if(tjr.getInviteStatus() != CommonConstant.YES_INT){
						return CommonResult.failed("邀请码错误");
					}
					if(!tjr.getType().equals(member.getType())){
						return CommonResult.failed("买家和商家无法互相邀请");
					}
					member.setTjrId(tjr.getId());
					member.setTjrUid(tjr.getUid());
				}
			}
		}
		member.setRegisterIp(this.getRequestIp());
		member.setRegisterIpAddress(IpSearch.getInstance().getIpAddress(member.getRegisterIp()));
		member.setRegisterUrl(this.getRequest().getHeader("Origin"));
		if(StringUtils.isNotBlank(member.getRegisterUrl())){
			member.setRegisterUrl(member.getRegisterUrl()
					.replaceAll("http://", "")
					.replaceAll("https://", "")
					.replaceAll("/", ""));
		}
		memberService.register(member);
		
		String token = memberService.loginByNoPassword(regParam.getUsername());
		Map<String, Object> tokenMap = new HashMap<>(16);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
		UmsMember currentMember = memberService.getCurrentMember();
        tokenMap.put("member", currentMember);
        return CommonResult.success(tokenMap);
	}
	
}