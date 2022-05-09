package com.chengyu.core.controller.common;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.service.kaptcha.CaptchaService;
import com.chengyu.core.utils.Base64Utils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * @title  校验码
 * @author LeGreen
 * @date   2021/1/8
 */
@Api(tags = "图形验证码")
@Controller
@RequestMapping("/common")
public class KaptchaController extends UserBaseController {
	
   @Autowired
   private DefaultKaptcha producer;
   @Autowired
   private CaptchaService captchaService;
	
	@ApiOperation(value = "获取验证码")
	@ResponseBody
	@RequestMapping(value="/captcha", method=RequestMethod.GET)
	public CommonResult<Map<String,Object>> captcha() throws Exception {
		// 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
 
        // 生成captcha的token
        Map<String, Object> map =captchaService.createToken(text);
        map.put("img", Base64Utils.encode(outputStream.toByteArray()));
		return CommonResult.success(map);
	}
	
}