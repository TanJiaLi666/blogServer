package com.tanjiali.blogpublicapi.service.impl;

import com.tanjiali.blogpublicapi.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/14 15:11
 * @Version 1.0
 **/
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String getString(String key) {
        String string = (String)redisTemplate.opsForValue().get(key);
        return string;
    }
    @Override
    public void setString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
}
