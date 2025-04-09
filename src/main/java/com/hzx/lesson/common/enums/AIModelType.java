package com.hzx.lesson.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.hzx.lesson.common.exception.BusinessException;

public enum AIModelType {

    OLLAMA_1("deepseek-r1:1.5b"),
    OLLAMA_2("qwen-2.5:0.5b"),
    DEEPSEEK("deepseek-chat");

    private final String model;

    public String getModel() {
        return model;
    }

    AIModelType(String model) {
        this.model = model;
    }

    public static AIModelType fromModelName(String model) {
        switch (model){
            case "本地部署deepseek":
                return OLLAMA_1;
            case "本地部署Qwen-2.5":
                return OLLAMA_2;
            case "满血版DeepSeek":
                return DEEPSEEK;
            default:
                throw new BusinessException(ErrorCode.AI_TYPE_NOT_EXIST);
        }
    }
}