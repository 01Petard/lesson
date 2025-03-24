package com.hzx.lesson.common.config;

import com.hzx.lesson.common.config.security.JwtAuthenticationFilter;
import com.hzx.lesson.common.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author zexiao.huang
 * @since 2025/3/23 下午4:04
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法级安全注解
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                // 禁用CSRF保护
                .csrf().disable()
                // 无状态会话，创建会话时，不创建会话，只创建一个空的Authentication对象
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 配置开放的接口和其他需认证的接口
                .and()
                .authorizeRequests()
                .antMatchers(SecurityConstants.PUBLIC_PATTERNS).permitAll()
                .anyRequest().authenticated()

                // 添加JWT过滤器
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}