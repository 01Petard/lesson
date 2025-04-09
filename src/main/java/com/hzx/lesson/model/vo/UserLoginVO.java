package com.hzx.lesson.model.vo;

import lombok.Data;

/**
 * 用户登录结果返回类
 * @author zexiao.huang
 * @since 2025/3/23 下午4:10
 */
@Data
public class UserLoginVO {
    private String token;
    private Long userId;
    private String userName;
}
