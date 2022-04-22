package com.chengyu.core.controller.member;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMemberBank;
import com.chengyu.core.service.member.MemberSignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @title  签到
 * @author LeGreen
 * @date   2021/4/9
 */
@Api(tags = "签到")
@Controller
@RequestMapping("/member")
public class MemberSignController extends UserBaseController {
	
	@Autowired
	private MemberSignService memberSignService;

	@OperationLog
	@ApiOperation(value = "签到")
	@ResponseBody
	@RequestMapping(value={"/sign"}, method=RequestMethod.POST)
	public CommonResult<UmsMemberBank> sign() throws ServiceException {
		memberSignService.addSign(getCurrentMember());
		return CommonResult.success(null);
	}

}