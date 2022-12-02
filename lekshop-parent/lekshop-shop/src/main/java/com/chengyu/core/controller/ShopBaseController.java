package com.chengyu.core.controller;

import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopAccount;
import com.chengyu.core.model.WalkMember;
import com.chengyu.core.security.util.JwtTokenUtil;
import com.chengyu.core.service.member.MemberService;
import com.chengyu.core.service.shop.ShopAccountService;
import com.chengyu.core.service.shop.ShopService;
import com.chengyu.core.service.system.ThirdConfigService;
import com.chengyu.core.service.walk.WalkMemberService;
import com.chengyu.core.util.RedisUtil;
import com.chengyu.core.util.third.manager.ThirdManager;
import com.chengyu.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @title  基础Controller
 * @author LeGreen
 * @date   2021/1/8
 */
@Controller
public class ShopBaseController {

	@Autowired
	protected ShopAccountService shopAccountService;
	@Autowired
	protected MemberService memberService;
	@Autowired
	protected RedisUtil redisUtil;
	@Value("${jwt.tokenHeader}")
	protected String tokenHeader;
    @Value("${jwt.tokenHead}")
    protected String tokenHead;
    @Autowired
    protected JwtTokenUtil jwtTokenUtil;
	@Autowired
	protected ThirdManager thirdManager;
	@Autowired
	protected ThirdConfigService thirdConfigService;
	@Autowired
	protected ShopService shopService;
	@Autowired
	protected WalkMemberService walkMemberService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

	public UmsShopAccount getCurrentAccount() throws ServiceException {
		UmsShopAccount member = shopAccountService.getCurrentAccount();
		if(member.getStatus() != CommonConstant.SUS_INT){
			String msg = "账号已被冻结";
			if(StringUtils.isNotBlank(member.getFreezeReason())){
				msg = msg + ",冻结原因:"+member.getFreezeReason();
			}
			throw new ServiceException(CommonConstant.WARN_MSG, msg);
		}
		member.setPassword(null);
		member.setPayPassword(null);
		return member;
	}

	public UmsShopAccount getCurrentAccountOrNull(){
		return shopAccountService.getCurrentAccountOrNull();
	}

	public UmsMember getCurrentMember() throws ServiceException {
		return memberService.getMemberById(getCurrentMemberId());
	}

	public Integer getCurrentMemberId() throws ServiceException {
		return getCurrentAccount().getMemberId();
	}
	public UmsShop getCurrentShop() throws ServiceException {
		Integer memberId = getCurrentMemberId();
		UmsShop shop = (UmsShop) redisUtil.getRedisValue("SHOP-"+memberId);
		if(shop != null){
			return shop;
		}
		shop = shopService.getShopByMemberId(memberId);
		if(shop == null) {
			throw new ServiceException("商户不存在");
		}
		if(shop.getStatus() == CommonConstant.NO_INT) {
			throw new ServiceException("商户已被停止使用");
		}
		redisUtil.setRedisValue("SHOP-"+memberId, shop);
		return shop;
	}

	public Integer getCurrentShopId() throws ServiceException {
		return getCurrentShop().getId();
	}

	public WalkMember getCurrentWalkMember() throws ServiceException {
		Integer memberId = getCurrentMemberId();
		return walkMemberService.getWalkMemberByMember(memberId);
	}

	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public String getRequestIp(){
		HttpServletRequest request = this.getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null) {
			ip = request.getRemoteAddr();
		} else {
			String[] ips = ip.split(",");
			ip = ips[0];
		}
		
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}

	/**
	 * 校验重复
	 * @author LeGreen
	 * @date   2021/1/8
	 * @param  key
	 * @param  seconds
	 * @param  message
	 * @throws ServiceException 业务异常
	 */
	protected void validateRepeat(String key, Long seconds, String message) throws ServiceException {
		seconds = seconds == null ? 5000 : seconds;
		Object oBetTime = redisUtil.getRedisValue(key);
		if(oBetTime != null && (System.currentTimeMillis() - (Long) oBetTime) < seconds){
			throw new ServiceException(message);
		}
		redisUtil.setRedisValue(key, System.currentTimeMillis(), 1L, TimeUnit.HOURS);
	}

	/**
	 * 校验图形验证码
	 * @author LeGreen
	 * @date   2021/1/8
	 * @param  cToken
	 * @param  captcha
	 */
	protected void validateCaptcha(String cToken, String captcha) throws ServiceException {
		if(StringUtils.isBlank(cToken) || StringUtils.isBlank(captcha)){
			throw new ServiceException("请输入图形验证码");
		}
		String cacheCaptcha = (String) redisUtil.getRedisValue(cToken);
		if(StringUtils.isBlank(cacheCaptcha)) {
			throw new ServiceException("图形验证码已过期");
		}
		if(!cacheCaptcha.equals(captcha)){
			throw new ServiceException("图形验证码错误");
		}
	}

	public String getUrl(){
		String url = this.getRequest().getHeader("Origin");
		if(StringUtils.isBlank(url)){
			return null;
		}

		return url.replaceAll("http://", "")
				.replaceAll("https://", "")
				.replaceAll("/", "");
	}

	public String getThirdMod(String nid){
		return thirdConfigService.getThirdConfigByNid(nid);
	}
}