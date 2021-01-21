package com.fastree.springboot.thymeleaf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastree.springboot.thymeleaf.entity.MenuEntity;
import com.fastree.springboot.thymeleaf.entity.RoleMenuEntity;
import com.fastree.springboot.thymeleaf.entity.UserRoleEntity;
import com.fastree.springboot.thymeleaf.mapper.MenuMapper;
import com.fastree.springboot.thymeleaf.service.MenuService;
import com.fastree.springboot.thymeleaf.service.RoleMenuService;
import com.fastree.springboot.thymeleaf.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单实体 服务实现类
 * </p>
 *
 * @author Alex Jiang
 * @since 2021-01-15
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuService menuService;

    @Override
    public Set<MenuEntity> getUserMenuSet(Long userId) {
        Set<Long> roleIds = null;
        Set<Long> menuIds = null;
        Set<MenuEntity> menuEntitySet = null;

        // 查询用户的所有角色
        if (Objects.nonNull(userId)) {
            LambdaQueryWrapper<UserRoleEntity> userRoleQuery = Wrappers.lambdaQuery();
            userRoleQuery.eq(UserRoleEntity::getUserId, userId);
            userRoleQuery.select(UserRoleEntity::getRoleId);
            roleIds = userRoleService.list(userRoleQuery)
                    .stream()
                    .map(UserRoleEntity::getRoleId)
                    .collect(Collectors.toSet());
        }

        // 查询角色对应的菜单
        if (Objects.nonNull(roleIds)) {
            menuIds = roleIds.stream()
                    .map(roleId -> {
                        LambdaQueryWrapper<RoleMenuEntity> roleMenuQuery = Wrappers.lambdaQuery();
                        roleMenuQuery.eq(RoleMenuEntity::getRoleId, roleId);
                        roleMenuQuery.select(RoleMenuEntity::getMenuId);
                        return roleMenuService.list(roleMenuQuery)
                                .stream()
                                .map(RoleMenuEntity::getMenuId)
                                .collect(Collectors.toSet());
                    }).flatMap(Set::stream)
                    .collect(Collectors.toSet());
        }

        // 查询用户的所有菜单
        if (Objects.nonNull(menuIds)) {
            menuEntitySet = menuIds.stream()
                    .map(menuId -> {
                        LambdaQueryWrapper<MenuEntity> menuQuery = Wrappers.lambdaQuery();
                        menuQuery.eq(MenuEntity::getMenuId, menuId);
                        return menuService.getOne(menuQuery);
                    }).collect(Collectors.toSet());
        }

        return menuEntitySet;
    }

    @Override
    public List<MenuEntity> getUserMenuTree(Long userId) {
        Set<MenuEntity> userMenuSet = getUserMenuSet(userId);

        // 获取当前菜单的父菜单
        Set<Long> parentIds = userMenuSet.stream()
                .map(MenuEntity::getParentId)
                .collect(Collectors.toSet());
        Set<MenuEntity> parentMenuSet = parentIds.stream()
                .map(menuId -> {
                    LambdaQueryWrapper<MenuEntity> menuQuery = Wrappers.lambdaQuery();
                    menuQuery.eq(MenuEntity::getMenuId, menuId);
                    return menuService.getOne(menuQuery);
                }).collect(Collectors.toSet());

        // 合并所有的菜单
        userMenuSet.addAll(parentMenuSet);

        // 只保留目录和菜单
        Set<MenuEntity> filterUserMenu = userMenuSet.stream()
                .filter(userMenu -> userMenu.getMenuType() < 2)
                .collect(Collectors.toSet());

        // 从根开始组装菜单树
        List<MenuEntity> userMenuTree = userMenuSet.stream()
                .filter(menu -> menu.getParentId() == 0)
                .map(menu -> {
                    menu.setChildren(getChildrenMenu(menu, filterUserMenu));
                    return menu;
                })
                .sorted(Comparator.comparingInt(MenuEntity::getMenuSort))
                .collect(Collectors.toList());

        return userMenuTree;
    }

    /**
     * 递归查找并设置子菜单
     *
     * @param rootMenu
     * @param userMenuSet
     * @return
     */
    private List<MenuEntity> getChildrenMenu(MenuEntity rootMenu, Set<MenuEntity> userMenuSet) {
        List<MenuEntity> menuEntities = userMenuSet.stream()
                .filter(userMenu -> userMenu.getParentId() == rootMenu.getMenuId())
                .map(currentMenu -> {
                    currentMenu.setChildren(getChildrenMenu(currentMenu, userMenuSet));
                    return currentMenu;
                })
                .sorted(Comparator.comparingInt(MenuEntity::getMenuSort))
                .collect(Collectors.toList());
        return menuEntities;
    }
}
