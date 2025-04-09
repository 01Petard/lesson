package com.hzx.lesson.model.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 前端发送的对话请求DTO
 */
@Data
public class UserChatExtDTO {

    /**
     * 用户ID（必传）
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 对话ID（可选，如果为空则新开启一个对话）
     */
    private Long chatId;

    /**
     * 用户输入的消息内容（必传）
     */
    @NotBlank(message = "消息内容不能为空")
    @Max(value = 32000, message = "消息内容不能超过32000个字符")
    private String content;

    /**
     * 对话的模型
     */
    @NotNull(message = "对话的模型不能为空")
    private String model;

    /**
     * 客户端时间戳（可选，用于调试）
     */
    private Long clientTimestamp;

}