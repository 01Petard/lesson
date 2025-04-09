package com.hzx.lesson.service;

import com.hzx.lesson.model.vo.UserLoginVO;
import com.hzx.lesson.model.dto.UserLoginDTO;
import com.hzx.lesson.model.vo.UserInfoVO;

/**
 * @author zexiao.huang
 * @since 2025/3/23 下午3:56
 */
public interface UserService {

    /**
     * 注册
     * @param username
     * @param rawPassword
     */
    void register(String username, String rawPassword);

    /**
     * 登录
     * @param userLoginDTO
     * @return
     */
    UserLoginVO login(UserLoginDTO userLoginDTO);

    /**
     * 获取用户信息
     * @return
     */
    UserInfoVO info();

    /**
     * 退出登录
     */
    void logout();
}
