package com.hzx.lesson.common.config.security;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author zexiao.huang
 * @since 2025/3/23 下午4:40
 */
@Data
@Builder
public class CurrentUser {
    private Long orgId;
    private Long userId;
    private Boolean isAdmin;
    /**
     * 所在部门给的角色
     */
    private Set<Long> roleIds;
}

