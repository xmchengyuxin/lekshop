package com.chengyu.core.controller.trade;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.component.DataPermissions;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.TradeSupplier;
import com.chengyu.core.service.trade.TradeSupplierService;
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

@Api(tags = "供应商管理")
@Controller
@RequestMapping("/member")
public class TradeSupplierController extends ShopBaseController {

	@Autowired
	private TradeSupplierService tradeSupplierService;

	@ApiOperation(value = "供应商列表")
	@ResponseBody
	@RequestMapping(value="/supplier/getList", method=RequestMethod.GET)
	@DataPermissions
	public CommonResult<CommonPage<TradeSupplier>> getList(@RequestParam(value = "page", defaultValue = "1") Integer page,
														   @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {

		List<TradeSupplier> list = tradeSupplierService.getTradeSupplierList(page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "添加更新供应商")
	@ResponseBody
	@RequestMapping(value = {"/supplier/add"}, method = RequestMethod.POST)
	public CommonResult<String> add(TradeSupplier supplier) throws ServiceException {
		tradeSupplierService.addSupplier(supplier);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除供应商")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", value = "供应商IDS"),
	})
	@ResponseBody
	@RequestMapping(value = {"/supplier/delete"}, method = RequestMethod.POST)
	public CommonResult<String> delete(String ids) {
		if (StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}

		List<Integer> idsList = Convert.convert(new TypeReference<List<Integer>>() {}, ids.split(CommonConstant.DH_REGEX));
		tradeSupplierService.deleteSupplier(idsList);
		return CommonResult.success(null);
	}
}