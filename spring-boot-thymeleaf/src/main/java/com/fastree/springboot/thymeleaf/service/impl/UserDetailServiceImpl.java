package com.fastree.springboot.thymeleaf.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fastree.springboot.thymeleaf.entity.MenuEntity;
import com.fastree.springboot.thymeleaf.entity.SecurityUser;
import com.fastree.springboot.thymeleaf.entity.UserEntity;
import com.fastree.springboot.thymeleaf.service.MenuService;
import com.fastree.springboot.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名查询用户
        LambdaQueryWrapper<UserEntity> userQuery = Wrappers.lambdaQuery();
        userQuery.eq(UserEntity::getUserName, username);
        UserEntity userEntity = userService.getOne(userQuery);
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 获得该用户的所有权限
        Set<SimpleGrantedAuthority> authoritySet = menuService.getUserMenuSet(userEntity.getUserId())
                .stream()
                .map(MenuEntity::getPermissions)
                .filter(StrUtil::isNotBlank)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        // 聚合安全用户实体
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUserEntity(userEntity);
        securityUser.setAuthorities(authoritySet);

        return securityUser;
    }
}
