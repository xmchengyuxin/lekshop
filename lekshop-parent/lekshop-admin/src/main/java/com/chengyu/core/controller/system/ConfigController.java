package com.chengyu.core.controller.system;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.chengyu.core.component.DemoStationForbid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.form.ConfigForm;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysConfig;
import com.chengyu.core.service.system.ConfigService;
import com.chengyu.core.utils.StringUtils;

import cn.hutool.core.util.ReflectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(tags = "ConfigController", description = "系统配置管理")
@RequestMapping("/system")
public class ConfigController extends AdminBaseController {
	
	@Autowired
	private ConfigService configService;
	
	@ResponseBody
	@ApiOperation("系统参数列表")
	@RequestMapping(value="/config/getList", method=RequestMethod.GET)
	public CommonResult<Map<String,String>> getList() throws Exception {
		Map<String,String> result = configService.getAllConfigValueMapByNoCache();
		return CommonResult.success(result);
	}
	
	@ResponseBody
	@ApiOperation("根据nid系统参数")
	@RequestMapping(value="/config/getByNid", method=RequestMethod.GET)
	public CommonResult<String> getByNid(String nid) throws Exception {
		String value = configService.getValueByNid(nid);
		return CommonResult.success(value);
	}

	@DemoStationForbid
	@OperationLog
	@ResponseBody
	@ApiOperation("修改系统参数")
	@RequestMapping(value="/config/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(ConfigForm configForm) throws Exception {
		List<SysConfig> configList = configService.getAllConfigsByNoCache();
		if(configList != null && !configList.isEmpty()){
			for(SysConfig config : configList){
				String value = (String) ReflectUtil.getFieldValue(configForm, config.getNid());
				if(StringUtils.isNotBlank(value)){
					config.setValue(value);
				}
			}
		}
		configService.updateConfig(configList);
		return CommonResult.success(null);
	}
	
	public static void main(String[] args) {
		Field[] a = ReflectUtil.getFields(ConfigForm.class);
		for(Field e : a){
			System.out.println(e.getName());
		}
	}
}