package com.fastree.springboot.thymeleaf.mapper;

import com.fastree.springboot.thymeleaf.entity.RoleMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 角色与菜单的关联实体 Mapper 接口
 * </p>
 *
 * @author Alex Jiang
 * @since 2021-01-15
 */
@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenuEntity> {

}
