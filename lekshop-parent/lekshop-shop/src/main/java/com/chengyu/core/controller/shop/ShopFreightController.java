package com.chengyu.core.controller.shop;

import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShopFreightTemplate;
import com.chengyu.core.model.UmsShopFreightTemplateArea;
import com.chengyu.core.service.shop.ShopFreightService;
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
 * @title  店铺运费模板运费模板
 * @author LeGreen
 * @date   2022/5/6
 */
@Api(tags = "店铺运费模板")
@Controller
@RequestMapping("/member")
public class ShopFreightController extends ShopBaseController {
	
	@Autowired
	private ShopFreightService shopFreightService;
	
	@ApiOperation(value = "店铺运费模板列表")
	@ResponseBody
	@RequestMapping(value="/shopFreight/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsShopFreightTemplate>> getList(
			String name,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		
		List<UmsShopFreightTemplate> list = shopFreightService.getFreightTemplateList(getCurrentShop().getId(), name, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "添加店铺运费模板")
	@ResponseBody
	@RequestMapping(value="/shopFreight/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(UmsShopFreightTemplate template, String areaJson) throws ServiceException {
		List<UmsShopFreightTemplateArea> areaList = JSONArray.parseArray(areaJson, UmsShopFreightTemplateArea.class);
		shopFreightService.addFreightTemplate(getCurrentShop(), template, areaList);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除店铺运费模板")
	@ResponseBody
	@RequestMapping(value="/shopFreight/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			shopFreightService.deleteFreightTemplate(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}