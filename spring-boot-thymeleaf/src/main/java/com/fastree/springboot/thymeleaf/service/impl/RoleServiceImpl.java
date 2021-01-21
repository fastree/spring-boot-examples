package com.fastree.springboot.thymeleaf.service.impl;

import com.fastree.springboot.thymeleaf.entity.RoleEntity;
import com.fastree.springboot.thymeleaf.mapper.RoleMapper;
import com.fastree.springboot.thymeleaf.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色实体 服务实现类
 * </p>
 *
 * @author Alex Jiang
 * @since 2021-01-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

}
