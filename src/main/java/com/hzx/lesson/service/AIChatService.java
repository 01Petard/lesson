package com.hzx.lesson.service;

import com.hzx.lesson.model.dto.UserChatExtDTO;

import java.util.List;

public interface AIChatService {

    /**
     * 根据模型对话
     * @param dto
     * @param assistantPrompts
     */
    String chatByModel(UserChatExtDTO dto, List<String> assistantPrompts);
}
