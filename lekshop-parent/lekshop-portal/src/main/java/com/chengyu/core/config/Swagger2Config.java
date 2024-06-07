package com.chengyu.core.config;

import com.chengyu.core.domain.SwaggerProperties;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger2API文档的配置
 * Created by LeGreen on 2018/4/26.
 */
@Configuration
@EnableSwagger2
//@EnableKnife4j
public class Swagger2Config extends BaseSwaggerConfig{

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.chengyu.core.controller")
                .title("用户端")
                .description("用户端相关接口文档")
                .contactName("LeGreen")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }

    @Bean
    public BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return generateBeanPostProcessor();
    }
}
