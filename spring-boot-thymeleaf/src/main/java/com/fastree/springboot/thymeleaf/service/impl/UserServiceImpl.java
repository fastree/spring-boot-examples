package com.fastree.springboot.thymeleaf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastree.springboot.thymeleaf.entity.UserEntity;
import com.fastree.springboot.thymeleaf.mapper.UserMapper;
import com.fastree.springboot.thymeleaf.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户实体 服务实现类
 * </p>
 *
 * @author Alex Jiang
 * @since 2021-01-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public Long getUserIdByName(String name) {
        LambdaQueryWrapper<UserEntity> userQuery = Wrappers.lambdaQuery();
        userQuery.eq(UserEntity::getUserName, name);
        userQuery.select(UserEntity::getUserId);

        return baseMapper.selectOne(userQuery).getUserId();
    }
}
