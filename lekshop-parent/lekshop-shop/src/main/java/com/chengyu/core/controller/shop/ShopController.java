package com.chengyu.core.controller.shop;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopAccount;
import com.chengyu.core.service.shop.ShopPermissionsService;
import com.chengyu.core.service.shop.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @title  店铺资料
 * @author LeGreen
 * @date   2022/5/9
 */
@Api(tags = "店铺资料")
@Controller
@RequestMapping("/member")
public class ShopController extends ShopBaseController {

	@Autowired
	private ShopPermissionsService shopPermissionsService;

	@Autowired
	private ShopService shopService;

	@ApiOperation(value = "店铺资料")
	@ResponseBody
	@RequestMapping(value="/shop/get", method=RequestMethod.GET)
	public CommonResult<Map<String, Object>> get() throws ServiceException {
		UmsShopAccount account = getCurrentAccountOrNull();
		Map<String, Object> data = new HashMap<>();
		if(account != null) {
			account.setPassword(null);
			account.setPayPassword(null);
			data.put("member", account);
			data.put("roles", new String[]{(account.getMainInd() != null && account.getMainInd() == 1) ? "admin" : "edit"});
			UmsShop shopInfo = shopService.getShopByMemberId(account.getMemberId());
			data.put("shopInfo", shopInfo);
			Map<String, Object> roleMap = shopPermissionsService.getAccountCurrentOrg(account);
			data.putAll(roleMap);
		}
		return CommonResult.success(data);
	}

	@ApiOperation(value = "店铺资料")
	@ResponseBody
	@RequestMapping(value="/shop/getInfo", method=RequestMethod.GET)
	public CommonResult<UmsShop> getInfo() throws ServiceException {
		return CommonResult.success(getCurrentShop());
	}

	@OperationLog
	@ApiOperation(value = "修改店铺资料")
	@ResponseBody
	@RequestMapping(value={"/shop/update"}, method=RequestMethod.POST)
	public CommonResult<Integer> update(UmsShop shop) throws Exception {
		shop.setId(getCurrentShop().getId());
		shopService.updateShop(shop);
		return CommonResult.success(null);
	}
}