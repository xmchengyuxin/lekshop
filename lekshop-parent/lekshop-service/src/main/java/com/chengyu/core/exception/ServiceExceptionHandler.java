package com.chengyu.core.exception;

import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @title  全局异常捕捉类
 * @author LeGreen
 * @date   2021/1/5
 */
@RestControllerAdvice
public class ServiceExceptionHandler{

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionHandler.class);
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e){
		if(e instanceof ServiceException){
			if(!StringUtils.isEmpty(e.getMessage())){
				if(CommonConstant.LOGIN_EXPIRED.equals(e.getMessage())){
					return CommonResult.unauthorized(null);
				}else if(e.getMessage().contains(CommonConstant.WARN_MSG)){
					return CommonResult.weakMessage(e.getMessage().replace(CommonConstant.WARN_MSG, ""));
				}else{
					return CommonResult.failed(e.getMessage());
				}
			}
		}else if(e instanceof UsernameNotFoundException){
			LOGGER.info("全局异常捕获>>找不到用户名");
			return CommonResult.failed("用户名密码错误");
		}
		LOGGER.error(e.getMessage(),e);

		return CommonResult.failed("网络开小差~");
    }
}
