package com.chengyu.core.controller.common;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.DeliveryEnums;
import com.chengyu.core.domain.enums.MemberTypes;
import com.chengyu.core.domain.enums.SecretEnum;
import com.chengyu.core.domain.enums.ThirdEnums;
import com.chengyu.core.domain.result.KeyNameResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.*;
import com.chengyu.core.service.content.*;
import com.chengyu.core.service.system.BankConfigService;
import com.chengyu.core.service.system.ConfigService;
import com.chengyu.core.service.system.QiniuService;
import com.chengyu.core.service.system.VerifyCodeService;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title  配置管理
 * @author LeGreen
 * @date   2021/1/8
 */
@Api(tags = "配置管理")
@Controller
@RequestMapping("/common")
public class ConfigController extends ShopBaseController {
	
	@Autowired
	private ConfigService configService;
	@Autowired
	private AdvertService advertService;
	@Autowired
	private BannerService bannerService;
	@Autowired
	private QiniuService qiniuService;
	@Autowired
	private AgreementService agreementService;
	@Autowired
	private VerifyCodeService verifyCodeService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleCateService articleCateService;
	@Autowired
	private BankConfigService bankConfigService;
	@Autowired
	private NoticeService noticeService;

	@ApiOperation(value = "系统参数列表")
	@ResponseBody
	@RequestMapping(value="/config/getList", method=RequestMethod.GET)
	public CommonResult<Map<String,String>> getConfig() {
		Map<String, String> map = configService.getAllConfigValueMap();
		return CommonResult.success(map);
	}

