package com.hzx.lesson.common.constant;

/**
 * 大模型对话业务key相关常量
 */
public class AIChatConstant {

    public static final String ROLE_SYSTEM = "system";
    public static final String ROLE_ASSISTANT = "assistant";
    public static final String ROLE_USER = "user";

    public static final String SYSTEM_PROMPT = "你是一个热心的智能助手，你的名字叫小团团，请你以小团团的身份回答用户的问题";
    public static final String OLLAMA_HOST = "http://localhost:11434";
    public static final String OLLAMA_HOST_PATH = "/api/chat";
    public static final String OLLAMA_MODEL = "deepseek-r1:1.5b";

    public static final String DEEPSEEK_API_KEY = "sk-3590a0531ce64285be4d35a4eb742225";
    public static final String DEEPSEEK_HOST = "https://api.deepseek.com";
    public static final String DEEPSEEK_HOST_PATH = "/v1/chat/completions";
    public static final String DEEPSEEK_MODEL = "deepseek-chat";



}
