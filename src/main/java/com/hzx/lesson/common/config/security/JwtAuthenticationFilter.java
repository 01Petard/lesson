package com.hzx.lesson.common.config.security;

import com.hzx.lesson.common.constant.SecurityConstants;
import com.hzx.lesson.common.enums.ErrorCode;
import com.hzx.lesson.common.exception.BusinessException;
import com.hzx.lesson.common.utils.JwtProperties;
import com.hzx.lesson.common.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author zexiao.huang
 * @since 2025/3/23 下午4:30
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final JwtProperties jwtProperties;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        // 获取请求路径
        String requestUri = request.getRequestURI();

        // 如果是不需要认证的路径直接放行
        boolean isPublicPath = Arrays.stream(SecurityConstants.PUBLIC_PATTERNS)
                .anyMatch(pattern -> SecurityConstants.PATH_MATCHER.match(pattern, requestUri));

        if (isPublicPath) {
            log.info("跳过权限认证: {}", requestUri);
            filterChain.doFilter(request, response);
            return;
        }

        // 如果是需要认证的路径，则需要解析JWT
        log.info("JwtAuthenticationFilter正在处理请求: {}", requestUri);
        String token = request.getHeader(jwtProperties.getTokenName());
        if (token == null || !jwtUtil.validateToken(token)) {
            throw new BusinessException(ErrorCode.TOKEN_INVALID);
        }

        // 从JWT中获取用户ID
        Long userId = jwtUtil.getUserIdFromToken(token);
        log.info("无有效JWT令牌: {}", userId);
        // 构建authentication，因为用户提供了正确的JWT，所以密码为null，实现自动登录
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                CurrentUser.builder().userId(userId).build(), null, null
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

}
