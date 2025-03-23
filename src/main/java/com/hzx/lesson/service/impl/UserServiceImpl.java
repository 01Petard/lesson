package com.hzx.lesson.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hzx.lesson.common.enums.ErrorCode;
import com.hzx.lesson.common.exception.BusinessException;
import com.hzx.lesson.common.utils.JwtUtil;
import com.hzx.lesson.common.utils.SecurityHelper;
import com.hzx.lesson.mapper.UserMapper;
import com.hzx.lesson.model.dto.TokenDTO;
import com.hzx.lesson.model.dto.UserDTO;
import com.hzx.lesson.model.entity.User;
import com.hzx.lesson.model.vo.UserVO;
import com.hzx.lesson.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zexiao.huang
 * @since 2025/3/23 下午7:21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, Object> redisTemplate;

    // 注册用户时加密密码
    @Override
    public void register(String userName, String rawPassword) {

        // 检查用户名是否已存在
        if (ObjectUtils.isNotEmpty(
                userMapper.selectOne(new LambdaQueryWrapper<User>()
                        .eq(User::getUserName, userName)))) {
            throw new BusinessException(ErrorCode.USER_EXISTS, userName);
        }

        // 创建用户对象
        User user = new User();
        user.setUserName(userName);
        user.setPassword(passwordEncoder.encode(rawPassword));

        userMapper.insert(user);
    }


    @Override
    public TokenDTO login(UserDTO userDTO) {
        // 1. 数据库认证
        User user = this.authenticate(userDTO.getUserName(), userDTO.getPassword());

        // 2. 生成JWT
        String token = jwtUtil.generateToken(user.getUserId(), user.getUserName());
        log.info("生成的Token: {}", token);

        // 3. 存储到Redis
        redisTemplate.opsForValue().set(
                "token:" + user.getUserId(),
                token,
                10, TimeUnit.MINUTES
        );

        // 4. 修改登录时间
        User temp = new User();
        temp.setUserId(user.getUserId());
        temp.setLastLogin(new Date());
        userMapper.updateById(temp);

        // 5. 返回响应
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        tokenDTO.setUserId(user.getUserId());
        tokenDTO.setUserName(user.getUserName());
        return tokenDTO;
    }


    @Override
    public UserVO info() {
        // 1. 获取用户id
        long userId = SecurityHelper.getUserId();

        // 2. Redis验证
        String storedToken = (String) redisTemplate.opsForValue().get("token:" + userId);
        if (storedToken == null) {
            throw new BusinessException(ErrorCode.TOKEN_EXPIRED);
        }

        // 3. 返回用户信息
        User user = userMapper.selectById(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new BusinessException(ErrorCode.USER_NOT_EXISTS);
        }

        UserVO userVO = new UserVO();
        userVO.setUserName(user.getUserName());
        userVO.setUserId(user.getUserId());
        return userVO;
    }


    @Override
    public void logout() {
        // 1. 获取当前用户ID（确保用户已登录）
        Long userId = SecurityHelper.getUserId();

        // 2. 尝试删除Token
        Boolean isDeleted = redisTemplate.delete("token:" + userId);

        // 3. 记录日志
        if (Boolean.TRUE.equals(isDeleted)) {
            log.info("用户：{} 的Token 已成功删除", userId);
        } else {
            log.warn("用户：{} 的Token 不存在或删除失败", userId);
        }
    }

    // 验证用户名和密码
    public User authenticate(String userName, String rawPassword) {

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, userName));
        if (ObjectUtils.isEmpty(user)) {
            throw new BusinessException(ErrorCode.USER_NOT_EXISTS);
        }
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BusinessException(ErrorCode.PASSWORD_ERROR);
        }
        return user;
    }


}
