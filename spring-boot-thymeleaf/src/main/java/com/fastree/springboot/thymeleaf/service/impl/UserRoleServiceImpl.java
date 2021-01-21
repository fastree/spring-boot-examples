package com.fastree.springboot.thymeleaf.service.impl;

import com.fastree.springboot.thymeleaf.entity.UserRoleEntity;
import com.fastree.springboot.thymeleaf.mapper.UserRoleMapper;
import com.fastree.springboot.thymeleaf.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色的关联实体 服务实现类
 * </p>
 *
 * @author Alex Jiang
 * @since 2021-01-15
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {

}
