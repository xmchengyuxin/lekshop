package com.chengyu.core.controller.third;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysInterfaceConfig;
import com.chengyu.core.service.system.InterfaceConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @title  第三方接口配置
 * @author LeGreen
 * @date   2022/6/6
 */
@Api(tags = "第三方接口配置")
@Controller
@RequestMapping("/system")
public class InterfaceConfigController extends AdminBaseController {
	
	@Autowired
	private InterfaceConfigService interfaceConfigService;
	
	@ApiOperation(value = "获取配置")
	@ResponseBody
	@RequestMapping(value="/interface/get", method=RequestMethod.GET)
	public CommonResult<SysInterfaceConfig> toIndex(String nid) {
		SysInterfaceConfig config = interfaceConfigService.getConfigNoCache(nid);
		return CommonResult.success(config);
	}
	
	@OperationLog
	@ApiOperation(value = "修改配置")
	@ResponseBody
	@RequestMapping(value="/interface/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(SysInterfaceConfig config) throws Exception {
		interfaceConfigService.updateConfig(config);
		return CommonResult.success(null);
	}
	
}