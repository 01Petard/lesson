package com.hzx.lesson.controller;

import com.hzx.lesson.common.result.Result;
import com.hzx.lesson.model.dto.UserRegisterDTO;
import com.hzx.lesson.model.vo.UserLoginVO;
import com.hzx.lesson.model.dto.UserLoginDTO;
import com.hzx.lesson.model.vo.UserInfoVO;
import com.hzx.lesson.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录
 * @author zexiao.huang
 * @since 2025/3/23 下午3:33
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public Result<?> register(@RequestBody UserRegisterDTO dto) {
        userService.register(dto.getUserName(), dto.getPassword());
        return Result.success("注册成功");
    }


    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO dto) {
        return Result.success(userService.login(dto));
    }

    @PostMapping("/info")
    public Result<UserInfoVO> info() {
        return Result.success(userService.info());
    }

    @DeleteMapping("/logout")
    public Result<Boolean> logout() {
        userService.logout();
        return Result.success(true);
    }
}