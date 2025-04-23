package com.realworld.service;

import com.realworld.dao.UserLoginDTO;
import com.realworld.vo.ProfileVO;
import com.realworld.vo.UserVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
public interface UserService {

	UserVO login(UserLoginDTO userLoginDTO);

	ProfileVO getInfo(Integer id);
}
