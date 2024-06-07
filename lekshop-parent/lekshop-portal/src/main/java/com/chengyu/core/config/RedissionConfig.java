package com.chengyu.core.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RedissionConfig {

    private final String REDISSON_PREFIX = "redis://";
    private final RedisProperties redisProperties;

    public RedissionConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        String url = REDISSON_PREFIX + redisProperties.getHost() + ":" + redisProperties.getPort();
        config.useSingleServer()
                .setAddress(url)
//                .setPassword(redisProperties.getPassword())
                .setDatabase(redisProperties.getDatabase())
                .setPingConnectionInterval(2000);
        config.setLockWatchdogTimeout(10000L);

        try {
            return Redisson.create(config);
        } catch (Exception e) {
            log.error("RedissonClient init redis url:{}, Exception:{}", url, e);
            return null;
        }
    }
}
