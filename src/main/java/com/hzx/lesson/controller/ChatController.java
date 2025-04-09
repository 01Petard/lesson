package com.hzx.lesson.controller;

import com.hzx.lesson.common.result.Result;
import com.hzx.lesson.model.vo.ChatVO;
import com.hzx.lesson.service.ChatService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 对话管理
 * @author zexiao.huang
 * @since 2025/3/23 下午3:33
 */
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ChatController {

    private final ChatService chatService;

    @ApiOperation(value = "获取用户对话列表", notes = "获取用户对话列表", response = Result.class, httpMethod = "GET")
    @GetMapping("/chat/list")
    public Result<List<ChatVO>> listChats(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        return Result.success(chatService.getUserChats(page, size));
    }

    @ApiOperation(value = "创建对话", notes = "创建对话", response = Result.class, httpMethod = "POST")
    @PostMapping("/chat/create")
    public Result<ChatVO> createChat() {
        return Result.success(chatService.createChat());
    }

    @ApiOperation(value = "置顶对话", notes = "置顶对话", response = Result.class, httpMethod = "PUT")
    @PutMapping("/chat/pin")
    public Result<String> pinChat(
            @RequestParam @NotNull Long chatId
    ) {
        chatService.pinChat(chatId);
        return Result.success();
    }

    @ApiOperation(value = "删除对话", notes = "删除对话", response = Result.class, httpMethod = "DELETE")
    @DeleteMapping("/chat/delete")
    public Result<String> deleteChat(
            @RequestParam @NotNull Long chatId
    ) {
        chatService.deleteChat(chatId);
        return Result.success();
    }




}