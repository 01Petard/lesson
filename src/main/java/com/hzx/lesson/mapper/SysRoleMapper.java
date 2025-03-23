package com.hzx.lesson.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzx.lesson.model.entity.SysRole;
import org.mapstruct.Mapper;

/**
 * 角色表(SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2025-03-23 21:50:38
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

}

