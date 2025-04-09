package com.hzx.lesson.common.enums;

/**
 * @author zexiao.huang
 * @since 2025/3/23 下午7:36
 */
public enum ErrorCode {

    // 系统级错误 1xxx
    SYSTEM_ERROR(1000, "系统繁忙，请稍后再试"),
    INVALID_PARAMETER(1001, "参数校验失败"),
    DATA_NOT_FOUND(1002, "数据不存在"),
    JSON_ERROR(1003, "序列化对象失败"),
    PARSE_ERROR(1003, "解析HTTP内容失败"),

    // 业务级错误 2xxx
    USER_EXISTS(2001, "用户已存在"),
    USER_NOT_EXISTS(2002, "用户不存在"),
    TOKEN_INVALID(2003, "未找到有效的JWT令牌"),
    TOKEN_EXPIRED(2004, "JWT令牌已过期"),
    PASSWORD_ERROR(2005, "密码错误"),
    USER_NOT_LOGIN(2006, "用户未登录"),
    AI_TYPE_NOT_EXIST(2006, "AI模型类型不存在"),
    HTTP_PARSE_ERROR(2006, "HTTP解析失败"),

    // 第三方服务错误 3xxx
    AI_SERVICE_ERROR(3001,"调用AI模型对话失败"),



    ;


    private final int code;
    private final String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
