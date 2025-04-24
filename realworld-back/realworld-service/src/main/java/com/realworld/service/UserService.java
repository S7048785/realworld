package com.realworld.service;

import com.realworld.dao.UserLoginDTO;
import com.realworld.dao.UserRegisterDTO;
import com.realworld.dao.UserUpdateDTO;
import com.realworld.vo.ProfileVO;
import com.realworld.vo.UserLoginVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
public interface UserService {


	ProfileVO getInfo(Integer id);

	void follow(Integer id);

	UserLoginVO login(UserLoginDTO userLoginDTO);

	UserLoginVO register(UserRegisterDTO userRegisterDTO);

	ProfileVO updateInfo(UserUpdateDTO userDTO);
}
