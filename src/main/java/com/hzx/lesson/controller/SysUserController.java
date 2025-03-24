package com.hzx.lesson.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.hzx.lesson.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户(SysUser)表控制层
 * @author makejava
 * @since 2025-03-23 21:50:38
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;


}

