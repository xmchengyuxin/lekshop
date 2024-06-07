package com.chengyu.core.config;

import com.chengyu.core.service.shop.ShopAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class ShopSecurityConfig {

    @Autowired
    private ShopAccountService shopAccountService;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> shopAccountService.loadUserByUsername(username);
    }
}
