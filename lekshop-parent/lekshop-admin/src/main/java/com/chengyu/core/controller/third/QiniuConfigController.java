package com.chengyu.core.controller.third;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysQiniuConfig;
import com.chengyu.core.service.system.QiniuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @类描述   	七牛配置
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
@Api(tags = "QiniuConfigController", description = "七牛配置")
@Controller
@RequestMapping("/system")
public class QiniuConfigController extends AdminBaseController {
	
	@Autowired
	private QiniuService qiniuService;
	
	@ApiOperation(value = "获取七牛配置")
	@ResponseBody
	@RequestMapping(value="/qiniu/get", method=RequestMethod.GET)
	public CommonResult<SysQiniuConfig> toIndex() throws Exception {
		SysQiniuConfig config = qiniuService.getQiniuConfig();
		return CommonResult.success(config);
	}
	
	@OperationLog
	@ApiOperation(value = "修改七牛配置")
	@ResponseBody
	@RequestMapping(value="/qiniu/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(SysQiniuConfig config) throws Exception {
		qiniuService.updateQiniuConfig(config);
		return CommonResult.success(null);
	}
	
}