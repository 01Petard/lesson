package com.hzx.lesson.controller;

import com.hzx.lesson.common.result.Result;
import com.hzx.lesson.model.dto.UserChatExtDTO;
import com.hzx.lesson.model.vo.UserChatExtVO;
import com.hzx.lesson.service.ChatExtService;
import io.swagger.annotations.ApiOperation;
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
public class ChatExtController {

    private final ChatExtService chatExtService;

    @ApiOperation(value = "获取对话详情", notes = "获取对话详情", response = Result.class, httpMethod = "GET")
    @GetMapping("/chat-ext/list")
    public Result<List<UserChatExtVO>> listChatExts(
            @RequestParam @NotNull Long chatId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        return Result.success(chatExtService.getChatExts(chatId, page, size));
    }

    @ApiOperation(value = "新增对话详情（与AI进行对话）", notes = "新增对话详情（与AI进行对话）", response = Result.class, httpMethod = "POST")
    @PutMapping("/chat-ext/add")
    public Result<UserChatExtVO> addChatExt(@RequestBody @Valid UserChatExtDTO dto) {
        return Result.success(chatExtService.insertChatExt(dto));
    }

    @ApiOperation(value = "删除对话详情", notes = "删除对话详情", response = Result.class, httpMethod = "DELETE")
    @DeleteMapping("/chat-ext/delete")
    public Result<String> deleteChatExt(@RequestParam @NotNull Long chatExtId) {
        chatExtService.deleteChatExt(chatExtId);
        return Result.success();
    }


}