package com.chengyu.core.controller.trade;


import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.component.DataPermissions;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.form.TradeStockSearchForm;
import com.chengyu.core.domain.result.TradeStockEditResult;
import com.chengyu.core.domain.result.TradeStockOrderResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.TradeStorageStockEdit;
import com.chengyu.core.model.TradeStorageStockOrder;
import com.chengyu.core.model.TradeStorageStockOrderDetail;
import com.chengyu.core.service.trade.TradeStorageStockOrderService;
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

import java.util.List;

@Api(tags = "仓库手动入库")
@Controller
@RequestMapping("/member")
public class TradeStorageStockOrderController extends ShopBaseController {

	@Autowired
	private TradeStorageStockOrderService storageStockOrderService;

	@ApiOperation(value = "入库单列表")
	@ResponseBody
	@RequestMapping(value="/stockOrder/getList", method=RequestMethod.GET)
	@DataPermissions
	public CommonResult<CommonPage<TradeStorageStockOrder>> getList(
			TradeStockSearchForm form,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
		    @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {

		List<TradeStorageStockOrder> list = storageStockOrderService.getStorageStockOrderList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "入库单详情")
	@ResponseBody
	@RequestMapping(value="/stockOrder/get", method=RequestMethod.GET)
	public CommonResult<TradeStockOrderResult> getOrder(Integer id) {
		TradeStockOrderResult result = storageStockOrderService.getStockOrderResult(id);
		return CommonResult.success(result);
	}

	@OperationLog
	@ApiOperation(value = "添加入库单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "stockJson", value = "[storageId:仓库ID, spaceNo: 货位编码, spaceName:货位名称, skuId: 商品SKUID, inNum: 进货数量, buyPrice: 进货价, discount:折扣, totalPrice: 总价]"),
	})
	@ResponseBody
	@RequestMapping(value = {"/stockOrder/add"}, method = RequestMethod.POST)
	public CommonResult<String> add(TradeStorageStockOrder order, String stockJson) throws ServiceException {
		if(StringUtils.isEmpty(stockJson)) {
			return CommonResult.failed("请选择入库商品");
		}
		List<TradeStorageStockOrderDetail> detailList = JSONArray.parseArray(stockJson, TradeStorageStockOrderDetail.class);
		storageStockOrderService.addStockOrder(getCurrentShop(), order, detailList);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "库存修改申请单列表")
	@ResponseBody
	@RequestMapping(value="/stockEdit/getList", method=RequestMethod.GET)
	@DataPermissions
	public CommonResult<CommonPage<TradeStorageStockEdit>> getEditList(
			TradeStockSearchForm form,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

		List<TradeStorageStockEdit> list = storageStockOrderService.getStorageStockEditList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "库存修改申请单详情")
	@ResponseBody
	@RequestMapping(value="/stockEdit/get", method=RequestMethod.GET)
	public CommonResult<TradeStockEditResult> get(Integer id) {
		TradeStockEditResult result = storageStockOrderService.getStorageStockEditResult(id);
		return CommonResult.success(result);
	}

	@OperationLog
	@ApiOperation(value = "添加库存修改单")
	@ResponseBody
	@RequestMapping(value = {"/stockEdit/add"}, method = RequestMethod.POST)
	public CommonResult<String> addStockEdit(TradeStorageStockEdit stockEdit) throws ServiceException {
		storageStockOrderService.addStockEdit(stockEdit);
		return CommonResult.success(null);
	}

}