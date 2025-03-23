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

    // 业务级错误 2xxx
    USER_EXISTS(2001, "用户已存在"),
    USER_NOT_EXISTS(2002, "用户不存在"),
    TOKEN_EXPIRED(2003, "token已过期"),
    PASSWORD_ERROR(2004, "密码错误"),
    USER_NOT_LOGIN(2005, "用户未登录"),

    // 第三方服务错误 3xxx
    PAYMENT_FAILED(3001,"支付失败");

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
