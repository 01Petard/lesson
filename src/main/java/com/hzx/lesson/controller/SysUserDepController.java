package com.hzx.lesson.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.hzx.lesson.service.SysUserDepService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysUserDep)表控制层
 * @author makejava
 * @since 2025-03-23 21:50:38
 */
@RestController
@RequestMapping("sysUserDep")
public class SysUserDepController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserDepService sysUserDepService;


}

