package com.senqicloud.senqipicserver.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 写入缓存（无过期时间）
     */
    public void set(String key, Object value) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value);
    }

    /**
     * 写入缓存（指定过期时间，单位秒）
     */
    public void set(String key, Object value, long timeoutSeconds) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value, timeoutSeconds, TimeUnit.SECONDS);
    }

    /**
     * 读取缓存
     */
    public Object get(String key) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }

    /**
     * 删除缓存
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 判断key是否存在
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置key过期时间，单位秒
     */
    public Boolean expire(String key, long timeoutSeconds) {
        return redisTemplate.expire(key, timeoutSeconds, TimeUnit.SECONDS);
    }
}
