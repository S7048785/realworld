package com.realworld.dao;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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

	public ProfileVO getProfile(Integer id, Integer currentId) {
		return userMapper.getProfile(id, currentId);
	}

	public User getUserInfo(String username, String password) {
		return userMapper.selectOne(Wrappers.<User>lambdaQuery()
				.eq(User::getUsername, username)
				.eq(User::getPassword, password));
	}

	public ProfileVO getProfileByArticleId(Integer articleId, Integer userId) {
		return userMapper.getProfileByArticleId(articleId, userId);
	}
}
