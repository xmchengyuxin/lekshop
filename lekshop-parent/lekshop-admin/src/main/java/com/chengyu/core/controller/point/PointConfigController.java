package com.chengyu.core.controller.point;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.PointConfig;
import com.chengyu.core.service.point.PointConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "积分配置")
@Controller
@RequestMapping("/system/point")
public class PointConfigController extends AdminBaseController {
	
	@Autowired
	private PointConfigService pointConfigService;
	
	@ApiOperation(value = "获取积分配置")
	@ResponseBody
	@RequestMapping(value="/config/get", method=RequestMethod.GET)
	public CommonResult<PointConfig> toIndex() {
		PointConfig config = pointConfigService.getPointConfigNoCache();
		return CommonResult.success(config);
	}
	
	@OperationLog
	@ApiOperation(value = "修改积分配置")
	@ResponseBody
	@RequestMapping(value="/config/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(PointConfig config) {
		pointConfigService.addPointConfig(config);
		return CommonResult.success(null);
	}
	
}