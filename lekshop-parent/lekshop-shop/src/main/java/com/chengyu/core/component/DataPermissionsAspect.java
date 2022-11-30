package com.chengyu.core.component;

import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.exception.ServiceException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(4)
public class DataPermissionsAspect extends ShopBaseController {

    @Pointcut("@annotation(com.chengyu.core.component.DataPermissions)")
    private void dataPermissions() {
    }

    @Before("dataPermissions()")
    public void doBefore(JoinPoint joinPoint) throws ServiceException {
        System.out.println("请求数据权限>>>>>>>>>>>>>>>>>>>>>>");
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(DataPermissions.class)) {
            //获取数据权限
            DataPermissions dataPermissions = method.getAnnotation(DataPermissions.class);
            DataPermissionsResult result = new DataPermissionsResult();
            result.setIsAdmin(false);
            result.setShopId(getCurrentShopId());
            result.setQueryShopIdField(dataPermissions.shopIdField());
            // 将获取到的数据存到thread-local中，后面mybatis拦截器中获取数据
            QueryInterceptorRegistry.setQueryInterceptor(result);
        }
    }

    @AfterReturning(value = "dataPermissions()", returning = "ret")
    public void doAfterReturning(Object ret) {
    }

    @Around("dataPermissions()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }

}
