package com.chengyu.core.controller.third;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysZfbConfig;
import com.chengyu.core.service.system.ZfbConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @类描述   	支付宝配置
 * @作者   		LeGreen
 * @创建时间  	2020年3月12日
 * @版本   		1.00
 * @修改记录
 * <pre>
 *     版本          修改人    		修改日期 			修改内容描述
 *     -----------------------------------------------------------
 *     1.00   	LeGreen    	2020年3月12日             
 *     -----------------------------------------------------------
 * </pre>
 */
@Api(tags = "ZfbConfigController", description = "支付宝配置")
@Controller
@RequestMapping("/system")
public class ZfbConfigController extends AdminBaseController {
	
	@Autowired
	private ZfbConfigService zfbService;
	
	@ApiOperation(value = "获取支付宝配置")
	@ResponseBody
	@RequestMapping(value="/zfb/get", method=RequestMethod.GET)
	public CommonResult<SysZfbConfig> toIndex() throws Exception {
		SysZfbConfig config = zfbService.getZfbConfig();
		return CommonResult.success(config);
	}
	
	@OperationLog
	@ApiOperation(value = "修改支付宝配置")
	@ResponseBody
	@RequestMapping(value="/zfb/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(SysZfbConfig config) throws Exception {
		if(config.getId() != null){
			zfbService.updateZfbConfig(config);
		}else{
			zfbService.addZfbConfig(config);
		}
		return CommonResult.success(null);
	}
	
}