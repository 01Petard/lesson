package com.hzx.lesson.service;

import com.hzx.lesson.model.dto.TokenDTO;
import com.hzx.lesson.model.dto.UserDTO;
import com.hzx.lesson.model.vo.UserVO;

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
     * @param userDTO
     * @return
     */
    TokenDTO login(UserDTO userDTO);

    /**
     * 获取用户信息
     * @return
     */
    UserVO info();

    /**
     * 退出登录
     */
    void logout();
}
