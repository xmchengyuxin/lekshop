package com.chengyu.core.controller.shop;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShopService;
import com.chengyu.core.service.shop.ShopServiceService;
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
 * @title  服务管理
 * @author LeGreen
 * @date   2022/5/6
 */
@Api(tags = "服务管理")
@Controller
@RequestMapping("/system")
public class ShopServiceController extends AdminBaseController {
	
	@Autowired
	private ShopServiceService shopServiceService;

	@ApiOperation(value = "服务管理列表")
	@ResponseBody
	@RequestMapping(value="/shopService/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsShopService>> getList(
			Integer status,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {

		List<UmsShopService> list = shopServiceService.getShopServiceList(status, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "服务选择器")
	@ResponseBody
	@RequestMapping(value="/shopService/getSelector", method=RequestMethod.GET)
	public CommonResult<List<UmsShopService>> getSelector() {
		List<UmsShopService> list = shopServiceService.getAllShopServices();
		return CommonResult.success(list);
	}

	@OperationLog
	@ApiOperation(value = "编辑服务管理")
	@ResponseBody
	@RequestMapping(value="/shopService/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(UmsShopService shopService) throws ServiceException {
		if(shopService.getId() == null){
			shopServiceService.addShopService(shopService);
		}else{
			shopServiceService.updateShopService(shopService);
		}
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除服务管理")
	@ResponseBody
	@RequestMapping(value="/shopService/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			shopServiceService.deleteShopServiceById(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}