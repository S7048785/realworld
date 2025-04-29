package com.realworld.service;

import com.realworld.dto.UserLoginDTO;
import com.realworld.dto.UserRegisterDTO;
import com.realworld.dto.UserUpdateDTO;
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


	ProfileVO getInfo(String username);

	void follow(Integer id);

	UserLoginVO login(UserLoginDTO userLoginDTO);

	UserLoginVO register(UserRegisterDTO userRegisterDTO);

	ProfileVO updateInfo(UserUpdateDTO userDTO);

	void logout(String token);
}
