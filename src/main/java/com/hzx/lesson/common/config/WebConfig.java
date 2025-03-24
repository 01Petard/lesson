package com.hzx.lesson.common.config;

import com.hzx.lesson.common.config.interceptor.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zexiao.huang
 * @since 2025/3/24 下午2:41
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoggerInterceptor loggerInterceptor;

    public WebConfig(LoggerInterceptor loggerInterceptor) {
        this.loggerInterceptor = loggerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器并指定拦截路径
        registry.addInterceptor(loggerInterceptor)
                // 拦截所有请求
                .addPathPatterns("/**")
                // 排除静态资源和错误页面
                .excludePathPatterns("/static/**", "/error");
    }
}
