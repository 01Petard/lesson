package com.hzx.lesson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzx.lesson.common.config.AIConfig;
import com.hzx.lesson.common.constant.AIChatConstant;
import com.hzx.lesson.common.enums.AIModelType;
import com.hzx.lesson.common.enums.ErrorCode;
import com.hzx.lesson.common.exception.BusinessException;
import com.hzx.lesson.common.utils.HttpUtils;
import com.hzx.lesson.model.dto.UserChatExtDTO;
import com.hzx.lesson.model.request.ModelRequest;
import com.hzx.lesson.model.response.DeepSeekResponse;
import com.hzx.lesson.model.response.OllamaResponse;
import com.hzx.lesson.service.AIChatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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