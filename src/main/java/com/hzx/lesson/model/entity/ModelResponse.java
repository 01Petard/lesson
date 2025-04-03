package com.hzx.lesson.model.entity;

import lombok.Data;

/**
 * 模型的响应参数
 * @author zexiao.huang
 * @since 2025/4/3 下午3:13
 */
@Data
public class ModelResponse {
    /**
     * 模型名称
     */
    private String model;
    /**
     * 生成时间
     */
    private String createdAt;
    /**
     * 生成的回复信息
     */
    private MessageBO message;
    /**
     * 生成结束的原因,stop表示模型正常完成生成。
     */
    private String doneReason;
    /**
     * 是否已完成生成
     */
    private boolean done;
    /**
     * 整个请求处理的总耗时（单位：纳秒），1纳秒=10^-9秒
     */
    private long totalDuration;
    /**
     * 加载模型的耗时（单位：纳秒）
     */
    private long loadDuration;
    /**
     * 解析提示（prompt）过程中处理的 token 数量
     */
    private int promptEvalCount;
    /**
     * 解析提示（prompt）的耗时（单位：纳秒）
     */
    private long promptEvalDuration;
    /**
     * 生成回复过程中处理的 token 数量
     */
    private int evalCount;
    /**
     * 生成回复的耗时（单位：纳秒）
     */
    private long evalDuration;
}
