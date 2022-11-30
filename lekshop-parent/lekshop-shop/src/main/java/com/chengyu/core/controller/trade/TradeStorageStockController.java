package com.chengyu.core.controller.trade;


import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.component.DataPermissions;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.form.StockLogSearchForm;
import com.chengyu.core.domain.form.TradeStockSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.TradeStorageStock;
import com.chengyu.core.model.TradeStorageStockLog;
import com.chengyu.core.service.trade.TradeStorageStockService;
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

@Api(tags = "仓库库存管理")
@Controller
@RequestMapping("/member")
public class TradeStorageStockController extends ShopBaseController {

	@Autowired
	private TradeStorageStockService tradeStorageStockService;

	@ApiOperation(value = "仓库库存列表")
	@ResponseBody
	@RequestMapping(value="/storageStock/getList", method=RequestMethod.GET)
	@DataPermissions
	public CommonResult<CommonPage<TradeStorageStock>> getList(
			TradeStockSearchForm form,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
		    @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {

		List<TradeStorageStock> list = tradeStorageStockService.getStockList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "库存进出记录")
	@ResponseBody
	@RequestMapping(value="/storageStock/getLogList", method=RequestMethod.GET)
	@DataPermissions
	public CommonResult<CommonPage<TradeStorageStockLog>> getList(
			StockLogSearchForm form,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		List<TradeStorageStockLog> list = tradeStorageStockService.getStockLogList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "更新仓库预警库存")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "stockJson", value = "[{id:库存ID,warnStock:预警库存,maxStock:最高库存量}]"),
	})
	@ResponseBody
	@RequestMapping(value = {"/storageStock/updateWarnStock"}, method = RequestMethod.POST)
	public CommonResult<String> updateWarnStock(String stockJson) {
		if(StringUtils.isBlank(stockJson)){
			return CommonResult.failed("请填写库存");
		}
		List<TradeStorageStock> stockList = JSONArray.parseArray(stockJson, TradeStorageStock.class);
		tradeStorageStockService.updateWarnStock(stockList);
		return CommonResult.success(null);
	}
}