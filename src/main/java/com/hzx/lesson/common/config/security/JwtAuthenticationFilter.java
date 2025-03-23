package com.hzx.lesson.common.config.security;

import com.hzx.lesson.common.utils.JwtProperties;
import com.hzx.lesson.common.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        log.info(String.format("---%s---",request.getRequestURI()));

        log.info("JwtAuthenticationFilter: Processing request for {}", request.getRequestURI());

        String token = request.getHeader(jwtProperties.getTokenName());

        if (token != null && jwtUtil.validateToken(token)) {
            log.info("Valid JWT token found for user: {}", jwtUtil.getUserIdFromToken(token));
            Long userId = jwtUtil.getUserIdFromToken(token);

            // 构建authentication，因为提供了正确的JWT，所以密码为null，实现自动登录
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    CurrentUser.builder().userId(userId).build(), null, null);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else {
            log.warn("No valid JWT token found");
        }
        filterChain.doFilter(request, response);
    }

//    @Autowired
//    private JwtUtils jwtUtils;
//    @Autowired
//    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
//    @Autowired
//    private InvalidJWTHandler invalidJWTHandler;
//    @Autowired
//    private InvalidRequestHandler invalidRequestHandler;
//    @Autowired
//    private PermissionService permissionService;
//
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        String jwt = request.getHeader(jwtUtils.getHeader());
//        // 这里如果没有jwt，继续往后走，因为后面还有鉴权管理器等去判断是否拥有身份凭证，所以是可以放行的
//        // 没有jwt相当于匿名访问，若有一些接口是需要权限的，则不能访问这些接口
//        if (StrUtil.isBlankOrUndefined(jwt)) {
//            chain.doFilter(request, response);
//            return;
//        }
//        try {
//            Claims jwtToken = jwtUtils.getClaimsByToken(jwt);
//            Long userId = Long.valueOf((Integer) jwtToken.get(LoginEnum.TOKEN_USER_ID.getKey())) ;
//            Integer isAdmin = (Integer) jwtToken.get(LoginEnum.ADMIN.getKey());
//            String roleIdsString = (String)jwtToken.get(LoginEnum.ROLES.getKey());
//            Set<Long> roleIds = Arrays.stream(roleIdsString.split(",")).filter(e -> !e.isEmpty()).map(Long::parseLong).collect(Collectors.toSet());
//            String orgIdString = request.getHeader("orgId");
//            if(!StringUtils.isNumeric(orgIdString)) {
//                invalidRequestHandler.result(response,"orgId参数不存在或非数字");
//                return;
//            }
//
//            Long orgId = Long.parseLong(request.getHeader("orgId"));
//            // 构建UsernamePasswordAuthenticationToken,这里密码为null，是因为提供了正确的JWT,实现自动登录
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                    CurrentUser.builder().orgId(orgId).userId(userId).isAdmin(isAdmin == 1).roleIds(roleIds).build(),
//                    null, null);
//            SecurityContextHolder.getContext().setAuthentication(token);
//            if(!permissionService.isAccessUri(request.getRequestURI())) {
//                jwtAccessDeniedHandler.handle(request, response);
//                return;
//            }
//
//            chain.doFilter(request, response);
//        }catch (ExpiredJwtException e) {
//            invalidJWTHandler.result(response,"Token 过期，请重新登录");
//        }catch (Exception e) {
//            log.error("token 异常",e);
//            invalidJWTHandler.result(response,"Token 异常，请重新登录");
//        }
//    }
}
