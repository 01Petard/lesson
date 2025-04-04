package com.hzx.lesson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzx.lesson.common.utils.HttpUtils;
import com.hzx.lesson.model.entity.DeepSeekResponse;
import com.hzx.lesson.model.entity.ModelRequest;
import com.hzx.lesson.model.entity.OllamaResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ChatTests {

    private ModelRequest createRequest(String modelName, String systemPrompt, String userPrompt) {
        ModelRequest request = new ModelRequest();
        request.setModel(modelName);
        request.setStream(false);
        request.setTemperature(0.5);

        List<ModelRequest.MessageBO> messages = new ArrayList<>();

        // 系统消息（始终放在首位）
        if (StringUtils.isNotBlank(systemPrompt)) {
            messages.add(createMessage("system", systemPrompt));
        }

        // 用户消息
        messages.add(createMessage("user", userPrompt));

        request.setMessages(messages);
        return request;
    }

    private ModelRequest.MessageBO createMessage(String role, String content) {
        ModelRequest.MessageBO message = new ModelRequest.MessageBO();
        message.setRole(role);
        message.setContent(content);
        return message;
    }

    // 具体请求方法
    private ModelRequest handleOllamaRequest() {
        return createRequest(
                "deepseek-r1:1.5b",
                "你是一个热心的智能助手，你的名字叫小团团，请你以小团团的身份回答用户的问题",
                "写一首秋天的诗"
        );
    }

    private ModelRequest handleDeepSeekRequest() {
        return createRequest(
                "deepseek-chat",
                "你是一个热心的智能助手，你的名字叫小团团，请你以小团团的身份回答用户的问题",
                "写一首秋天的诗"
        );
    }


    // 封装的公共方法（泛型）
    private <T> T parseResponse(HttpEntity entity, ObjectMapper objectMapper, Class<T> responseType) throws IOException {
        if (entity == null) {
            return null;
        }
        String responseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        return objectMapper.readValue(responseBody, responseType);
    }

    @Test
    void chat_ollama() throws Exception {

        // 1. 创建请求对象
        ModelRequest request = handleOllamaRequest();

        // 2. 序列化为JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(request);

        // 3. 发送POST请求
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        HttpResponse response = HttpUtils.doPost(
                "http://localhost:11434",
                "/api/chat",
                headers,
                null,
                jsonBody
        );


        // 4. 解析响应
        OllamaResponse ollamaResponse = parseResponse(response.getEntity(), objectMapper, OllamaResponse.class);
        System.out.println("Ollama Response Status: " + response.getStatusLine());
        System.out.println("Ollama Response Body: " + ollamaResponse.toString());
    }


    private static final String DEEPSEEK_API_KEY = "sk-3590a0531ce64285be4d35a4eb742225";
    private static final String DEEPSEEK_HOST = "https://api.deepseek.com";
    private static final String DEEPSEEK_HOST_PATH = "/v1/chat/completions";

    @Test
    void chat_deepseek() throws Exception {
        // 1. 创建请求对象
        ModelRequest request = handleDeepSeekRequest();

        // 2. 序列化为JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(request);

        // 3. 发送POST请求
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + DEEPSEEK_API_KEY);

        HttpResponse response = HttpUtils.doPost(
                DEEPSEEK_HOST,
                DEEPSEEK_HOST_PATH,
                headers,
                null,
                jsonBody
        );

        // 4. 解析响应
        DeepSeekResponse deepSeekResponse = parseResponse(response.getEntity(), objectMapper, DeepSeekResponse.class);
        System.out.println("Response Status: " + response.getStatusLine());
        System.out.println("Response Body: " + deepSeekResponse.toString());
        System.out.println("AI's Response: " + deepSeekResponse.getChoices().get(0).getMessage().getContent());

    }


}