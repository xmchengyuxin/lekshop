package com.chengyu.core.controller.shop;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.ShopCateResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShopCate;
import com.chengyu.core.service.shop.ShopCateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  店铺类目
 * @author LeGreen
 * @date   2022/5/7
 */
@Api(tags = "店铺类目")
@Controller
@RequestMapping("/member")
public class ShopCateController extends ShopBaseController {
	
	@Autowired
	private ShopCateService shopCateService;
	
	@ApiOperation(value = "店铺类目列表")
	@ResponseBody
	@RequestMapping(value="/shopCate/getList", method=RequestMethod.GET)
	public CommonResult<List<ShopCateResult>> getList(Integer status) throws ServiceException {
		List<ShopCateResult> list = shopCateService.getShopCateResults(getCurrentShop().getId(), status);
		return CommonResult.success(list);
	}

	@OperationLog
	@ApiOperation(value = "添加店铺类目")
	@ResponseBody
	@RequestMapping(value="/shopCate/add", method=RequestMethod.POST)
	public CommonResult<String> add(Integer pid, String name, Integer sort, String img) throws ServiceException {
		shopCateService.addShopCate(getCurrentShop(), pid, name, sort, img);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "编辑店铺类目")
	@ResponseBody
	@RequestMapping(value="/shopCate/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(UmsShopCate cate) throws ServiceException {
		if(cate.getId() == null){
			shopCateService.addShopCate(getCurrentShop(), cate.getPid(), cate.getName(), cate.getSort(), cate.getImg());
		}else {
			shopCateService.updateShopCate(cate);
		}
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除店铺类目")
	@ResponseBody
	@RequestMapping(value="/goodsCate/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			shopCateService.deleteShopCate(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}