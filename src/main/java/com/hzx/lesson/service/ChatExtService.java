package com.hzx.lesson.service;

import com.hzx.lesson.model.dto.UserChatExtDTO;
import com.hzx.lesson.model.vo.UserChatExtVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ChatExtService {
    /**
     * 获取对话的详细记录
     * @param chatId
     * @param page
     * @param size
     * @return
     */
    List<UserChatExtVO> getChatExts(@NotNull Long chatId, Integer page, Integer size);

    /**
     * 新增对话的详细记录
     * @param dto
     * @return
     */
    UserChatExtVO insertChatExt(@Valid UserChatExtDTO dto);

    /**
     * 删除对话的详细记录
     * @param chatExtId
     */
    void deleteChatExt(@NotNull Long chatExtId);
}
