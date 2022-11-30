package com.chengyu.core.controller.shop;

import com.chengyu.core.component.DataPermissions;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShopAccountOperationLog;
import com.chengyu.core.service.shop.ShopAccountOperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  子账号操作日志
 * @author LeGreen
 * @date   2021/1/22
 */
@Api(tags = "子账号操作日志")
@Controller
@RequestMapping("/member")
public class ShopAccountOperationLogController extends ShopBaseController {
	
	@Autowired
	private ShopAccountOperationLogService operationLogService;
	
	@ApiOperation(value = "子账号日志列表")
	@ResponseBody
	@RequestMapping(value="/accountOperation/getList", method=RequestMethod.GET)
	@DataPermissions
	public CommonResult<CommonPage<UmsShopAccountOperationLog>> getList(
			String keyword,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		List<UmsShopAccountOperationLog> list = operationLogService.getOperationLogList(null, null, keyword, null, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "删除子账号日志")
	@ResponseBody
	@RequestMapping(value="/accountOperation/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(Integer days) {
		operationLogService.deleteLogByDays(days);
		return CommonResult.success(null);
	}
}