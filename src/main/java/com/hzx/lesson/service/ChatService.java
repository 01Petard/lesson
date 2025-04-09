package com.hzx.lesson.service;

import com.hzx.lesson.model.vo.ChatVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ChatService {
    /**
     * 获取用户对话列表
     * @param page
     * @param size
     * @return
     */
    List<ChatVO> getUserChats(Integer page, Integer size);

    /**
     * 创建对话
     * @return
     */
    ChatVO createChat();

    /**
     * 置顶对话
     * @param chatId
     */
    void pinChat(@NotNull Long chatId);

    /**
     * 删除对话
     * @param chatId
     */
    void deleteChat(@NotNull Long chatId);
}
