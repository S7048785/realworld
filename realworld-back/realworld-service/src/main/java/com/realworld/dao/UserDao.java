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
		return userMapper.selectUser(id, currentId);
	}

	/**
	 * 更新关注数
	 * @param currentId
	 * @param b true 加一；false 减一
	 */
	public void updateFollowCount(Integer currentId, boolean b) {
		userMapper.update(Wrappers.<User>lambdaUpdate()
				.eq(User::getId, currentId)
				.setSql("follow_count = follow_count " + (b ? "+" : "-") + " 1"));
	}

	/**
	 * 更新粉丝数
	 * @param currentId
	 * @param b true 加一；false 减一
	 */
	public void updateFansCount(Integer currentId, boolean b) {
		userMapper.update(Wrappers.<User>lambdaUpdate()
				.eq(User::getId, currentId)
				.setSql("fans_count = fans_count " + (b ? "+" : "-") + " 1"));
	}

	public User getUserInfo(String username, String password) {
		return userMapper.selectOne(Wrappers.<User>lambdaQuery()
				.eq(User::getUsername, username)
				.eq(User::getPassword, password));
	}
}
