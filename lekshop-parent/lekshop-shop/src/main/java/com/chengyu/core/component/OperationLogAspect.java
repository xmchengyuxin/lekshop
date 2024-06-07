package com.chengyu.core.component;

import cn.hutool.core.map.MapUtil;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.model.UmsShopAccount;
import com.chengyu.core.service.shop.ShopAccountOperationLogService;
import com.chengyu.core.utils.JsonUtils;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一日志处理切面
 */
@Aspect
@Component
@Order(3)
public class OperationLogAspect extends ShopBaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OperationLogAspect.class);
    @Autowired
	private ShopAccountOperationLogService operationLogService;
    
    @Pointcut("@annotation(com.chengyu.core.component.OperationLog)")
    private void operationLog() {
    }

    @Before("operationLog()")
    public void doBefore(JoinPoint joinPoint) {
    }

    @AfterReturning(value = "operationLog()", returning = "ret")
    public void doAfterReturning(Object ret) {
    }

    @SuppressWarnings("unchecked")
	@Around("operationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        UmsShopAccount member = this.getCurrentAccountOrNull();
        if(member == null) {
            return result;
        }
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        String remark = null;
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation log = method.getAnnotation(ApiOperation.class);
            remark = log.value();
        }
        String ip = this.getRequestIp();
        List<Map<String,Object>> list = (List<Map<String, Object>>) getParameter(method, joinPoint.getArgs());
        if(remark.contains(CommonConstant.JING_REGEX) && !CollectionUtils.isEmpty(list)){
            for(Map<String,Object> map : list){
                for(String key : map.keySet()){
                    remark = remark.replaceAll(CommonConstant.JING_REGEX+key+CommonConstant.JING_REGEX, "{"+MapUtil.getStr(map, key)+"}");
                }
            }
        }
        operationLogService.addOperationLog(member, ip, remark,
                !CollectionUtils.isEmpty(list) ? JsonUtils.object2JsonString(getParameter(method, joinPoint.getArgs())) : null);
        return result;

    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
            
            //将无注解的参数作为请求参数
            Map<String, Object> map = new HashMap<>();
            String key = parameters[i].getName();
            map.put(key, args[i]);
            argList.add(map);
        }
        if (argList.size() == 0) {
            return null;
        } else {
            return argList;
        }
    }
}