	@ApiOperation(value = "轮播图列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "location", value = "位置(1首页)")
	})
	@ResponseBody
	@RequestMapping(value="/banner/getList", method=RequestMethod.GET)
	public CommonResult<List<CmsBanner>> getBanner(String location) throws Exception {
		return CommonResult.success(bannerService.getAllBanners(location));
	}

	@ApiOperation(value = "文章分类")
	@ResponseBody
	@RequestMapping(value="/articleCate/getCateList", method=RequestMethod.GET)
	public CommonResult<List<CmsArticleCate>> getCateList() {
		return CommonResult.success(articleCateService.getAllArticleCates());
	}

	@ApiOperation(value = "文章列表")
	@ResponseBody
	@RequestMapping(value="/article/getList", method=RequestMethod.GET)
	public CommonResult<List<CmsArticle>> getArticleList(Integer cateId, Integer type) throws Exception {
		return CommonResult.success(articleService.getAllArticles(cateId, type));
	}
	
	@ApiOperation(value = "文章详情")
	@ResponseBody
	@RequestMapping(value="/article/get", method=RequestMethod.GET)
	public CommonResult<CmsArticle> get(Integer articleId) throws Exception {
		return CommonResult.success(articleService.getArticleById(articleId));
	}

	@ApiOperation(value = "公告详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cate", value = "user用户端>>shop商家端")
	})
	@ResponseBody
	@RequestMapping(value="/notice/get", method=RequestMethod.GET)
	public CommonResult<CmsNotice> getNotice(String cate) {
		return CommonResult.success(noticeService.getNoticeByCate(cate));
	}
	
	@ApiOperation(value = "广告信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "cate", value = "待定")
	})
	@ResponseBody
	@RequestMapping(value="/advert/get", method=RequestMethod.GET)
	public CommonResult<CmsAdvert> getAdvert(String cate) throws Exception {
		return CommonResult.success(advertService.getAdvertByCate(cate));
	}
	
	@ApiOperation(value = "协议信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "cate", value = "待定")
	})
	@ResponseBody
	@RequestMapping(value="/agreement/get", method=RequestMethod.GET)
	public CommonResult<CmsAgreement> getAgreement(String cate) throws Exception {
		return CommonResult.success(agreementService.getAgreementByCate(cate));
	}
	
	@ApiOperation(value = "获取银行卡配置")
	@RequestMapping(value = "/getBankSelector", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<SysBankConfig>> getBankList() throws ServiceException{
		return CommonResult.success(bankConfigService.getBankList());
	}

	@ApiOperation(value = "获取密保问题")
	@ResponseBody
	@RequestMapping(value="/secret/getList", method=RequestMethod.GET)
	public CommonResult<List<Map<String, String>>> getSecretList() {
		List<Map<String, String>> list = new ArrayList<>();
		for(SecretEnum secretEnum : SecretEnum.values()){
			Map<String, String> map = new HashMap<>();
			map.put("key", secretEnum.getKey());
			map.put("question", secretEnum.getQuestion());
			list.add(map);
		}
		return CommonResult.success(list);
	}
	
	@ApiOperation(value = "上传图片")
    @ResponseBody
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public CommonResult<Map<String,Object>> upload(MultipartHttpServletRequest request) throws Exception {
		Map<String, MultipartFile> fileMap = request.getFileMap();
		if(fileMap.size() <= 0) {
			return CommonResult.success(null);
		}
		
		Map<String,Object> result = new HashMap<>(16);
		for(String field : fileMap.keySet()) {
			MultipartFile file = fileMap.get(field);
			if(file == null || file.getSize() <= 0 || StringUtils.isBlank(file.getOriginalFilename())) {
				continue;
			}
			
			long currentTime = System.currentTimeMillis();
			String fileName = currentTime + "" + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String imgUrl = thirdManager.getThidFactory(this.getThirdMod(ThirdEnums.UPLOAD.getKey())).upload(file, fileName, CommonConstant.IMG);
			result.put("imgUrl", imgUrl);
			
			break;
		}
		
		return CommonResult.success(result);
	}
	
    @ApiOperation(value = "上传视频")
    @RequestMapping(value="/uploadVideo", method=RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String,Object>> uploadVideo(MultipartHttpServletRequest request) throws Exception {
		Map<String, MultipartFile> fileMap = request.getFileMap();
		if(fileMap.size() <= 0) {
			return CommonResult.success(null);
		}
		
		Map<String,Object> result = new HashMap<>(16);
		for(String field : fileMap.keySet()) {
			MultipartFile file = fileMap.get(field);
			if(file == null || file.getSize() <= 0 || StringUtils.isBlank(file.getOriginalFilename())) {
				continue;
			}
			
			long currentTime = System.currentTimeMillis();
			String fileName = currentTime + ".mp4";
			String imgUrl = thirdManager.getThidFactory(this.getThirdMod(ThirdEnums.UPLOAD.getKey())).upload(file, fileName, CommonConstant.VIDEO);
			result.put("videoUrl", imgUrl);
			
			break;
		}
		return CommonResult.success(result);
	}
    
    @ApiOperation(value = "发送验证码")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "phone", value = "手机"),
		@ApiImplicitParam(name = "sendType", value = "发送类型(1注册 2绑定 3忘记密码 4通用)"),
		@ApiImplicitParam(name = "cToken", value = "图形验证码TOKEN"),
		@ApiImplicitParam(name = "captcha", value = "图形验证码")
	})
    @ResponseBody
	@RequestMapping(value={"/sendCode"}, method=RequestMethod.POST)
	public CommonResult<String> sendCode(String phone, String sendType, String cToken, String captcha) throws Exception {
		if(StringUtils.isEmpty(sendType)) {
			sendType = "1";
		}
		
		if("1".equals(sendType)){
			//校验图形验证码
			super.validateCaptcha(cToken, captcha);

			//注册
			UmsMember phoneMember = memberService.getMemberByParams(phone, MemberTypes.PHONE);
			if(phoneMember != null){
				return CommonResult.failed("该手机号已注册，请重新填写手机号！");
			}
		}else if("3".equals(sendType)){
			//忘记密码
			UmsMember phoneMember = memberService.getMemberByParams(phone, MemberTypes.PHONE);
			if(phoneMember == null){
				return CommonResult.failed("该手机号不存在，请重新填写手机号！");
			}
		}
		
		Map<String,String> templateParam = new HashMap<>(16);
		verifyCodeService.sendSms(phone, sendType, templateParam);
		return CommonResult.success(null);
	}
    
    @ApiOperation(value = "校验验证码")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "phone", value = "手机"),
		@ApiImplicitParam(name = "code", value = "验证码")
	})
    @ResponseBody
	@RequestMapping(value={"/validateCode"}, method=RequestMethod.POST)
	public CommonResult<String> validateCode(String phone, String code) throws Exception {
		//校验短信验证码
    	verifyCodeService.validateCode(phone, code);
    	return CommonResult.success(null);
	}

	@ApiOperation(value = "物流选择器")
	@ResponseBody
	@RequestMapping(value="/deliveryType/getSelector", method=RequestMethod.GET)
	public CommonResult<List<KeyNameResult>> getSelector() {
		List<KeyNameResult> list = CollectionUtil.newArrayList();
		DeliveryEnums.DeliveryType[] types = DeliveryEnums.DeliveryType.values();
		for (DeliveryEnums.DeliveryType mtype : types) {
			list.add(new KeyNameResult(mtype.getName(), mtype.getKey()));
		}
		return CommonResult.success(list);
	}
}