package com.chengyu.core.controller.third;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysThirdWarn;
import com.chengyu.core.service.system.ThirdWarnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title  第三方接口预警
 * @author LeGreen
 * @date   2021/1/29
 */
@Api(tags = "第三方接口预警")
@Controller
@RequestMapping("/system")
public class ThirdWarnController extends AdminBaseController {
	
	@Autowired
	private ThirdWarnService thirdWarnService;
	
	@ApiOperation(value = "获取接口预警配置")
	@ResponseBody
	@RequestMapping(value="/thirdWarn/getList", method=RequestMethod.GET)
	public CommonResult<List<SysThirdWarn>> toIndex() throws Exception {
		List<SysThirdWarn> list = thirdWarnService.getAllConfig();
		if(CollectionUtil.isNotEmpty(list)){
			for(SysThirdWarn warn : list){
				warn.setLeftAmount(thirdWarnService.getLeftAmount(warn.getNid(), warn.getInterfaceNid()));
			}
		}
		return CommonResult.success(list);
	}

	@OperationLog
	@ApiOperation(value = "更新配置")
	@ResponseBody
	@RequestMapping(value="/thirdWarn/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(SysThirdWarn config) {
		thirdWarnService.updateConfig(config);
		return CommonResult.success(null);
	}
	
}