package com.chengyu.core.controller.common;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.MemberTypes;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysInviteCode;
import com.chengyu.core.model.SysWeixinConfig;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.service.system.SysInviteCodeService;
import com.chengyu.core.util.weixin.WechatUtil;
import com.chengyu.core.util.weixin.XCXWeixin;
import com.chengyu.core.utils.HttpUtil;
import com.chengyu.core.utils.JsonUtils;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @title  小程序登录
 * @author LeGreen
 * @date   2022/5/14
 */
@Api(tags = "小程序登录")
@Controller
@RequestMapping("/common")
public class LoginXcxController extends UserBaseController {
	
	private Logger logger = LoggerFactory.getLogger(LoginXcxController.class);
	
	@Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
	@Autowired
	private SysInviteCodeService inviteCodeService;
    
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "小程序授权登录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "小程序授权码"),
	})
	@RequestMapping(value={"/xcx/auth"}, method=RequestMethod.POST)
    @ResponseBody
    public CommonResult auth(String code){
		SysWeixinConfig config = WechatUtil.config;
    	String url = WechatUtil.JS_CODE_URL
    			.replace("APPID", config.getXcxAppId())
    			.replace("SECRET", config.getXcxSecret())
    			.replace("JSCODE", code);
    	
    	JSONObject result = HttpUtil.doGet(url);
    	System.out.println(result);

    	String errcode = result.getString("errcode");
    	if(StringUtils.isEmpty(errcode) || "0".equals(errcode)){
    		return CommonResult.success(result);
    	}else{
    		return CommonResult.failed("授权登录失败");
    	}
    }

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "通过小程序注册账号")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "encryptedData", value = "小程序encryptedData"),
		@ApiImplicitParam(name = "sessionKey", value = "小程序sessionKey"),
		@ApiImplicitParam(name = "iv", value = "小程序iv"),
		@ApiImplicitParam(name = "inviteCode", value = "邀请码"),
		@ApiImplicitParam(name = "applicationType", value = "xcx:小程序,gzh：公众号,app:APP"),
		@ApiImplicitParam(name = "openId", value = "openId"),
		@ApiImplicitParam(name = "unionId", value = "unionId"),
		@ApiImplicitParam(name = "phone", value = "手机")
	})
	@RequestMapping(value={"/xcx/regitser"}, method=RequestMethod.POST)
    @ResponseBody
    public CommonResult regitser(String encryptedData, String sessionKey, String iv, String inviteCode, String openId, String unionId, String phone) throws ServiceException{
		logger.info("xcx register params>>encryptedData>>"+encryptedData+"sessionKey>>"+sessionKey+"iv>>"+iv);
		XCXWeixin weixin = this.getUserInfo(encryptedData, sessionKey, iv);
		logger.info("小程序注册>>"+JSONUtil.toJsonStr(weixin));
		UmsMember member;
		if(weixin == null){
			return CommonResult.failed("微信授权失败");
		}
		if (StringUtils.isBlank(openId)) {
		  openId = weixin.getOpenId();
		}
		if (StringUtils.isBlank(unionId)) {
			unionId = weixin.getUnionId();
		}
		member = memberService.getMemberByParams(openId, MemberTypes.XCX_OPEN_ID);
		if(member == null){
			member = new UmsMember();
			member.setXcxOpenId(openId);
			member.setUnionid(unionId);
			member.setHeadImg(weixin.getAvatarUrl());
			member.setWeixin(weixin.getNickName());
			if(StringUtils.isNotBlank(phone)){
				member.setPhone(phone);
				member.setCode(phone);
				member.setPhoneStatus(CommonConstant.SUS_INT);
			}else{
				member.setCode(weixin.getNickName()+StringUtils.getStringRandom(4));
			}
			member.setNickname(weixin.getNickName());
			member.setGender(weixin.getGender());
			member.setProvince(weixin.getProvince());
			member.setCity(weixin.getCity());
			member.setArea(weixin.getCountry());
			member.setMethod(1);
			member.setType(CommonConstant.BUYER);
			if (StringUtils.isNotBlank(inviteCode)) {
				if ("8912058".equals(inviteCode)) {
					member.setTjrId(0);
				} else {
					SysInviteCode config = inviteCodeService.getConfigByInviteCode(inviteCode);
					if (config != null) {
					 	member.setTjrId(config.getUserId());
				    }
				}
		    }
		    member.setPassword("123456");
			memberService.register(member, false);
		}else{
			//登录
			if(StringUtils.isBlank(member.getXcxOpenId())){
				UmsMember updateMember = new UmsMember();
				updateMember.setId(member.getId());
				updateMember.setXcxOpenId(openId);
				updateMember.setUnionid(unionId);
				updateMember.setNickname(weixin.getNickName());
				updateMember.setHeadImg(weixin.getAvatarUrl());
				memberService.updateMember(updateMember);
			}
		}
		
		//进行登录
		String token = memberService.loginByNoPassword(member.getCode());
        if (token == null) {
        	return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
		
	 }
	
	@ApiOperation(value = "小程序获取手机授权")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "encryptedData", value = "小程序encryptedData"),
		@ApiImplicitParam(name = "sessionKey", value = "小程序sessionKey"),
		@ApiImplicitParam(name = "iv", value = "小程序iv")
	})
	@RequestMapping(value={"/xcx/getPhone"}, method=RequestMethod.GET)
	@ResponseBody
	public CommonResult<String> getPhone(String encryptedData, String sessionKey, String iv) throws Exception{
		XCXWeixin weixin = this.getUserInfo(encryptedData, sessionKey, iv);
		return CommonResult.success(weixin != null ? weixin.getPhoneNumber() : null);
	 }

	//javax.crypto.BadPaddingException: pad block corrupted出现该异常应该是转义\/字符失败，待核查
    public XCXWeixin getUserInfo(String encryptedData, String sessionKey, String iv){
    	// 被加密的数据
        byte[] dataByte;
        // 加密秘钥
        byte[] keyByte;
        // 偏移量
        byte[] ivByte;
		dataByte = Base64.decode(encryptedData);
		keyByte = Base64.decode(sessionKey);
		ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
				return JsonUtils.fromJson(result, XCXWeixin.class);
            }
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchProviderException | InvalidKeyException | UnsupportedEncodingException | NoSuchPaddingException | InvalidParameterSpecException | IllegalBlockSizeException | BadPaddingException e) {
        	logger.error(e.getMessage(), e);
        }
		return null;
    }
   
    public static void main(String[] args) {
		String encryptedData = "c6tFi+rsV3/Np0lNlf49tr0c4WucxOvBCDbmaNA4UWEjvZRNnzH4CxdL8/gPZGtVZvFP8tImt0sRjTYfQjgJEfXF22xHVM54jCH63wSd1LXYxta7VNidNcfuB1Ng1Y8BBDmMf/CHmwkXlmg40z5OiLlA2raMGjn7970bi0WmHzDTqF60RgQiFzLN7hbmqpk/FuiVz3CmJLWQXJwZMSnYHTTvompePE6VrJELlrDAhUdX8NU52YLaNEroZmwvPMT2mGr+a7nwENE6GGsPVRStj+iduu46E0ET4b7AOIhoEaKWTbxTFEYQsJDlYzl7n+9ovXyopzgkMtJeG7LjYBnM2+s8gRYozaZdMCRj5SigAB8CB6SEWL6G4jJWWfcUHE97k98MFCPOGJwyJO/l6cFm/QN++eyJ5WFZcFszC7rfwdNJAAG3J6B4vXi928qk1izqVtIMKiuxxL6bIST0rmujTiYJsR1AV8PZzMyZPP5OqvH9JYYJHnUxtD/RU4Wlw/9X";
		String sessionKey = "CoiEq9x/3qgv2IFvy42UiA==";
		String iv = "X0RsCUEFHCNG17WPdSftTA==";
		XCXWeixin weixin = new LoginXcxController().getUserInfo(encryptedData, sessionKey, iv);
		System.out.println(weixin);
	}
}