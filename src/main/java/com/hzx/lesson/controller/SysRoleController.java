package com.hzx.lesson.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.hzx.lesson.service.SysRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色表(SysRole)表控制层
 * @author makejava
 * @since 2025-03-23 21:50:38
 */
@RestController
@RequestMapping("sysRole")
public class SysRoleController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;


}

