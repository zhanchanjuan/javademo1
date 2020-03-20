package com.javademo.common.basic.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by 13375 on 2020/3/17.
 */
public class redisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setValue(Object key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }
    public void setValue(Object key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public Object getValue(Object key) {
        Object value = redisTemplate.opsForValue().get(key);
        return value;
    }

    public static void main(String[] args) {
        redisTest redisTest=new redisTest();
        redisTest.setValue(123,"第一条redis信息");
//        redisTest.getValue(123);
    }

}
