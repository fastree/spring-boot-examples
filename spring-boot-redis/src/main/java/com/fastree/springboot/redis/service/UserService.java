package com.fastree.springboot.redis.service;


import com.fastree.springboot.redis.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<Object> testRedisTransaction();
    List<Object> testRedisPipeline();
    void testRedisPubSub(String message);
    UserEntity testRedisRepository();

}
