package com.fastree.springboot.thymeleaf.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色实体
 * </p>
 *
 * @author Alex Jiang
 * @since 2021-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_role")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色标题
     */
    private String roleTitle;

    /**
     * 逻辑删除字段
     */
    private Boolean deleted;

    /**
     * 乐观锁字段
     */
    private Integer version;

    /**
     * 创建人员
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 修改人员
     */
    private String modifiedBy;

    /**
     * 修改时间
     */
    private LocalDateTime modifiedTime;


}
