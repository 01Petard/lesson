package com.hzx.lesson.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzx.lesson.model.entity.SysUserRole;
import org.mapstruct.Mapper;

/**
 * 角色表关联用户(SysUserRole)表数据库访问层
 *
 * @author makejava
 * @since 2025-03-23 21:50:38
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}

