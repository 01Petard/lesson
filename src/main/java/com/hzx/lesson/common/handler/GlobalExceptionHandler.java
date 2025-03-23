package com.hzx.lesson.common.handler;

import com.hzx.lesson.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zexiao.huang
 * @since 2025/3/23 下午7:40
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        return ResponseEntity
                .status(resolveHttpStatus(ex.getCode()))
                .body(new ErrorResponse(ex.getCode(), ex.getMessage()));
    }

    private HttpStatus resolveHttpStatus(int code) {

        switch (code / 1000) {
            case 1:
                // 1xxx 客户端错误
                return HttpStatus.BAD_REQUEST;
            case 2:
                // 2xxx 业务冲突
                return HttpStatus.CONFLICT;
            case 3:
                // 3xxx 第三方服务错误
                return HttpStatus.BAD_GATEWAY;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
