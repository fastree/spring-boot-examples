package com.fastree.springboot.thymeleaf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 菜单实体
 * </p>
 *
 * @author Alex Jiang
 * @since 2021-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_menu")
public class MenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 父级菜单ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单标题
     */
    private String menuTitle;

    /**
     * 菜单类型(0:目录，1:菜单，2:按钮)
     */
    private Integer menuType;

    /**
     * 菜单排序
     */
    private Integer menuSort;

    /**
     * 菜单导航
     */
    private String menuHref;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单操作权限
     */
    private String permissions;

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

    /**
     * 当前菜单的子菜单
     */
    @TableField(exist = false)
    private List<MenuEntity> children;

}
