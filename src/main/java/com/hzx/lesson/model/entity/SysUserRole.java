package com.hzx.lesson.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 角色表关联用户(SysUserRole)实体类
 * @author makejava
 * @since 2025-03-23 21:41:57
 */
@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 组织id
     */
    private Integer orgId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 创建人ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

}

