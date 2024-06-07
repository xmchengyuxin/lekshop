package com.chengyu.core.component;

/**
 * @title  存储数据权限
 * @author LeGreen
 * @date   2022/7/11
 */
public class QueryInterceptorRegistry {

    private static ThreadLocal<DataPermissionsResult> queryInterceptor = new ThreadLocal<>();

    public static DataPermissionsResult getQueryInterceptor() {
        return queryInterceptor.get();
    }

    public static void setQueryInterceptor(DataPermissionsResult queryInterceptor) {
        QueryInterceptorRegistry.queryInterceptor.set(queryInterceptor);
    }

    public static void clear() {
        queryInterceptor.remove();
    }
}
