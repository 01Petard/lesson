package com.hzx.lesson.common.utils;

import com.hzx.lesson.config.security.CurrentUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author zexiao.huang
 * @since 2025/3/23 下午4:39
 */
@UtilityClass
public class SecurityHelper {

    public static long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getUserId(authentication);
    }

    public static long getUserId(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return 0;
        }
        Object principal = authentication.getPrincipal();
        if(principal instanceof CurrentUser) {
            return ((CurrentUser) principal).getUserId();
        }
        return 0;
    }

    public static CurrentUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getCurrentUser(authentication);
    }

    public static CurrentUser getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return CurrentUser.builder().build();
        }
        Object principal = authentication.getPrincipal();
        if(principal instanceof CurrentUser) {
            return (CurrentUser) principal;
        }
        return CurrentUser.builder().build();
    }
}
