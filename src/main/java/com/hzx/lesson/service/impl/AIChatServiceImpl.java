package com.hzx.lesson.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzx.lesson.config.AIConfig;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AIChatServiceImpl implements AIChatService {
    private final AIConfig aiConfig;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String chatByModel(UserChatExtDTO dto, List<String> assistantPrompts) {

        // 1. 根据模型名称选择配置
        AIModelType modelType = AIModelType.fromModelName(dto.getModel());
        AIConfig.ModelConfig config = aiConfig.getConfigs().get(modelType);

        // 2. 构建请求
        ModelRequest request = buildRequest(modelType, dto, assistantPrompts);

        // 3. 序列化对象为字符串
        String jsonBody;
        try {
            jsonBody = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new BusinessException(ErrorCode.JSON_ERROR, e.getMessage());
        }

        // 4. 发送请求并处理响应
        HttpResponse response;
        try {
            response = sendRequest(modelType, config, jsonBody);
        } catch (Exception e) {
            log.error("{} 模型请求失败", modelType.name(), e);
            throw new BusinessException(ErrorCode.AI_SERVICE_ERROR, e.getMessage());
        }

        // 5. 解析响应
        String content;
        try {
            content = parseResponse(modelType, response);
        } catch (Exception e) {
            log.error("HTTP响应解析失败：{}", response, e);
            throw new BusinessException(ErrorCode.HTTP_PARSE_ERROR, e.getMessage());
        }
        return content;
    }


    /**
     * 构建请求
     * @param dto
     * @param assistantPrompts
     * @return
     */
    private ModelRequest buildRequest(AIModelType modelType, UserChatExtDTO dto, List<String> assistantPrompts) {
        ModelRequest request = new ModelRequest();
        request.setModel(modelType.getModel());
        request.setStream(false);
        request.setTemperature(0.5);
        // 构建消息列表
        List<ModelRequest.MessageBO> messages = new ArrayList<>();
        // 系统消息
        if (StringUtils.isNotBlank(AIChatConstant.SYSTEM_PROMPT)) {
            messages.add(new ModelRequest.MessageBO(AIChatConstant.ROLE_SYSTEM, AIChatConstant.SYSTEM_PROMPT));
        }
        // 历史消息
        assistantPrompts.forEach(prompt ->
                messages.add(new ModelRequest.MessageBO(AIChatConstant.ROLE_ASSISTANT, prompt)));
        // 用户消息
        messages.add(new ModelRequest.MessageBO(AIChatConstant.ROLE_USER, dto.getContent()));
        request.setMessages(messages);
        return request;
    }

    /**
     * 发送请求
     * @param modelType
     * @param config
     * @param jsonBody
     * @return
     * @throws Exception
     */
    private HttpResponse sendRequest(AIModelType modelType, AIConfig.ModelConfig config, String jsonBody) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        // DeepSeek需要API Key
        if (modelType == AIModelType.DEEPSEEK) {
            headers.put("Authorization", "Bearer " + config.getApiKey());
        }

        return HttpUtils.doPost(
                config.getHost(),
                config.getPath(),
                headers,
                null,
                jsonBody
        );
    }

    /**
     * 解析响应
     * @param modelType
     * @param response
     * @return
     * @throws IOException
     */
    private String parseResponse(AIModelType modelType, HttpResponse response) throws IOException {
        String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        String content;
        switch (modelType) {
            case OLLAMA_1:
                content = objectMapper.readValue(responseBody, OllamaResponse.class).getMessage().getContent();
                break;
            case OLLAMA_2:
                content = objectMapper.readValue(responseBody, OllamaResponse.class).getMessage().getContent();
                break;
            case DEEPSEEK:
                content = objectMapper.readValue(responseBody, DeepSeekResponse.class).getChoices().get(0).getMessage().getContent();
                break;
            default:
                throw new BusinessException(ErrorCode.AI_TYPE_NOT_EXIST);
        }
        return content;
    }
}