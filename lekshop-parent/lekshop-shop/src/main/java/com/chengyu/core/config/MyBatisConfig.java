package com.chengyu.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"com.chengyu.core.mapper","com.chengyu.core.dao"})
public class MyBatisConfig {
}
