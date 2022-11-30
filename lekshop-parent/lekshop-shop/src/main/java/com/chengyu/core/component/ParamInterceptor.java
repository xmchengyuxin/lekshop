package com.chengyu.core.component;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.model.UmsShopAccount;
import com.chengyu.core.service.shop.ShopAccountService;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author LeGreen
 * @title 自动插入创建人, 创建时间, 更新人, 更新时间
 * @date 2022/7/11
 */
@Component
@Log4j2
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class ParamInterceptor implements Interceptor{

    @Autowired
    private ShopAccountService shopAccountService;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        log.info("------sqlId------" + mappedStatement.getId());

        // sql类型：insert、update、select、delete
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        log.info("------sqlCommandType------" + sqlCommandType);

        if (parameter == null) {
            return invocation.proceed();
        }

        // 当sql为新增或更新类型时，自动填充操作人相关信息
        if (SqlCommandType.INSERT == sqlCommandType) {
            // 获取当前登录用户信息
            UmsShopAccount member = getShopAccount();

            Field[] fields = getAllFields(parameter);
            for (Field field : fields) {
                try {
                    // 注入创建人
                    if ("addBy".equals(field.getName()) && member != null) {
                        field.setAccessible(true);
                        field.set(parameter, member.getRealname());
                        field.setAccessible(false);
                    }
                    //注入创建时间
                    if ("addTime".equals(field.getName())) {
                        field.setAccessible(true);
                        field.set(parameter, DateUtil.date());
                        field.setAccessible(false);
                    }
                    if ("shopId".equals(field.getName()) && member != null) {
                        field.setAccessible(true);
                        field.set(parameter, member.getShopId());
                        field.setAccessible(false);
                    }
                    if ("shopName".equals(field.getName()) && member != null) {
                        field.setAccessible(true);
                        field.set(parameter, member.getShopName());
                        field.setAccessible(false);
                    }
                } catch (Exception e) {
                    log.error("failed to insert data, exception = ", e);
                }
            }
        }
        if (SqlCommandType.UPDATE == sqlCommandType) {
            // 获取当前登录用户信息
            UmsShopAccount member = getShopAccount();

            Field[] fields = getAllFields(parameter);
            for (Field field : fields) {
                try {
                    if ("updBy".equals(field.getName())  && member != null) {
                        field.setAccessible(true);
                        field.set(parameter, member.getRealname());
                        field.setAccessible(false);
                    }
                    if ("updTime".equals(field.getName())) {
                        field.setAccessible(true);
                        field.set(parameter, DateUtil.date());
                        field.setAccessible(false);
                    }
                } catch (Exception e) {
                    log.error("failed to update data, exception = ", e);
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private UmsShopAccount getShopAccount(){
        try {
            return shopAccountService.getCurrentAccountOrNull();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取类的所有属性，包括父类
     */
    private Field[] getAllFields(Object object) {
        Class<?> clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

}
