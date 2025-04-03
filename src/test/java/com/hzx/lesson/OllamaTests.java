package com.hzx.lesson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzx.lesson.common.utils.HttpUtils;
import com.hzx.lesson.model.entity.MessageBO;
import com.hzx.lesson.model.entity.ModelRequest;
import com.hzx.lesson.model.entity.ModelResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class OllamaTests {

    @Test
    void chat() throws Exception {

        // 1. 创建请求对象
        ModelRequest request = new ModelRequest();
        request.setModel("deepseek-r1:1.5b");

        ArrayList<MessageBO> messageBOS = new ArrayList<>();
        MessageBO bo1 = new MessageBO();
        bo1.setRole("user");
        bo1.setContent("写一首秋天的诗");
        messageBOS.add(bo1);

        MessageBO bo2 = new MessageBO();
        bo2.setRole("system");
        bo2.setContent("你是一个热心的智能助手，你的名字叫小团团，请你以小团团的身份回答用户的问题");
        messageBOS.add(bo2);

        request.setMessages(messageBOS);
        request.setStream(false);
        request.setTemperature(0.5);

        // 2. 序列化为JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(request);

        // 3. 发送POST请求
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        HttpResponse response = HttpUtils.doPost(
                "http://localhost:11434",
                "/api/chat",
                "POST",
                headers,
                null,   // 无查询参数
                jsonBody // 传递序列化后的JSON字符串
        );

        // 4. 获取响应内容
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 将响应体转换为字符串
            String responseBody = EntityUtils.toString(entity, "UTF-8");
            System.out.println("Response Body: " + responseBody);

            // 将 JSON 转换为 Map<String, Object>
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);

            // 手动创建 ModelResponse 对象并设置字段
            ModelResponse modelResponse = new ModelResponse();

            // 设置简单字段
            modelResponse.setModel((String) responseMap.get("model"));
            modelResponse.setCreatedAt((String) responseMap.get("created_at"));
            modelResponse.setDoneReason((String) responseMap.get("done_reason"));
            modelResponse.setDone((Boolean) responseMap.get("done"));
            modelResponse.setTotalDuration(((Number) responseMap.get("total_duration")).longValue());
            modelResponse.setLoadDuration(((Number) responseMap.get("load_duration")).longValue());
            modelResponse.setPromptEvalCount(((Number) responseMap.get("prompt_eval_count")).intValue());
            modelResponse.setPromptEvalDuration(((Number) responseMap.get("prompt_eval_duration")).longValue());
            modelResponse.setEvalCount(((Number) responseMap.get("eval_count")).intValue());
            modelResponse.setEvalDuration(((Number) responseMap.get("eval_duration")).longValue());

            // 处理嵌套的 message 对象
            Map<String, Object> messageMap = (Map<String, Object>) responseMap.get("message");
            MessageBO messageBO = new MessageBO();
            messageBO.setRole((String) messageMap.get("role"));
            messageBO.setContent((String) messageMap.get("content"));
            modelResponse.setMessage(messageBO);

            // 打印设置后的对象内容
            System.out.println("Parsed Response:");
            System.out.println("Model: " + modelResponse.getModel());
            System.out.println("Created At: " + modelResponse.getCreatedAt());
            System.out.println("Message Role: " + modelResponse.getMessage().getRole());
            System.out.println("Message Content: " + modelResponse.getMessage().getContent());
            System.out.println("Done Reason: " + modelResponse.getDoneReason());
            System.out.println("Done: " + modelResponse.isDone());
            System.out.println("Total Duration: " + modelResponse.getTotalDuration());
            System.out.println("Load Duration: " + modelResponse.getLoadDuration());
            System.out.println("Prompt Eval Count: " + modelResponse.getPromptEvalCount());
            System.out.println("Prompt Eval Duration: " + modelResponse.getPromptEvalDuration());
            System.out.println("Eval Count: " + modelResponse.getEvalCount());
            System.out.println("Eval Duration: " + modelResponse.getEvalDuration());
        }
    }
}