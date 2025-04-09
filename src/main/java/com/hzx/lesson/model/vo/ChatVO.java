package com.hzx.lesson.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * 对话列表项VO
 */
@Data
public class ChatVO {
    /**
     * 对话ID
     */
    private Long chatId;

    /**
     * 对话标题（自动生成或用户自定义）
     */
    private String title;

    /**
     * 是否置顶（需要Chat类转一下）
     */
    private Boolean pinned;

    /**
     * 对话创建时间
     */
    private Date createTime;

    /**
     * 最后活跃时间（等同于最后消息时间）
     */
    private Date updateTime;

    /**
     * 消息总数
     */
    private Integer totalMessages;

}
