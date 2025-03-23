package com.hzx.lesson.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzx.lesson.model.entity.SysOrganization;
import com.hzx.lesson.service.SysOrganizationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 用户(SysOrganization)表控制层
 * @author makejava
 * @since 2025-03-23 21:50:38
 */
@RestController
@RequestMapping("sysOrganization")
public class SysOrganizationController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysOrganizationService sysOrganizationService;

    /**
     * 分页查询所有数据
     * @param page            分页对象
     * @param sysOrganization 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<SysOrganization> page, SysOrganization sysOrganization) {
        return success(this.sysOrganizationService.page(page, new QueryWrapper<>(sysOrganization)));
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.sysOrganizationService.getById(id));
    }

    /**
     * 新增数据
     * @param sysOrganization 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody SysOrganization sysOrganization) {
        return success(this.sysOrganizationService.save(sysOrganization));
    }

    /**
     * 修改数据
     * @param sysOrganization 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody SysOrganization sysOrganization) {
        return success(this.sysOrganizationService.updateById(sysOrganization));
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.sysOrganizationService.removeByIds(idList));
    }
}

