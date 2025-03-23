package com.hzx.lesson.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzx.lesson.model.entity.SysResource;
import com.hzx.lesson.service.SysResourceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (SysResource)表控制层
 * @author makejava
 * @since 2025-03-23 21:50:38
 */
@RestController
@RequestMapping("sysResource")
public class SysResourceController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysResourceService sysResourceService;

    /**
     * 分页查询所有数据
     * @param page        分页对象
     * @param sysResource 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<SysResource> page, SysResource sysResource) {
        return success(this.sysResourceService.page(page, new QueryWrapper<>(sysResource)));
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.sysResourceService.getById(id));
    }

    /**
     * 新增数据
     * @param sysResource 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody SysResource sysResource) {
        return success(this.sysResourceService.save(sysResource));
    }

    /**
     * 修改数据
     * @param sysResource 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody SysResource sysResource) {
        return success(this.sysResourceService.updateById(sysResource));
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.sysResourceService.removeByIds(idList));
    }
}

