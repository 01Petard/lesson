package com.hzx.lesson.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户(SysUser)实体类
 * @author makejava
 * @since 2025-03-23 21:41:56
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 登陆名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 用户类型：1-admin,0-其他
     */
    private Integer userType;
    /**
     * 密码
     */
    private String password;
    /**
     * 1:初始密码 0:非初始密码
     */
    private Integer initial;
    /**
     * 密码修改时间
     */
    private Integer passwordUpdateTime;
    /**
     * 最后登录时间
     */
    private String lastLogin;
    /**
     * 备注
     */
    private String memo;
    /**
     * 更新人ID
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
    /**
     * 是否已删除
     */
    @TableLogic
    private Integer deleted;

}

