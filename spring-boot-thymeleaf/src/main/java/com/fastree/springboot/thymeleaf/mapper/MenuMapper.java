package com.fastree.springboot.thymeleaf.mapper;

import com.fastree.springboot.thymeleaf.entity.MenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 菜单实体 Mapper 接口
 * </p>
 *
 * @author Alex Jiang
 * @since 2021-01-15
 */
@Repository
public interface MenuMapper extends BaseMapper<MenuEntity> {

}
