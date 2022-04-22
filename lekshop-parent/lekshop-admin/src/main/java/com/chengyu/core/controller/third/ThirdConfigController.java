package com.chengyu.core.controller.third;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysThirdConfig;
import com.chengyu.core.service.system.ThirdConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  第三方接口切换
 * @author LeGreen
 * @date   2021/1/29
 */
@Api(tags = "第三方接口切换")
@Controller
@RequestMapping("/system")
public class ThirdConfigController extends AdminBaseController {
	
	@Autowired
	private ThirdConfigService thirdConfigService;
	
	@ApiOperation(value = "获取接口切换配置")
	@ResponseBody
	@RequestMapping(value="/thirdConfig/getList", method=RequestMethod.GET)
	public CommonResult<List<SysThirdConfig>> toIndex() {
		List<SysThirdConfig> list = thirdConfigService.getAllConfig();
		return CommonResult.success(list);
	}
	
	@OperationLog
	@ApiOperation(value = "更新配置")
	@ResponseBody
	@RequestMapping(value="/thirdConfig/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(SysThirdConfig config) {
		thirdConfigService.updateConfig(config);
		return CommonResult.success(null);
	}
	
}