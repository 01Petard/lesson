package com.hzx.lesson;

import com.hzx.lesson.model.dto.UserChatExtDTO;
import com.hzx.lesson.service.AIChatService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;

@Slf4j
@SpringBootTest
class ChatByModelTests {

    @Resource
    private AIChatService aiChatService;

    @Test
    public void ChatByModelTests() {

        UserChatExtDTO dto = new UserChatExtDTO();
        dto.setUserId(1L);
        dto.setChatId(1L);
        dto.setContent("你好");
        dto.setModel("满血版DeepSeek");
        ArrayList<String> assistantPrompts = new ArrayList<>();

        String reply = aiChatService.chatByModel(dto, assistantPrompts);

        System.out.println(reply);

    }



}