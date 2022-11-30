package com.chengyu.core.component;

import com.chengyu.core.utils.SqlParser;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.jetbrains.annotations.NotNull;

import java.util.Properties;

@Log4j2
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class }) })
public class DataPermissionInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取用户自定义权限数据。
        DataPermissionsResult permissionsResult = QueryInterceptorRegistry.getQueryInterceptor();
        QueryInterceptorRegistry.clear();
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameterObject = args[1];

        // sql语句类型 select、delete、insert、update
        String sqlCommandType = ms.getSqlCommandType().toString();

        // 仅拦截 select 查询 或者 传入的参数为空
        if (!sqlCommandType.equals(SqlCommandType.SELECT.toString()) || permissionsResult == null || permissionsResult.getIsAdmin()) {
            return invocation.proceed();
        }
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        String origSql = boundSql.getSql();
        log.info("======>原始SQL:{}", origSql);
        String newSql = "";

        // 这个方法主要是用来解析原来的SQL， 然后再将现有的数据权限拼装成SQL语句添加上，返回一个新SQL。
        if (!checkContainField(origSql, new String[]{permissionsResult.getQueryShopIdField()})){
            //都没有包含，则直接返回
            return invocation.proceed();
        }
        StringBuffer sb = generatePrivilegeSql(permissionsResult.getShopId(), permissionsResult.getQueryShopIdField());
        newSql = SqlParser.handlerSql(origSql, sb.toString());
        log.info("======>改写的SQL:{}", newSql);
        // 重新new一个查询语句对象
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), newSql, boundSql.getParameterMappings(),
                boundSql.getParameterObject());
        // 把新的查询放到statement里
        MappedStatement newMs = newMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        // MappedStatement维护了⼀条<select|update|delete|insert>节点的封装
        Object[] queryArgs = invocation.getArgs();
        queryArgs[0] = newMs;
        return invocation.proceed();
    }

    @NotNull
    private StringBuffer generatePrivilegeSql(Integer shopId, String queryShopIdField) {
        StringBuffer sb = new StringBuffer();//格式：(a = 1 or b = 1 or c = 1) and
        sb.append(" ( ");
        sb.append(queryShopIdField).append(" = ").append(shopId);
        sb.append(" ) ");//对应起点的括号
        sb.append(" and ");
        return sb;
    }

    //验证 原始的sql，是否包含定义的字段，比如member_id，如果没有，则返回false；有，返回true
    private boolean checkContainField(String origSql, String[] queryFieldArray) {
        boolean containFlag = false;
        for (String field : queryFieldArray) {
            if(origSql.contains(field)){
                containFlag = true;
                break;
            }
        }
        return containFlag;
    }

    static class BoundSqlSqlSource implements SqlSource {

        private final BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }

    }

    private MappedStatement newMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource,
                ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
            builder.keyProperty(ms.getKeyProperties()[0]);
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
