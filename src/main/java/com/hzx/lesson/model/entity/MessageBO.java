package com.hzx.lesson.model.entity;

import lombok.Data;

/**
 * 通信消息
 * @author zexiao.huang
 * @since 2025/4/3 下午3:15
 */
@Data
public class MessageBO {
    private String role;
    private String content;
}
