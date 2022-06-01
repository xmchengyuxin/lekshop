package com.chengyu.core;

import com.chengyu.core.i18n.MyLocaleResolverConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;

@SpringBootApplication
public class PortalApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PortalApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolverConfig();
    }
}
