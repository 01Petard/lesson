package com.hzx.lesson.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysResource)实体类
 * @author makejava
 * @since 2025-03-23 21:41:55
 */
@Data
@TableName("sys_resource")
public class SysResource implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 资源路径
     */
    private String path;
    /**
     * 角色ID
     */
    private Integer roleId;

    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

}

