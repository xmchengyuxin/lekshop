package com.chengyu.core.controller.callback.pay;

import com.chengyu.core.controller.callback.PayCallbackController;
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

@Api(tags = "充值回调")
@Controller
public class RechargeCallbackController extends PayCallbackController {
	
	@ApiOperation(value = "支付宝支付回调")
    @ResponseBody
    @RequestMapping(value= PayService.ALIPAY_NOTIFY_URL_RECHARGE, method=RequestMethod.POST)
    public CommonResult<String> paySusForZfb(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return this.paySusForZfb(request, response, CallbackManager.CALLBACK_RECHARGE);
    }
	
	@ApiOperation(value = "微信支付回调")
    @ResponseBody
    @RequestMapping(value=PayService.WXPAY_NOTIFY_URL_RECHARGE, method=RequestMethod.POST)
    public String paySusForWx(HttpServletRequest request) throws Exception{
		return this.paySusForWx(request, CallbackManager.CALLBACK_RECHARGE);
    }
	
}