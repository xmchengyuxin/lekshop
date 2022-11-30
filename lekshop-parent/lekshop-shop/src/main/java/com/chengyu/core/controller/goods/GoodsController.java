package com.chengyu.core.controller.goods;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.ThirdEnums;
import com.chengyu.core.domain.form.GoodsPublishForm;
import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.domain.result.GoodsExportResult;
import com.chengyu.core.domain.result.GoodsResult;
import com.chengyu.core.domain.result.GoodsStockResult;
import com.chengyu.core.domain.result.GoodsThirdResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.PmsGoods;
import com.chengyu.core.model.PmsGoodsGroup;
import com.chengyu.core.model.PmsGoodsSku;
import com.chengyu.core.service.goods.GoodsService;
import com.chengyu.core.util.third.logic.TaofakeLogic;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * @title  商品
 * @author LeGreen
 * @date   2022/5/7
 */
@Api(tags = "商品")
@Controller
@RequestMapping("/member")
public class GoodsController extends ShopBaseController {
	
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private TaofakeLogic taofakeLogic;
	
	@ApiOperation(value = "商品列表")
	@ResponseBody
	@RequestMapping(value="/goods/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<PmsGoods>> getList(GoodsSearchForm form,
													   @RequestParam(value = "page", defaultValue = "1") Integer page,
													   @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		form.setShopId(getCurrentShop().getId());
		List<PmsGoods> list = goodsService.getGoodsList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "商品详情")
	@ResponseBody
	@RequestMapping(value="/goods/get", method=RequestMethod.GET)
	public CommonResult<GoodsResult> get(Integer goodsId) {
		GoodsResult goods = goodsService.getGoodsResult(goodsId);
		return CommonResult.success(goods);
	}

	@OperationLog
	@ApiOperation(value = "添加编辑商品")
	@ResponseBody
	@RequestMapping(value="/goods/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(GoodsPublishForm publishForm, String groupJson) throws ServiceException {
		if(StringUtils.isNotBlank(groupJson)){
			publishForm.setGroupList(JSONArray.parseArray(groupJson, PmsGoodsGroup.class));
		}
		goodsService.publishGoods(getCurrentShop(), publishForm);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "商品Sku列表")
	@ResponseBody
	@RequestMapping(value = "/goods/getSkuList", method = RequestMethod.GET)
	public CommonResult<List<PmsGoodsSku>> getSkuList(Integer goodsId) {
		List<PmsGoodsSku> list = goodsService.getGoodsSkuList(goodsId).getSkuList();
		return CommonResult.success(list);
	}

	@OperationLog
	@ApiOperation(value = "批量上架")
	@ResponseBody
	@RequestMapping(value="/goods/shangjia", method=RequestMethod.POST)
	public CommonResult<String> shangjia(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		List<Integer> goodsIdList = getIdList(ids);
		goodsService.shangjiaGoods(goodsIdList);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "批量下架")
	@ResponseBody
	@RequestMapping(value="/goods/xiajia", method=RequestMethod.POST)
	public CommonResult<String> xiajia(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		List<Integer> goodsIdList = getIdList(ids);
		goodsService.xiajiaGoods(goodsIdList);
		return CommonResult.success(null);
	}

	private List<Integer> getIdList(String ids){
		List<String> idStrList = Arrays.asList(ids.split(CommonConstant.DH_REGEX));
		return Convert.convert(new TypeReference<List<Integer>>() {}, idStrList);
	}

	@OperationLog
	@ApiOperation(value = "删除商品")
	@ResponseBody
	@RequestMapping(value="/goods/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		List<Integer> goodsIdList = getIdList(ids);
		goodsService.deleteGoods(goodsIdList);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "识别第三方商品详情")
	@ResponseBody
	@RequestMapping(value="/goods/getThirdDetail", method=RequestMethod.GET)
	public CommonResult<GoodsThirdResult> getThirdDetail(String url) throws ServiceException {
		GoodsThirdResult goods = thirdManager.getThidFactory(this.getThirdMod(ThirdEnums.GOODS.getKey())).getGoodsDetail(url);
		return CommonResult.success(goods);
	}


	@ApiOperation(value = "商品库存列表")
	@ResponseBody
	@RequestMapping(value = "/goods/getStockList", method = RequestMethod.GET)
	public CommonResult<CommonPage<GoodsStockResult>> getStockList(
			GoodsSearchForm form,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		form.setShopId(getCurrentShopId());
		List<GoodsStockResult> list = goodsService.getGoodsStockList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "更新商品库存")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "stockJson", value = "[{id:商品ID,stock:库存}]"),
	})
	@ResponseBody
	@RequestMapping(value = {"/goods/updateStock"}, method = RequestMethod.POST)
	public CommonResult<String> updateStock(String stockJson) {
		if(StringUtils.isBlank(stockJson)){
			return CommonResult.failed("请填写库存");
		}
		List<PmsGoodsSku> stockList = JSONArray.parseArray(stockJson, PmsGoodsSku.class);
		goodsService.updateGoodsStock(stockList);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "同步仓库库存")
	@ResponseBody
	@RequestMapping(value = {"/goods/synStock"}, method = RequestMethod.POST)
	public CommonResult<String> synStock() throws ServiceException {
		goodsService.synStock(getCurrentShopId());
		return CommonResult.success(null);
	}

	@ApiOperation(value = "导出商品列表")
	@ResponseBody
	@RequestMapping(value="/goods/getExportList", method=RequestMethod.GET)
	public CommonResult<List<GoodsExportResult>> getExportList(GoodsSearchForm form) throws ServiceException {
		form.setShopId(getCurrentShop().getId());
		List<GoodsExportResult> list = goodsService.getExportList(form, 1, 99999);
		return CommonResult.success(list);
	}
}