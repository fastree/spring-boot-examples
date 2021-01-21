package com.fastree.springboot.thymeleaf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastree.springboot.thymeleaf.entity.UserRoleEntity;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户与角色的关联实体 Mapper 接口
 * </p>
 *
 * @author Alex Jiang
 * @since 2021-01-15
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

}
