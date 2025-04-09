package com.hzx.lesson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzx.lesson.common.utils.HttpUtils;
import com.hzx.lesson.model.response.DeepSeekResponse;
import com.hzx.lesson.model.request.ModelRequest;
import com.hzx.lesson.model.response.OllamaResponse;
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

    /**
     * 创建对话请求对象
     * @param model
     * @param systemPrompt
     * @param userPrompt
     * @return
     */
    private ModelRequest createChatRequest(String model, String systemPrompt, List<String> assistantPrompts, String userPrompt) {
        ModelRequest request = new ModelRequest();
        request.setModel(model);
        request.setStream(false);
        request.setTemperature(0.5);

        List<ModelRequest.MessageBO> messages = new ArrayList<>();

        // 系统消息（始终放在首位）
        if (StringUtils.isNotBlank(systemPrompt)) {
            messages.add(createChatRequestMessage("system", systemPrompt));
        }
        if (!assistantPrompts.isEmpty()) {
            // 上下文消息（作为提示词）
            for (String assistantPrompt : assistantPrompts) {
                messages.add(createChatRequestMessage("assistant", assistantPrompt));
            }
        }

        // 用户消息（始终放在最后）
        messages.add(createChatRequestMessage("user", userPrompt));

        request.setMessages(messages);
        return request;
    }

    /**
     * 创建对话请求对象--创建消息对象
     * @param role
     * @param content
     * @return
     */
    private ModelRequest.MessageBO createChatRequestMessage(String role, String content) {
        ModelRequest.MessageBO message = new ModelRequest.MessageBO();
        message.setRole(role);
        message.setContent(content);
        return message;
    }


    /**
     * 解析大模型的响应，将返回的JSON数据解析为不同类型模型的对象
     * @param entity       响应的HttpEntity
     * @param responseType Response对象的类型
     * @param <T>          不同的AI模型的Response对象
     * @return
     * @throws IOException
     */
    private <T> T parseResponse(HttpEntity entity, Class<T> responseType) throws IOException {
        if (entity == null) {
            return null;
        }
        String responseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        return new ObjectMapper().readValue(responseBody, responseType);
    }


    private static final String SYSTEM_PROMPT = "你是一个热心的智能助手，你的名字叫小团团，请你以小团团的身份回答用户的问题";
    private static final String OLLAMA_HOST = "http://localhost:11434";
    private static final String OLLAMA_HOST_PATH = "/api/chat";
    private static final String OLLAMA_MODEL = "deepseek-r1:1.5b";

    /**
     * 测试本地大模型的聊天功能
     * @throws Exception
     */
    @Test
    void chat_ollama() throws Exception {

        // 用户输入的问题
        String userPrompt = "写一首秋天的诗";

        // 根据用户的id，获取用户的历史对话数据，将所有的历史对话作为上下文
        List<String> assistantPrompts = new ArrayList<>();

        // 1. 创建请求对象
        ModelRequest request = createChatRequest(
                OLLAMA_MODEL,
                SYSTEM_PROMPT,
                assistantPrompts,
                userPrompt
        );

        // 2. 将请求对象序列化为JSON
        String jsonBody = new ObjectMapper().writeValueAsString(request);

        // 3. 发送POST请求
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        HttpResponse response = HttpUtils.doPost(
                OLLAMA_HOST,
                OLLAMA_HOST_PATH,
                headers,
                null,
                jsonBody
        );

        // 4. 解析响应
        OllamaResponse ollamaResponse = parseResponse(response.getEntity(), OllamaResponse.class);
        System.out.println("Ollama Response Status: " + response.getStatusLine());
        System.out.println("Ollama Response Body: " + ollamaResponse.toString());
        System.out.println("Ollama's Response: " + ollamaResponse.getMessage().getContent());

    }


    private static final String DEEPSEEK_API_KEY = "sk-3590a0531ce64285be4d35a4eb742225";
    private static final String DEEPSEEK_HOST = "https://api.deepseek.com";
    private static final String DEEPSEEK_HOST_PATH = "/v1/chat/completions";
    private static final String DEEPSEEK_MODEL = "deepseek-chat";

    /**
     * 测试DeepSeek API的聊天功能
     * @throws Exception
     */
    @Test
    void chat_deepseek() throws Exception {


        // 用户输入的问题
        String userPrompt = "写一首秋天的诗";

        // 根据用户的id，获取用户的历史对话数据，将所有的历史对话作为上下文
        List<String> assistantPrompts = new ArrayList<>();


        // 1. 创建请求对象
        ModelRequest request = createChatRequest(
                DEEPSEEK_MODEL,
                SYSTEM_PROMPT,
                assistantPrompts,
                userPrompt
        );

        // 2. 将请求对象序列化为JSON
        String jsonBody = new ObjectMapper().writeValueAsString(request);

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
        DeepSeekResponse deepSeekResponse = parseResponse(response.getEntity(), DeepSeekResponse.class);
        System.out.println("Response Status: " + response.getStatusLine());
        System.out.println("Response Body: " + deepSeekResponse.toString());
        System.out.println("DeepSeek's Response: " + deepSeekResponse.getChoices().get(0).getMessage().getContent());

    }


}