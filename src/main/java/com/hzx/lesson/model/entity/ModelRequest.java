package com.hzx.lesson.model.entity;

import lombok.Data;

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
    /**
     * 是否流式返回
     */
    private boolean stream;
    /**
     * 控制生成文本的随机性（0 表示确定性最高，1 表示更随机）
     */
    private double temperature;
    /**
     * 限制生成的最大 token 数量
     */
    private int maxTokens;
    /**
     * 控制采样策略，只保留累积概率达到 top_p 的候选词
     */
    private double topP;
    /**
     * 生成的回复数量
     */
    private int n;
    /**
     * 指定停止生成的字符串或标记
     */
    private boolean stop;

}