package com.realworld.mapper;

import com.realworld.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.realworld.vo.ProfileVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

	ProfileVO selectUser(Integer userId, Integer currentId);
}
