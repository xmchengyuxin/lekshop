package com.chengyu.core.service.kaptcha;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chengyu.core.util.RedisUtil;

@Service
public class CaptchaService {

	@Autowired
	private RedisUtil redisUtil;
 
    public Map<String,Object> createToken(String captcha){
        //生成一个token
        String cToken = UUID.randomUUID().toString();
 
        //生成验证码对应的token  以token为key  验证码为value存在redis中
        redisUtil.setRedisValue(cToken, captcha, 5L, TimeUnit.MINUTES);
        
        Map<String, Object> map = new HashMap<>();
        map.put("cToken", cToken);
        map.put("expire", 5L);
        return map;
    }
}
