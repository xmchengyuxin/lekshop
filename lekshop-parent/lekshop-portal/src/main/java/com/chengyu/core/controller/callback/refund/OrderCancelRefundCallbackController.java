package com.chengyu.core.controller.callback.refund;

import com.chengyu.core.controller.callback.manager.CallbackManager;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.service.pay.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "订单取消退款")
@Controller
public class OrderCancelRefundCallbackController extends com.chengyu.core.controller.callback.RefundCallbackController {
	
	@ApiOperation(value = "支付宝回调")
    @ResponseBody
    @RequestMapping(value=PayService.ALIPAY_NOTIFY_URL_CANCEL_ORDER, method=RequestMethod.POST)
    public CommonResult<String> paySusJoinPinkaForZfb(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return this.refundSusForZfb(request, response, CallbackManager.CALLBACK_ORDER);
    }
	
	@ApiOperation(value = "微信回调")
    @ResponseBody
    @RequestMapping(value=PayService.WXPAY_NOTIFY_URL_CANCEL_ORDER, method=RequestMethod.POST)
    public String paySusJoinPinkaForWx(HttpServletRequest request) throws Exception{
		return this.refundSusForWx(request, CallbackManager.CALLBACK_ORDER);
    }
	
}