package com.hzx.lesson.common.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * jwt令牌相关配置
 * @author zexiao.huang
 * @date 2025/3/23 下午3:15
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    private String secretKey;
    private long ttl;
    private String tokenName;


}