package com.hzx.lesson.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hzx.lesson.common.constant.RedisKeyConstant;
import com.hzx.lesson.common.enums.ErrorCode;
import com.hzx.lesson.common.exception.BusinessException;
import com.hzx.lesson.common.utils.JwtUtil;
import com.hzx.lesson.common.utils.SecurityHelper;
import com.hzx.lesson.mapper.UserMapper;
import com.hzx.lesson.model.vo.UserLoginVO;
import com.hzx.lesson.model.dto.UserLoginDTO;
import com.hzx.lesson.model.entity.User;
import com.hzx.lesson.model.vo.UserInfoVO;
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

        // 用户名称如果重复，则注册失败
        if (ObjectUtils.isNotEmpty(
                userMapper.selectOne(new LambdaQueryWrapper<User>()
                        .eq(User::getUserName, userName)))) {
            throw new BusinessException(ErrorCode.USER_EXISTS, userName);
        }

        // 创建用户
        User user = new User();
        user.setUserName(userName);
        user.setPassword(passwordEncoder.encode(rawPassword));

        userMapper.insert(user);
    }


    @Override
    public UserLoginVO login(UserLoginDTO dto) {
        // 1. 数据库认证
        User user = this.authenticate(dto.getUserName(), dto.getPassword());

        // 2. 生成JWT，存储到Redis
        String token = jwtUtil.generateToken(user.getUserId(), user.getUserName());
        log.info("生成的Token: {}", token);
        redisTemplate.opsForValue().set(
                RedisKeyConstant.USER_TOKEN + user.getUserId(),
                token,
                30, TimeUnit.DAYS
        );

        // 3. 修改用户的最后登录时间
        userMapper.update(null,
                new LambdaUpdateWrapper<User>()
                        .eq(User::getUserId, user.getUserId())
                        .set(User::getLastLogin, new Date())
        );

        // 4. 返回响应
        UserLoginVO tokenDTO = new UserLoginVO();
        tokenDTO.setToken(token);
        tokenDTO.setUserId(user.getUserId());
        tokenDTO.setUserName(user.getUserName());
        return tokenDTO;
    }

    /**
     * 验证用户名和密码是否正确
     * @param userName    用户名
     * @param rawPassword 密码
     * @return
     */
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


    @Override
    public UserInfoVO info() {
        // 1. 获取用户id
        long userId = SecurityHelper.getUserId();

        // 2. Redis验证
        String storedToken = (String) redisTemplate.opsForValue().get(RedisKeyConstant.USER_TOKEN + userId);
        if (storedToken == null) {
            throw new BusinessException(ErrorCode.TOKEN_EXPIRED);
        }

        // 3. 返回用户信息
        User user = userMapper.selectById(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new BusinessException(ErrorCode.USER_NOT_EXISTS);
        }

        UserInfoVO userVO = new UserInfoVO();
        userVO.setUserName(user.getUserName());
        userVO.setUserId(user.getUserId());
        return userVO;
    }


    @Override
    public void logout() {
        // 1. 获取当前用户ID（确保用户已登录）
        Long userId = SecurityHelper.getUserId();

        // 2. 判断Redis中是否有这个用户的Token
        String storedToken = (String) redisTemplate.opsForValue().get(RedisKeyConstant.USER_TOKEN + userId);
        if (null == storedToken) {
            log.info("用户：{} 的Token 不存在，已经退出登录了", userId);
        }

        // 3. 尝试删除Token
        Boolean isDeleted = redisTemplate.delete(RedisKeyConstant.USER_TOKEN + userId);

        // 4. 记录日志
        if (Boolean.TRUE.equals(isDeleted)) {
            log.info("用户：{} 的Token 已成功删除", userId);
        } else {
            log.warn("用户：{} 的Token 删除失败", userId);
        }
    }


}
