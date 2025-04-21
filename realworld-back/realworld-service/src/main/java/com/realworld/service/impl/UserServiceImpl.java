package com.realworld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realworld.dto.UserLoginDTO;
import com.realworld.entity.User;
import com.realworld.mapper.UserMapper;
import com.realworld.service.IUserService;
import com.realworld.vo.UserVO;
import org.springframework.stereotype.Service;

/**
 * @author YYJYP
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	@Override
	public UserVO login(UserLoginDTO userLoginDTO) {
		return null;
	}
}
