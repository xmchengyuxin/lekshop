package com.chengyu.core.config;

import com.chengyu.core.component.DataPermissionInterceptor;
import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@AutoConfigureAfter(PageHelperAutoConfiguration.class)
public class MybatisInterceptorConfig {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @PostConstruct
    public void addMyInterceptor() {
        DataPermissionInterceptor dataPermissionInterceptor = new DataPermissionInterceptor();
//        ParamInterceptor paramInterceptor = new ParamInterceptor();
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
            configuration.addInterceptor(dataPermissionInterceptor);
//            configuration.addInterceptor(paramInterceptor);
        }
    }

}
