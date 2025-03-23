package com.hzx.lesson.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzx.lesson.model.entity.SysOrganization;
import org.mapstruct.Mapper;


/**
 * 用户(SysOrganization)表数据库访问层
 *
 * @author makejava
 * @since 2025-03-23 21:50:38
 */
@Mapper
public interface SysOrganizationMapper extends BaseMapper<SysOrganization> {

}

