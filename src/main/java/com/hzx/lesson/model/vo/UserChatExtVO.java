package com.hzx.lesson.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * 对话消息详情VO
 * （对应chat_ext表，用于前端展示单条消息）
 */
@Data
public class UserChatExtVO {
    /**
     * 消息ID
     */
    private Long ChatExtId;

    /**
     * 所属对话ID
     */
    private Long chatId;

    /**
     * 对话详情所属用户ID
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
     * 关联的AI模型名称（当消息由不同AI生成时有用）
     */
    private String model;

    /**
     * 消息创建时间
     */
    private Date createTime;

}
