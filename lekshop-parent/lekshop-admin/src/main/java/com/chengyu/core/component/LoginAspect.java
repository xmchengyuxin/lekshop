package com.chengyu.core.component;

import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.security.config.IgnoreUrlsConfig;
import com.chengyu.core.util.RedisUtil;
import com.chengyu.core.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @title  实现单设备登录
 * @author LeGreen
 * @date   2023/1/3
 */
@Aspect
@Component
@Order(1)
public class LoginAspect extends AdminBaseController {
	
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Pointcut("execution(public * com.chengyu.core.controller..*.*(..))")
    public void login() {
    }

    @Before("login()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    @AfterReturning(value = "login()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
    }

    @Around("login()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes == null) {
            return joinPoint.proceed();
        }
        
        HttpServletRequest request = attributes.getRequest();
        for (String url : ignoreUrlsConfig.getUrls()) {
            if(isMatch(request.getServletPath(), url)) {
                return joinPoint.proceed();
            }
        }

        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            
            String token = (String) redisUtil.getRedisValue(RedisEnums.ADMIN_TOKEN_KEY.getKey()+username);
            if(StringUtils.isBlank(token)) {
                return CommonResult.unauthorized("您的登录信息已过期,请重新登录");
            }
            if(!token.trim().equals(authToken.trim())) {
            	return CommonResult.unauthorized("您的账号已在其他的设备登录,请重新登录");
            }
        }
        return joinPoint.proceed();
    }

    private boolean isMatch(String url, String urlPath) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(url, urlPath);
    }

}
