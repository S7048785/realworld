package com.realworld.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realworld.entity.User;
import com.realworld.mapper.UserMapper;
import com.realworld.vo.ProfileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao extends ServiceImpl <UserMapper, User>{
	@Autowired
	private UserMapper userMapper;
	public ProfileVO getInfo(Integer id, Integer currentId) {
		return userMapper.selectUser(id, currentId);
	}
}
