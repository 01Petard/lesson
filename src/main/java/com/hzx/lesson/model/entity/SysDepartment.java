package com.hzx.lesson.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 组织部门(SysDepartment)实体类
 * @author makejava
 * @since 2025-03-23 21:41:55
 */
@Data
@TableName("sys_department")
public class SysDepartment implements Serializable {
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
     * 部门
     */
    private String name;
    /**
     * 默认角色ID
     */
    private Integer defaultRoleId;
    /**
     * 操作人
     */
    private Integer operatorId;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

}

