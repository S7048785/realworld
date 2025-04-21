package com.realworld.service;

import com.realworld.dto.UserLoginDTO;
import com.realworld.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.realworld.vo.UserVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
public interface IUserService extends IService<User> {

	UserVO login(UserLoginDTO userLoginDTO);
}
