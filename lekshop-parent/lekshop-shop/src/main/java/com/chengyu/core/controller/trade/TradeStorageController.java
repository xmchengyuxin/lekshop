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
import com.chengyu.core.model.TradeStorage;
import com.chengyu.core.service.trade.TradeStorageService;
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

@Api(tags = "仓库管理")
@Controller
@RequestMapping("/member")
public class TradeStorageController extends ShopBaseController {

	@Autowired
	private TradeStorageService tradeStorageService;

	@ApiOperation(value = "仓库列表")
	@ResponseBody
	@RequestMapping(value="/storage/getList", method=RequestMethod.GET)
	@DataPermissions
	public CommonResult<CommonPage<TradeStorage>> getList(@RequestParam(value = "page", defaultValue = "1") Integer page,
														  @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {

		List<TradeStorage> list = tradeStorageService.getTradeStorageList(page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "添加更新仓库")
	@ResponseBody
	@RequestMapping(value = {"/storage/add"}, method = RequestMethod.POST)
	public CommonResult<String> add(TradeStorage storage) throws ServiceException {
		tradeStorageService.addStorage(storage);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除仓库")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", value = "仓库IDS"),
	})
	@ResponseBody
	@RequestMapping(value = {"/storage/delete"}, method = RequestMethod.POST)
	public CommonResult<String> delete(String ids) {
		if (StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}

		List<Integer> idsList = Convert.convert(new TypeReference<List<Integer>>() {}, ids.split(CommonConstant.DH_REGEX));
		tradeStorageService.deleteStorage(idsList);
		return CommonResult.success(null);
	}
}