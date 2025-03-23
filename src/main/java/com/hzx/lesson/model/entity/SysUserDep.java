package com.hzx.lesson.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysUserDep)实体类
 * @author makejava
 * @since 2025-03-23 21:41:56
 */
@Data
@TableName("sys_user_dep")
public class SysUserDep implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer orgId;

    private Integer depId;

    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

}

