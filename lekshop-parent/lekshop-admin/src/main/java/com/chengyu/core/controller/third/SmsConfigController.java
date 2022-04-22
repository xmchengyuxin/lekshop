package com.chengyu.core.controller.third;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysAlismsConfig;
import com.chengyu.core.service.system.AlismsConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @类描述   	短信配置
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
@Api(tags = "SmsConfigController", description = "短信配置")
@Controller
@RequestMapping("/system")
public class SmsConfigController extends AdminBaseController {
	
	@Autowired
	private AlismsConfigService smsService;
	
	@ApiOperation(value = "获取短信配置")
	@ResponseBody
	@RequestMapping(value="/sms/get", method=RequestMethod.GET)
	public CommonResult<SysAlismsConfig> toIndex() throws Exception {
		SysAlismsConfig config = smsService.getAlismsConfig();
		return CommonResult.success(config);
	}
	
	@OperationLog
	@ApiOperation(value = "修改短信配置")
	@ResponseBody
	@RequestMapping(value="/sms/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(SysAlismsConfig config) throws Exception {
		if(config.getId() != null){
			smsService.updateAlismsConfig(config);
		}else{
			smsService.addAlismsConfig(config);
		}
		return CommonResult.success(null);
	}
	
}