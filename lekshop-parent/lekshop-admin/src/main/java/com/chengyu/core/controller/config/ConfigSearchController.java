package com.chengyu.core.controller.config;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.ConfigSearch;
import com.chengyu.core.service.config.ConfigSearchService;
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
 * @title  搜索设置
 * @author LeGreen
 * @date   2022/5/6
 */
@Api(tags = "搜索设置")
@Controller
@RequestMapping("/system")
public class ConfigSearchController extends AdminBaseController {
	
	@Autowired
	private ConfigSearchService configSearchService;
	
	@ApiOperation(value = "搜索设置列表")
	@ResponseBody
	@RequestMapping(value="/configSearch/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<ConfigSearch>> getList(
			Integer status, String keyword,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		
		List<ConfigSearch> list = configSearchService.getConfigSearchList(status, keyword, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "添加编辑搜索设置")
	@ResponseBody
	@RequestMapping(value="/configSearch/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(ConfigSearch config) throws ServiceException {
		if(config.getId() == null){
			configSearchService.addConfigSearch(config);
		}else{
			configSearchService.updateConfigSearch(config);
		}
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除搜索设置")
	@ResponseBody
	@RequestMapping(value="/configSearch/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			configSearchService.deleteConfigSearchById(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}