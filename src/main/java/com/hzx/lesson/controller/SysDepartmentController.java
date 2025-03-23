package com.hzx.lesson.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzx.lesson.model.entity.SysDepartment;
import com.hzx.lesson.service.SysDepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 组织部门(SysDepartment)表控制层
 * @author makejava
 * @since 2025-03-23 21:50:38
 */
@RestController
@RequestMapping("sysDepartment")
public class SysDepartmentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysDepartmentService sysDepartmentService;

    /**
     * 分页查询所有数据
     * @param page          分页对象
     * @param sysDepartment 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<SysDepartment> page, SysDepartment sysDepartment) {
        return success(this.sysDepartmentService.page(page, new QueryWrapper<>(sysDepartment)));
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.sysDepartmentService.getById(id));
    }

    /**
     * 新增数据
     * @param sysDepartment 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody SysDepartment sysDepartment) {
        return success(this.sysDepartmentService.save(sysDepartment));
    }

    /**
     * 修改数据
     * @param sysDepartment 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody SysDepartment sysDepartment) {
        return success(this.sysDepartmentService.updateById(sysDepartment));
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.sysDepartmentService.removeByIds(idList));
    }
}

