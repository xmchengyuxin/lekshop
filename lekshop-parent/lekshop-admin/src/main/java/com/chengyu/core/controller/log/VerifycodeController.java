package com.chengyu.core.controller.log;

import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysVerifycode;
import com.chengyu.core.service.system.VerifyCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "VerifycodeController", description = "验证码管理")
@Controller
@RequestMapping("/system")
public class VerifycodeController extends AdminBaseController {
	
	@Autowired
	private VerifyCodeService verifyCodeService;
	
	@ApiOperation(value = "验证码列表")
	@ResponseBody
	@RequestMapping(value="/verifyCode/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<SysVerifycode>> getList(
			String type, String name, String sendStatus, 
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		List<SysVerifycode> list = verifyCodeService.getVerifyCodeList(type, name, sendStatus, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
}