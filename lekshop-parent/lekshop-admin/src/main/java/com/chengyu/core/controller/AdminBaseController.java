package com.chengyu.core.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysAdmin;
import com.chengyu.core.security.util.JwtTokenUtil;
import com.chengyu.core.util.RedisUtil;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class AdminBaseController {

	@Autowired
	protected RedisUtil redisUtil;
	@Value("${jwt.tokenHeader}")
    protected String tokenHeader;
	@Value("${jwt.tokenHead}")
	protected String tokenHead;
	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	public void setSessionAdmin(SysAdmin admin) {
		String token = this.getRequest().getHeader(tokenHeader);
		redisUtil.setRedisValue(token, admin);
	}
	
	public SysAdmin getSessionAdmin() throws ServiceException{
		String token = this.getRequest().getHeader(tokenHeader);
		if(StringUtils.isBlank(token)){
			throw new ServiceException("LOGIN_EXPIRED");
		}
		SysAdmin admin = (SysAdmin) redisUtil.getRedisValue(token);
		if(admin == null){
			throw new ServiceException("LOGIN_EXPIRED");
		}
		return admin;
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
	 * @date   2023/1/5
	 * @param  key 业务KEY
	 * @param  message 错误信息
	 * @param  timeout 超时时间
	 * @throws ServiceException 业务异常
	 */
	protected void validateRepeat(String key, String message, Long timeout) throws ServiceException {
		Object oBetTime = redisUtil.getRedisValue(key);
		if(oBetTime != null && (System.currentTimeMillis() - (Long) oBetTime) < timeout){
			throw new ServiceException(message);
		}
		redisUtil.setRedisValue(key, System.currentTimeMillis(), 1L, TimeUnit.HOURS);
	}


	protected List<Integer> getIdList(String ids){
		List<String> idStrList = Arrays.asList(ids.split(CommonConstant.DH_REGEX));
		return Convert.convert(new TypeReference<List<Integer>>() {}, idStrList);
	}
}