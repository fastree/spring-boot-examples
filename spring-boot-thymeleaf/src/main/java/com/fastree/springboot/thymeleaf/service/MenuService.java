package com.fastree.springboot.thymeleaf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fastree.springboot.thymeleaf.entity.MenuEntity;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单实体 服务类
 * </p>
 *
 * @author Alex Jiang
 * @since 2021-01-15
 */
public interface MenuService extends IService<MenuEntity> {

    Set<MenuEntity> getUserMenuSet(Long userId);

    List<MenuEntity> getUserMenuTree(Long userId);
}
