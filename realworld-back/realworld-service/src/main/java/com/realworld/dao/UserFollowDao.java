package com.realworld.dao;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realworld.entity.UserFollow;
import com.realworld.mapper.UserFollowMapper;
import org.springframework.stereotype.Service;

@Service
public class UserFollowDao extends ServiceImpl<UserFollowMapper, UserFollow> {

	/**
	 * 判断用户是否关注了某个用户
	 * @param userId
	 * @param followedUserId
	 * @return
	 */
	public UserFollow isFollowed(Integer userId, Integer followedUserId) {
		return getOne(Wrappers.lambdaQuery(UserFollow.class)
				.eq(UserFollow::getUserId, userId)
				.eq(UserFollow::getFollowedUserId, followedUserId));
	}


	/**
	 * 更新关注关系
	 * @param userId
	 * @param followedUserId
	 * @param isDel
	 */
	public void updateFollow(Integer userId, Integer followedUserId, int isDel) {
		update(Wrappers.lambdaUpdate(UserFollow.class)
				.set(UserFollow::getIsDel, isDel == 1 ? 0 : 1)
				.eq(UserFollow::getUserId, userId)
				.eq(UserFollow::getFollowedUserId, followedUserId));
	}

	/**
	 * 新增关注关系
	 */
	public void saveFollow(UserFollow userFollow) {
		save(userFollow);
	}

}
