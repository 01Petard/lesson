package com.hzx.lesson.model.dto;

import lombok.Data;

/**
 * @author zexiao.huang
 * @since 2025/3/23 下午4:10
 */
@Data
public class TokenDTO {
    private String token;
    private Long userId;
    private String userName;
}
