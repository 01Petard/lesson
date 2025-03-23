package com.hzx.lesson.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzx.lesson.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
/**
 * @author zexiao.huang
 * @since 2025/3/23 下午3:56
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}