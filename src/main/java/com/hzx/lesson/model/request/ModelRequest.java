package com.hzx.lesson.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 模型的请求参数
 * @author zexiao.huang
 * @since 2025/4/3 下午3:13
 */
@Data
public class ModelRequest {
    /**
     * 模型名称
     */
    private String model;
    /**
     * 发送给模型的prompt
     */
    private List<MessageBO> messages;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MessageBO {
        private String role;
        private String content;
    }
    /**
     * 是否流式返回
     */
    private boolean stream;
    /**
     * 控制生成文本的随机性（0 表示确定性最高，1 表示更随机）
     */
    private double temperature;

}