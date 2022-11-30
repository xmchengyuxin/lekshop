package com.chengyu.core.controller.order;

import com.chengyu.core.component.DataPermissions;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.form.OrderSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderWriteOff;
import com.chengyu.core.service.order.OrderWriteOffService;
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

@Api(tags = "订单核销")
@Controller
@RequestMapping("/member")
public class OrderWriteOffController extends ShopBaseController {
	
	@Autowired
	private OrderWriteOffService writeOffService;

	@ApiOperation(value = "订单核销列表")
	@ResponseBody
	@RequestMapping(value="/order/getWriteOffList", method=RequestMethod.GET)
	@DataPermissions
	public CommonResult<CommonPage<OmsOrderWriteOff>> getList(OrderSearchForm form,
													  @RequestParam(value = "page", defaultValue = "1") Integer page,
													  @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		List<OmsOrderWriteOff> list = writeOffService.getWriteOffOrderList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "订单核销")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "verifyCode", value = "核销码")
	})
	@ResponseBody
	@RequestMapping(value="/order/writeOff", method=RequestMethod.POST)
	public CommonResult<String> writeOff(String verifyCode) throws ServiceException {
		writeOffService.writeOff(getCurrentAccount(), verifyCode);
		return CommonResult.success(null);
	}

}