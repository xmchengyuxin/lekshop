package com.chengyu.core.controller.config;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.ConfigMission;
import com.chengyu.core.service.config.ConfigMissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  佣金组别列表
 * @author LeGreen
 * @date   2021/1/15
 */
@Api(tags = "佣金组别列表")
@Controller
@RequestMapping("/system")
public class ConfigMissionController extends AdminBaseController {
	
	@Autowired
	private ConfigMissionService configMissionService;
	
	@ApiOperation(value = "佣金组别列表")
	@ResponseBody
	@RequestMapping(value="/configMission/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<ConfigMission>> getList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
		
		List<ConfigMission> list = configMissionService.getConfigMissionList(page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "佣金组别选择器")
	@ResponseBody
	@RequestMapping(value="/configMission/getSelector", method=RequestMethod.GET)
	public CommonResult<List<ConfigMission>> getSelector() {
		return CommonResult.success(configMissionService.getAllConfigMission());
	}
	
	@OperationLog
	@ApiOperation(value = "添加编辑佣金组别")
	@ResponseBody
	@RequestMapping(value="/configMission/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(ConfigMission config) {
		configMissionService.addConfigMission(config);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "修改佣金组别参数")
	@ResponseBody
	@RequestMapping(value="/configMission/updateParams", method=RequestMethod.POST)
	public CommonResult<String> updateParams(ConfigMission config) {
		configMissionService.updateParams(config);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除佣金组别")
	@ResponseBody
	@RequestMapping(value="/configMission/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			configMissionService.deleteConfigMission(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}