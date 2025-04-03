package com.hzx.lesson.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 模型的响应参数
 * @author zexiao.huang
 * @since 2025/4/3 下午3:13
 */
@Data
public class OllamaResponse {
    /**
     * 模型名称
     */
    @JsonProperty("model")
    private String model;
    /**
     * 生成时间
     */
    @JsonProperty("created_at")
    private String createdAt;
    /**
     * 生成的回复信息
     */
    @JsonProperty("message")
    private ModelRequest.MessageBO message;

    /**
     * 生成结束的原因,stop表示模型正常完成生成。
     */
    @JsonProperty("done_reason")
    private String doneReason;
    /**
     * 是否已完成生成
     */
    @JsonProperty("done")
    private boolean done;
    /**
     * 整个请求处理的总耗时（单位：纳秒），1纳秒=10^-9秒
     */
    @JsonProperty("total_duration")
    private long totalDuration;
    /**
     * 加载模型的耗时（单位：纳秒）
     */
    @JsonProperty("load_duration")
    private long loadDuration;
    /**
     * 解析提示（prompt）过程中处理的 token 数量
     */
    @JsonProperty("prompt_eval_count")
    private int promptEvalCount;
    /**
     * 解析提示（prompt）的耗时（单位：纳秒）
     */
    @JsonProperty("prompt_eval_duration")
    private long promptEvalDuration;
    /**
     * 生成回复过程中处理的 token 数量
     */
    @JsonProperty("eval_count")
    private int evalCount;
    /**
     * 生成回复的耗时（单位：纳秒）
     */
    @JsonProperty("eval_duration")
    private long evalDuration;
}
