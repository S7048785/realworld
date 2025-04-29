package com.realworld.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CacheUtil {

	@Autowired
	private StringRedisTemplate redisTemplate;

	public void setStr(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public void setStr(String key, String value, long timeout) {
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}

	public String getStr(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public void setHash(String key, String hashKey, String value) {
		redisTemplate.opsForHash().put(key, hashKey, value);
	}

	public String getHash(String key, String hashKey) {
		return (String) redisTemplate.opsForHash().get(key, hashKey);
	}
}
