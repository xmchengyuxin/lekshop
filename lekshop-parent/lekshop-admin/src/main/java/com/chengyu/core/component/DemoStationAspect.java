package com.chengyu.core.component;

import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.service.system.ConfigService;
import com.chengyu.core.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @title  演示站禁止某些功能
 * @author LeGreen
 * @date   2023/4/24
 */
@Aspect
@Component
@Order(1)
public class DemoStationAspect extends AdminBaseController {

    @Autowired
	private ConfigService configService;
    
    @Pointcut("@annotation(com.chengyu.core.component.DemoStationForbid)")
    private void demoStationForbid() {
    }

    @Before("demoStationForbid()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        String forbidStatus = configService.getValueByNid("demo_station");
        String message = "演示站禁止了此功能";
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(DemoStationForbid.class)) {
            DemoStationForbid log = method.getAnnotation(DemoStationForbid.class);
            if(StringUtils.isNotBlank(log.remark())) {
                message = log.remark();
            }
        }
        if(StringUtils.isNotBlank(forbidStatus) && CommonConstant.YES.equals(forbidStatus)) {
            throw new ServiceException(message);
        }
    }

    @AfterReturning(value = "demoStationForbid()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
    }

    @Around("demoStationForbid()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        /*Object result = joinPoint.proceed();
        String forbidStatus = configService.getValueByNid("demo_station");
        String message = "演示站禁止了此功能";
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(DemoStationForbid.class)) {
            DemoStationForbid log = method.getAnnotation(DemoStationForbid.class);
            if(StringUtils.isNotBlank(log.remark())) {
                message = log.remark();
            }
        }
        if(StringUtils.isNotBlank(forbidStatus) && CommonConstant.YES.equals(forbidStatus)) {
            throw new ServiceException(message);
        }
        return result;*/
        return joinPoint.proceed();
    }

}
