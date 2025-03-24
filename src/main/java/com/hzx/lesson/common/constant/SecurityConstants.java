package com.hzx.lesson.common.constant;

import org.springframework.util.AntPathMatcher;

/**
 * @author zexiao.huang
 * @since 2025/3/24 下午3:11
 */

public class SecurityConstants {

    // 开放路径模式（支持通配符）
    public static final String[] PUBLIC_PATTERNS = {
            "/user/login",
            "/user/register",
            "/test/**"
    };

    // Ant路径匹配器（单例）
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    private SecurityConstants() {
    } // 防止实例化
}