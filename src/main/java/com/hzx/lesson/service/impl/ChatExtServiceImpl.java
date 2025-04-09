package com.hzx.lesson.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzx.lesson.common.constant.AIChatConstant;
import com.hzx.lesson.common.enums.ErrorCode;
import com.hzx.lesson.common.exception.BusinessException;
import com.hzx.lesson.common.utils.HttpUtils;
import com.hzx.lesson.common.utils.SecurityHelper;
import com.hzx.lesson.mapper.ChatExtMapper;
import com.hzx.lesson.mapper.UserMapper;
import com.hzx.lesson.model.dto.UserChatExtDTO;
import com.hzx.lesson.model.entity.ChatExt;
import com.hzx.lesson.model.entity.User;
import com.hzx.lesson.model.request.ModelRequest;
import com.hzx.lesson.model.response.OllamaResponse;
import com.hzx.lesson.model.vo.UserChatExtVO;
import com.hzx.lesson.service.AIChatService;
import com.hzx.lesson.service.ChatExtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zexiao.huang
 * @since 2025/3/23 下午7:21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ChatExtServiceImpl implements ChatExtService {

    private final ChatExtMapper chatExtMapper;
    private final UserMapper userMapper;
    private final AIChatService aiChatService;

    @Override
    public List<UserChatExtVO> getChatExts(Long chatId, Integer page, Integer size) {
        long userId = SecurityHelper.getUserId();

        // 校验用户是否存在
        User user = userMapper.selectById(userId);
        if (null == user) {
            log.error("用户：{} 不存在，无法查询对话列表", userId);
            return Collections.emptyList();
        }

        // 分页查询对话详情
        IPage<ChatExt> chatExtPageQueryResult = chatExtMapper.selectPage(new Page<>(page, size),
                new LambdaQueryWrapper<ChatExt>().eq(ChatExt::getChatId, chatId));
        log.info("用户：{}，对话：{}，对话详情数量：{}，明细：{}", userId, chatId, chatExtPageQueryResult.getTotal(), chatExtPageQueryResult.getRecords());

        // 封装VO
        return chatExtPageQueryResult.getRecords()
                .stream().map(chatExt -> {
                    UserChatExtVO vo = new UserChatExtVO();
                    vo.setChatExtId(chatExt.getChatExtId());
                    vo.setChatId(chatExt.getChatId());
                    vo.setUserId(chatExt.getUserId());
                    vo.setRole(chatExt.getRole());
                    vo.setContent(chatExt.getContent());
                    vo.setModel(chatExt.getModel());
                    vo.setCreateTime(chatExt.getCreateTime());
                    return vo;
                }).collect(Collectors.toList());
    }

    @Override
    public UserChatExtVO insertChatExt(UserChatExtDTO dto) {

        long userId = dto.getUserId();
        Long chatId = dto.getChatId();

        // 根据用户的id，获取用户的历史对话数据，将所有的历史对话作为上下文
        List<String> assistantPrompts = getAssistantPrompts(userId);
        // 调用AI接口，获取AI的回复
        String reply = aiChatService.chatByModel(dto, assistantPrompts);

        // 保存本轮对话详情
        ChatExt chatExt = new ChatExt();
        chatExt.setChatId(chatId);
        chatExt.setUserId(userId);
        chatExt.setRole(AIChatConstant.ROLE_ASSISTANT);
        chatExt.setContent(reply);
        chatExt.setModel(dto.getModel());
        chatExtMapper.insert(chatExt);

        // 封装VO
        UserChatExtVO vo = new UserChatExtVO();
        vo.setChatExtId(chatExt.getChatExtId());
        vo.setChatId(chatId);
        vo.setUserId(userId);
        vo.setRole(AIChatConstant.ROLE_USER);
        vo.setContent(reply);

        return vo;
    }

    // 根据用户的id，获取AI的历史对话数据，将所有的历史对话作为上下文提示词
    private List<String> getAssistantPrompts(Long userId) {
        List<String> assistantPrompts = new ArrayList<>();
        // 获取用户对话列表
        List<ChatExt> chatExtList = chatExtMapper.selectList(
                new LambdaQueryWrapper<ChatExt>().eq(ChatExt::getUserId, userId));
        // 获取AI的对话列表
        for (ChatExt chatExt : chatExtList) {
            if (AIChatConstant.ROLE_ASSISTANT.equals(chatExt.getRole())) {
                assistantPrompts.add(chatExt.getContent());
            }
        }
        return assistantPrompts;
    }


    @Override
    public void deleteChatExt(Long chatExtId) {
        chatExtMapper.deleteById(chatExtId);
    }
}