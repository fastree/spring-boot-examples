package com.fastree.springboot.redis.service.impl;

import com.fastree.springboot.redis.dao.UserRepository;
import com.fastree.springboot.redis.entity.UserEntity;
import com.fastree.springboot.redis.service.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final StringRedisTemplate redisTemplate;
    private final UserRepository userRepository;

    public UserServiceImpl(StringRedisTemplate redisTemplate, UserRepository userRepository) {
        this.redisTemplate = redisTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public List<Object> testRedisTransaction() {
        return redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                /*提交事务之前，会先检查watch的key的值的变化*/
                operations.watch("tx");
                operations.multi();
                operations.opsForValue().set("k1", "v1");
//                operations.opsForValue().increment("k1", 1); /*redis异常产生，仍执行后续命令队列*/
                operations.opsForValue().set("k2", "v2");
//                int i = 1 / 0;  /*非redis异常产生，不会进行事务的提交*/
                operations.opsForValue().set("k3", "v3");
                return operations.exec();
            }
        });
    }

    @Override
    public List<Object> testRedisPipeline() {
        return redisTemplate.executePipelined((RedisConnection connection) -> {
            for (int i = 1; i <= 100000; i++) {
                connection.set((i + "").getBytes(), (i + "").getBytes());
            }
            return null;
        });
    }

    @Override
    public void testRedisPubSub(String message) {
        redisTemplate.convertAndSend("topic1", message);
    }

    @Override
    public UserEntity testRedisRepository() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1001l);
        userEntity.setName("Tim");
        userEntity.setAge(22);
        userEntity.setTime(LocalDateTime.now());
        userEntity.setExpire(600l);
        return userRepository.save(userEntity);
    }

}
