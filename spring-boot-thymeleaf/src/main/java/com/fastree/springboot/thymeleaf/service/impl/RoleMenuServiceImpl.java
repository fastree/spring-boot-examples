package com.fastree.springboot.thymeleaf.service.impl;

import com.fastree.springboot.thymeleaf.entity.RoleMenuEntity;
import com.fastree.springboot.thymeleaf.mapper.RoleMenuMapper;
import com.fastree.springboot.thymeleaf.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与菜单的关联实体 服务实现类
 * </p>
 *
 * @author Alex Jiang
 * @since 2021-01-15
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenuEntity> implements RoleMenuService {

}
