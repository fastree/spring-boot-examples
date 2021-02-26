package com.fastree.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisApplicationTest {
    @Autowired
    private RedisTemplate redisTemplate;

    private void m2() {

    }
}
