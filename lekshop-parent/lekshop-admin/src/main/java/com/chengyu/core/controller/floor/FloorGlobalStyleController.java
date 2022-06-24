package com.chengyu.core.controller.floor;

import cn.hutool.core.util.ReflectUtil;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.form.GlobalStyleForm;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.FloorGlobalStyle;
import com.chengyu.core.service.floor.FloorGlobalStyleService;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.util.Map;

@Api(tags = "全局样式")
@Controller
@RequestMapping("/system")
public class FloorGlobalStyleController extends AdminBaseController {
	
	@Autowired
	private FloorGlobalStyleService floorGlobalStyleService;
	
	@ApiOperation(value = "获取全局样式")
	@ResponseBody
	@RequestMapping(value="/globalStyle/get", method=RequestMethod.GET)
	public CommonResult<Map<String, String>> get() throws ServiceException {
		Map<String, String> result = floorGlobalStyleService.getGlobalStyleByNoCache();
		return CommonResult.success(result);
	}

	@ApiOperation(value = "获取默认样式")
	@ResponseBody
	@RequestMapping(value="/globalStyle/getDefault", method=RequestMethod.GET)
	public CommonResult<GlobalStyleForm> getDefault() {
		return CommonResult.success(new GlobalStyleForm());
	}

	@OperationLog
	@ApiOperation(value = "添加编辑全局样式")
	@ResponseBody
	@RequestMapping(value="/globalStyle/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(GlobalStyleForm form) throws ServiceException {
		Field[] fields = ReflectUtil.getFields(GlobalStyleForm.class);
		for(Field field : fields){
			FloorGlobalStyle style = new FloorGlobalStyle();
			style.setStyleKey(field.getName());
			String value = (String) ReflectUtil.getFieldValue(form, style.getStyleKey());
			if(StringUtils.isNotBlank(value)){
				style.setStyleValue(value);
				floorGlobalStyleService.addGlobalStyle(style);
			}
		}
		return CommonResult.success(null);
	}
}