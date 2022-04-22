package com.chengyu.core.controller.config;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.ConfigAccount;
import com.chengyu.core.service.config.ConfigAccountService;
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
 * @title  收款账号
 * @author LeGreen
 * @date   2021/1/16
 */
@Api(tags = "收款账号")
@Controller
@RequestMapping("/system")
public class ConfigAccountController extends AdminBaseController {
	
	@Autowired
	private ConfigAccountService configAccountService;
	
	@ApiOperation(value = "收款账户列表")
	@ResponseBody
	@RequestMapping(value="/configAccount/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<ConfigAccount>> getList(
			Integer type,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
		
		List<ConfigAccount> list = configAccountService.getAccountList(type, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "添加编辑收款账户")
	@ResponseBody
	@RequestMapping(value="/configAccount/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(ConfigAccount config) throws ServiceException {
		configAccountService.addConfigAccount(config);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除收款账户")
	@ResponseBody
	@RequestMapping(value="/configAccount/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			configAccountService.deleteAccount(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}