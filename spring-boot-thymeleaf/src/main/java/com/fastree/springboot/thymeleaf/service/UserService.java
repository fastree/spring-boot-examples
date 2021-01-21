package com.fastree.springboot.thymeleaf.service;

import com.fastree.springboot.thymeleaf.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户实体 服务类
 * </p>
 *
 * @author Alex Jiang
 * @since 2021-01-15
 */
public interface UserService extends IService<UserEntity> {

    Long getUserIdByName(String name);
}
