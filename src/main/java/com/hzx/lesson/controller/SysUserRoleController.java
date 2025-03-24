package com.hzx.lesson.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.hzx.lesson.service.SysUserRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色表关联用户(SysUserRole)表控制层
 * @author makejava
 * @since 2025-03-23 21:50:38
 */
@RestController
@RequestMapping("sysUserRole")
public class SysUserRoleController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserRoleService sysUserRoleService;

}

