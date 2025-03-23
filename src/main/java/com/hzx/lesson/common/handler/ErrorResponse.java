package com.hzx.lesson.common.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zexiao.huang
 * @since 2025/3/23 下午8:09
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private int code;
    private String message;
}
