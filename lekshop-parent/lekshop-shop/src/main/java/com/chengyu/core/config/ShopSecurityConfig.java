package com.chengyu.core.config;

import com.chengyu.core.security.config.SecurityConfig;
import com.chengyu.core.service.shop.ShopAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ShopSecurityConfig extends SecurityConfig {

    @Autowired
    private ShopAccountService shopAccountService;

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> shopAccountService.loadUserByUsername(username);
    }
}
