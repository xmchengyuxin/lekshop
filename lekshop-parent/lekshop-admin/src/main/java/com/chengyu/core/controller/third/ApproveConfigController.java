package com.chengyu.core.controller.third;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysApproveConfig;
import com.chengyu.core.service.system.ApproveConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @title  认证接口配置
 * @author LeGreen
 * @date   2021/1/29
 */
@Api(tags = "认证接口配置")
@Controller
@RequestMapping("/system")
public class ApproveConfigController extends AdminBaseController {
	
	@Autowired
	private ApproveConfigService approveConfigService;
	
	@ApiOperation(value = "获取认证接口")
	@ResponseBody
	@RequestMapping(value="/approveConfig/get", method=RequestMethod.GET)
	public CommonResult<SysApproveConfig> toIndex() {
		SysApproveConfig config = approveConfigService.getApproveConfig();
		return CommonResult.success(config);
	}
	
	@OperationLog
	@ApiOperation(value = "修改认证接口")
	@ResponseBody
	@RequestMapping(value="/approveConfig/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(SysApproveConfig config) {
		if(config.getId() != null){
			approveConfigService.updateApproveConfig(config);
		}else{
			approveConfigService.addApproveConfig(config);
		}
		return CommonResult.success(null);
	}
	
}