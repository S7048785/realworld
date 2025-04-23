package com.realworld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realworld.context.BaseContext;
import com.realworld.dao.UserDao;
import com.realworld.dao.UserLoginDTO;
import com.realworld.entity.User;
import com.realworld.mapper.UserMapper;
import com.realworld.service.UserService;
import com.realworld.vo.ProfileVO;
import com.realworld.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YYJYP
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Override
	public UserVO login(UserLoginDTO userLoginDTO) {
		return null;
	}

	@Override
	public ProfileVO getInfo(Integer id) {

		return userDao.getInfo(id, BaseContext.getCurrentId());
	}
}
