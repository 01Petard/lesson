package com.hzx.lesson.controller;

import com.hzx.lesson.common.result.Result;
import com.hzx.lesson.model.dto.RegisterDTO;
import com.hzx.lesson.model.dto.TokenDTO;
import com.hzx.lesson.model.dto.UserDTO;
import com.hzx.lesson.model.vo.UserVO;
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
    public Result<?> register(@RequestBody RegisterDTO registerDTO) {
        userService.register(registerDTO.getUserName(), registerDTO.getPassword());
        return Result.success("注册成功");
    }


    @PostMapping("/login")
    public Result<TokenDTO> login(@RequestBody UserDTO userDTO) {
        TokenDTO tokenDTO = userService.login(userDTO);
        return Result.success(tokenDTO);
    }

    @PostMapping("/info")
    public Result<UserVO> info() {
        return Result.success(userService.info());
    }

    @DeleteMapping("/logout")
    public Result<Boolean> logout() {
        userService.logout();
        return Result.success(true);
    }
}