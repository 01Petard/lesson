package com.hzx.lesson.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 对话类
 */
@Data
@TableName("chat")
public class Chat implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 对话ID（主键）
     */
    @TableId(type = IdType.AUTO)
    private Long chatId;

    /**
     * 用户ID（外键，关联user表）
     */
    private Long userId;

    /**
     * 对话标题（自动生成，如"新对话-2023-10-01"）
     */
    private String title;

    /**
     * 是否置顶（0-否 1-是）
     */
    private Integer pinned;

    /**
     * 对话创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 对话最后活跃的时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 逻辑删除（0-未删除 1-已删除）
     */
    @TableLogic
    private Integer deleted = 0;

    /**
     * 对话详情列表（一对多，非数据库字段）
     */
    @TableField(exist = false)
    private List<ChatExt> chatExtList;
}