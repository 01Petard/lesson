package com.hzx.lesson.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzx.lesson.common.enums.ErrorCode;
import com.hzx.lesson.common.exception.BusinessException;
import com.hzx.lesson.common.utils.SecurityHelper;
import com.hzx.lesson.mapper.ChatMapper;
import com.hzx.lesson.mapper.UserMapper;
import com.hzx.lesson.model.entity.Chat;
import com.hzx.lesson.model.entity.User;
import com.hzx.lesson.model.vo.ChatVO;
import com.hzx.lesson.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zexiao.huang
 * @since 2025/3/23 下午7:21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatMapper chatMapper;
    private final UserMapper userMapper;

    @Override
    public List<ChatVO> getUserChats(Integer page, Integer size) {

        long userId = SecurityHelper.getUserId();
        // 校验用户是否存在
        User user = userMapper.selectById(userId);
        if (null == user) {
            log.error("用户：{} 不存在，无法查询对话列表", userId);
            throw new BusinessException(ErrorCode.USER_NOT_EXISTS);
        }

        // 分页查询用户的历史对话
        IPage<Chat> chatPageQueryResult = chatMapper.selectPage(new Page<>(page, size),
                new LambdaQueryWrapper<Chat>().eq(Chat::getUserId, userId));
        log.info("用户：{}，对话数量：{}，明细：{}", userId, chatPageQueryResult.getTotal(), chatPageQueryResult.getRecords());

        // 封装VO
        return chatPageQueryResult.getRecords()
                .stream().map(chat -> {
                    ChatVO chatVO = new ChatVO();
                    chatVO.setChatId(chat.getChatId());
                    chatVO.setTitle(chat.getTitle());
                    chatVO.setPinned(chat.getPinned() == 1);
                    chatVO.setCreateTime(chat.getCreateTime());
                    chatVO.setUpdateTime(chat.getUpdateTime());
                    chatVO.setTotalMessages(chat.getChatExtList().size());
                    return chatVO;
                }).collect(Collectors.toList());
    }

    @Override
    public ChatVO createChat() {
        // 1. 获取当前用户ID
        long userId = SecurityHelper.getUserId();

        // 2. 校验用户是否存在
        User user = userMapper.selectById(userId);
        if (user == null) {
            log.error("用户：{} 不存在，无法创建对话", userId);
            throw new BusinessException(ErrorCode.USER_NOT_EXISTS);
        }

        // 3. 构建新对话
        Chat chat = new Chat();
        chat.setUserId(userId);
        chat.setTitle("新的对话 - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        chat.setPinned(0);

        // 4. 插入数据库
        chatMapper.insert(chat);

        // 5. 返回VO对象
        ChatVO chatVO = new ChatVO();
        chatVO.setChatId(chat.getChatId());
        chatVO.setTitle(chat.getTitle());
        chatVO.setPinned(false);
        chatVO.setCreateTime(chat.getCreateTime());
        chatVO.setUpdateTime(chat.getUpdateTime());
        chatVO.setTotalMessages(0);
        return chatVO;
    }

    @Override
    public void pinChat(Long chatId) {
        chatMapper.update(
                null,
                new LambdaUpdateWrapper<Chat>().eq(Chat::getChatId, chatId)
                        .set(Chat::getPinned, 1));
    }

    @Override
    public void deleteChat(Long chatId) {
        chatMapper.deleteById(chatId);

    }
}