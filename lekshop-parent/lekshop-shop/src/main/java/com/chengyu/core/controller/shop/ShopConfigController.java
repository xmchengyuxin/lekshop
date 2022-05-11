package com.chengyu.core.controller.shop;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShopConfig;
import com.chengyu.core.service.shop.ShopConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @title  店铺配置
 * @author LeGreen
 * @date   2022/5/9
 */
@Api(tags = "店铺配置")
@Controller
@RequestMapping("/member")
public class ShopConfigController extends ShopBaseController {
	
	@Autowired
	private ShopConfigService shopConfigService;

	@ApiOperation(value = "店铺配置详情")
	@ResponseBody
	@RequestMapping(value="/shopConfig/get", method=RequestMethod.GET)
	public CommonResult<UmsShopConfig> get() throws ServiceException {
		UmsShopConfig config = shopConfigService.getShopConfig(getCurrentShop().getId());
		return CommonResult.success(config);
	}

	@OperationLog
	@ApiOperation(value = "修改店铺配置")
	@ResponseBody
	@RequestMapping(value={"/shopConfig/editSubmit"}, method=RequestMethod.POST)
	public CommonResult<Integer> editSubmit(UmsShopConfig config) {
		shopConfigService.updateShopConfig(config);
		return CommonResult.success(null);
	}

}