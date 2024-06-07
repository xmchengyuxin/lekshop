package com.chengyu.core.config;

import com.chengyu.core.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class PortalSecurityConfig {

    @Autowired
    private MemberService memberService;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> memberService.loadUserByUsername(username);
    }
}
