package com.hzx.lesson.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户(SysOrganization)实体类
 * @author makejava
 * @since 2025-03-23 21:41:55
 */
@Data
@TableName("sys_organization")
public class SysOrganization implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 电话
     */
    @TableId(type = IdType.AUTO)
    private String mobile;
    /**
     * 地址
     */
    private String address;
    /**
     * 状态 0-关闭 1-启用
     */
    private Integer state;
    /**
     * none-不选择 kingdee-金蝶
     */
    private String erpType;
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

}

