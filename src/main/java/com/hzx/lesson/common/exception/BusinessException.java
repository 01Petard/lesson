package com.hzx.lesson.common.exception;

import com.hzx.lesson.common.enums.ErrorCode;
import lombok.Getter;

import java.text.MessageFormat;

/**
 * @author zexiao.huang
 * @since 2025/3/23 下午7:37
 */
public class BusinessException extends RuntimeException {
    @Getter
    private final int code;
    private final String message;

    public BusinessException(ErrorCode errorCode, Object... args) {
        super(formatMessage(errorCode.getMsg(), args));
        this.code = errorCode.getCode();
        this.message = super.getMessage();
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    private static String formatMessage(String msg, Object... args) {
        return args.length > 0 ? MessageFormat.format(msg, args) : msg;
    }

    @Override
    public String getMessage() {
        return message;
    }
}