package com.chengyu.core.controller.shop;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.ShopSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShopInfo;
import com.chengyu.core.service.shop.ShopInfoService;
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
 * @title  店铺审核
 * @author LeGreen
 * @date   2022/5/6
 */
@Api(tags = "店铺审核")
@Controller
@RequestMapping("/system")
public class ShopVerifyController extends AdminBaseController {
	
	@Autowired
	private ShopInfoService shopInfoService;
	
	@ApiOperation(value = "店铺审核列表")
	@ResponseBody
	@RequestMapping(value="/shopInfo/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsShopInfo>> getList(
			ShopSearchForm form,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		
		List<UmsShopInfo> list = shopInfoService.getShopInfoList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "审核店铺")
	@ResponseBody
	@RequestMapping(value="/shopInfo/verify", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(Integer shopInfoId, Integer status, String reason) throws ServiceException {
		shopInfoService.verifyShop(shopInfoId, status, reason);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除店铺认证信息")
	@ResponseBody
	@RequestMapping(value="/shopInfo/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			shopInfoService.deleteShopInfo(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}