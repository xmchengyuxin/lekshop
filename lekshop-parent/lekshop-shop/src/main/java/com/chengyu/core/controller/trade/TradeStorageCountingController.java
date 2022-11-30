package com.chengyu.core.controller.trade;


import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.component.DataPermissions;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.form.TradeStockCountingSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.TradeStorageStockCounting;
import com.chengyu.core.model.TradeStorageStockCountingDetail;
import com.chengyu.core.service.trade.TradeStorageCountingService;
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

@Api(tags = "盲盘")
@Controller
@RequestMapping("/member")
public class TradeStorageCountingController extends ShopBaseController {

	@Autowired
	private TradeStorageCountingService storageCountingService;

	@ApiOperation(value = "盲盘列表")
	@ResponseBody
	@RequestMapping(value="/storageCounting/getList", method=RequestMethod.GET)
	@DataPermissions
	public CommonResult<CommonPage<TradeStorageStockCounting>> getList(
			TradeStockCountingSearchForm form,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
		    @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {

		List<TradeStorageStockCounting> list = storageCountingService.getStockCountingList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "盲盘明细")
	@ResponseBody
	@RequestMapping(value="/storageCounting/getDetailList", method=RequestMethod.GET)
	@DataPermissions
	public CommonResult<CommonPage<TradeStorageStockCountingDetail>> getList(
			Integer stockCountingId, Integer status,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {

		List<TradeStorageStockCountingDetail> list = storageCountingService.getStockCountingDetailList(stockCountingId, status, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "添加盲盘")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countingJson", value = "[{goodsId:商品ID,countingNum:盲盘数,stock:库存}]"),
	})
	@ResponseBody
	@RequestMapping(value = {"/storageCounting/add"}, method = RequestMethod.POST)
	public CommonResult<String> add(String countingJson) throws ServiceException {
		if(StringUtils.isBlank(countingJson)){
			return CommonResult.failed("请填写盲盘");
		}
		List<TradeStorageStockCountingDetail> countingDetailList = JSONArray.parseArray(countingJson, TradeStorageStockCountingDetail.class);
		storageCountingService.addStockCounting(getCurrentAccount(), countingDetailList);
		return CommonResult.success(null);
	}
}