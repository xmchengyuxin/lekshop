package com.chengyu.core.controller.shop;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.ShopSearchForm;
import com.chengyu.core.domain.result.ShopDataResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.service.shop.ShopService;
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
 * @title  店铺
 * @author LeGreen
 * @date   2022/5/6
 */
@Api(tags = "店铺")
@Controller
@RequestMapping("/system")
public class ShopController extends AdminBaseController {
	
	@Autowired
	private ShopService shopService;
	
	@ApiOperation(value = "店铺列表")
	@ResponseBody
	@RequestMapping(value="/shop/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsShop>> getList(
			ShopSearchForm form,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		
		List<UmsShop> list = shopService.getShopList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "店铺数据")
	@ResponseBody
	@RequestMapping(value="/shop/getDataList", method=RequestMethod.GET)
	public CommonResult<CommonPage<ShopDataResult>> getDataList(
			ShopSearchForm form,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

		List<ShopDataResult> list = shopService.getShopDataList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "编辑店铺")
	@ResponseBody
	@RequestMapping(value="/shop/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(UmsShop shop) throws ServiceException {
		shopService.updateShop(shop);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除店铺")
	@ResponseBody
	@RequestMapping(value="/shop/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			shopService.deleteShop(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}