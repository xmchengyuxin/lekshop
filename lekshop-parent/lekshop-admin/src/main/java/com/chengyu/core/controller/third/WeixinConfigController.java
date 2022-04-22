package com.chengyu.core.controller.third;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysWeixinConfig;
import com.chengyu.core.model.SysWeixinTemplate;
import com.chengyu.core.service.system.WeixinConfigService;
import com.chengyu.core.service.system.WeixinTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @类描述   	微信配置
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
@Api(tags = "WeixinConfigController", description = "微信配置")
@Controller
@RequestMapping("/system")
public class WeixinConfigController extends AdminBaseController {
	
	@Autowired
	private WeixinConfigService weixinService;
	@Autowired
	private WeixinTemplateService templateService;
	
	@ApiOperation(value = "获取微信配置")
	@ResponseBody
	@RequestMapping(value="/weixin/get", method=RequestMethod.GET)
	public CommonResult<SysWeixinConfig> toIndex() throws Exception {
		SysWeixinConfig config = weixinService.getWeixinConfig();
		return CommonResult.success(config);
	}
	
	@OperationLog
	@ApiOperation(value = "修改微信配置")
	@ResponseBody
	@RequestMapping(value="/weixin/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(SysWeixinConfig config) throws Exception {
		if(config.getId() == null){
			weixinService.addWeixinConfig(config);
		}else{
			weixinService.updateWeixinConfig(config);
		}
		return CommonResult.success(null);
	}

	@ApiOperation(value = "获取微信模板消息")
	@ResponseBody
	@RequestMapping(value="/weixinTemplate/getList", method=RequestMethod.GET)
	public CommonResult<List<SysWeixinTemplate>> getList() throws Exception {
		List<SysWeixinTemplate> list = templateService.getWeixinTemplateList();
		return CommonResult.success(list);
	}

	@OperationLog
	@ApiOperation(value = "修改微信模板消息")
	@ResponseBody
	@RequestMapping(value="/weixinTemplate/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmitWeixinTemplate(SysWeixinTemplate config) throws Exception {
		templateService.updateWeixinTemplate(config);
		return CommonResult.success(null);
	}
}