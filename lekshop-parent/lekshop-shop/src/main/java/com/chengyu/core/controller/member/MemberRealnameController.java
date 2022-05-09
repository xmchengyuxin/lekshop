package com.chengyu.core.controller.member;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberRealname;
import com.chengyu.core.service.member.MemberRealnameService;
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
 * @title  实名认证
 * @author LeGreen
 * @date   2021/1/8
 */
@Api(tags = "实名认证")
@Controller
@RequestMapping("/member")
public class MemberRealnameController extends UserBaseController {
	
	@Autowired
	private MemberRealnameService memberRealnameService;
	@Autowired
	private VerifyCodeService verifyCodeService;
	
	@ApiOperation(value = "获取实名认证信息")
	@ResponseBody
	@RequestMapping(value={"/getRealname"}, method=RequestMethod.GET)
	public CommonResult<UmsMemberRealname> getRealname() throws ServiceException {
		UmsMemberRealname realname = memberRealnameService.getMemberRealnameByMemberId(getCurrentMemberId());
		return CommonResult.success(realname);
	}

	@OperationLog
	@ApiOperation(value = "实名认证")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "realname", value = "真实姓名"),
		@ApiImplicitParam(name = "idCard", value = "身份证"),
		@ApiImplicitParam(name = "idcardFace", value = "身份证正面"),
		@ApiImplicitParam(name = "idcardBack", value = "身份证反面"),
		@ApiImplicitParam(name = "idcardFaceHand", value = "手持身份证")
	})
	@ResponseBody
	@RequestMapping(value={"/applyRealname"}, method=RequestMethod.POST)
	public CommonResult<UmsMemberRealname> applyRealname(String realname, String idCard, String idcardFace, String idcardBack, String idcardFaceHand, String code) throws Exception {
		//校验短信验证码
		UmsMember member = getCurrentMember();
		if(StringUtils.isNotBlank(code)){
			verifyCodeService.validateCode(member.getPhone(), code);
		}
		UmsMemberRealname memberRealname = memberRealnameService.applyRealname(member, realname, idCard, idcardFace, idcardBack, idcardFaceHand);
		return CommonResult.success(memberRealname);
	}

}