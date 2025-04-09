package com.hzx.lesson.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 对话详情类
 */
@Data
@TableName("chat_ext")
public class ChatExt implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 详情ID（主键）
     */
    @TableId(type = IdType.AUTO)
    private Long chatExtId;

    /**
     * 对话ID（外键，关联chat表）
     */
    private Long chatId;

    /**
     * 用户ID（外键，关联user表）
     */
    private Long userId;

    /**
     * 消息角色（user/assistant/system）
     */
    private String role;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 对话的模型
     */
    private String model;

    /**
     * 消息时间戳
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 消息时间戳
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 逻辑删除（0-未删除 1-已删除）
     */
    @TableLogic
    private Integer deleted = 0;

    /**
     * 排序字段（可用于调整消息顺序）
     */
    private Integer sortOrder;

}