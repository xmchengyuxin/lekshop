package com.chengyu.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @title  redis工具类
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class RedisUtil {
	
	private static final String COMMON_KEY = "scaffold-";
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	/**
	 * 设置缓存过期时间
	 * @param key 缓存Key
	 * @param timeout 过期时间
	 * @param unit 单位(默认为秒-TimeUnit.SECONDS)
	 */
	public void expire(String key, long timeout, TimeUnit unit) {
		key = COMMON_KEY+key;
		redisTemplate.expire(key, timeout, unit == null ? TimeUnit.SECONDS : unit);
	}

	/**
	 * 设置缓存
	 * @param key 缓存Key
	 * @param value 缓存值
	 */
	public void setRedisValue(String key, Object value) {
		setRedisValue(key, value, null, null);
	}
	
	/**
	 * 设置缓存
	 * @param key 缓存Key
	 * @param value 缓存值
	 * @param timeout 过期时间
	 * @param unit 单位(默认为秒-TimeUnit.SECONDS)
	 */
	public void setRedisValue(String key, Object value, Long timeout, TimeUnit unit) {
		key = COMMON_KEY+key;
		if(timeout == null || timeout <= 0) {
			redisTemplate.opsForValue().set(key, value);
		} else {
			redisTemplate.opsForValue().set(key, value, timeout, unit == null ? TimeUnit.SECONDS : unit);
		}
	}

	/**
	 * 获取对象缓存(String)
	 * @param key 缓存Key
	 * @return
	 */
	public Object getRedisValue(String key) {
		key = COMMON_KEY+key;
		return redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * 删除缓存
	 * @param keys 要删除的缓存keys
	 */
	public void delete(String ... keys) {
		if(keys == null || keys.length <= 0) {
			return;
		}
		
		List<String> keyList = new ArrayList<>();
		for(String key : keys) {
			key = COMMON_KEY+key;
			keyList.add(key);
		}
		redisTemplate.delete(keyList);
	}
	
	/**
	 * 删除缓存
	 * @param keys 要删除的缓存keys
	 */
	public void deletePattern(String ... keys) {
		if(keys == null || keys.length <= 0) {
			return;
		}
		
		for(String key : keys) {
			key = COMMON_KEY+key;
			redisTemplate.delete(redisTemplate.keys(key+"*"));
		}
	}
}