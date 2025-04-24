package com.realworld.service.Tran;

import com.realworld.dao.UserDao;
import com.realworld.dao.UserFollowDao;
import com.realworld.entity.UserFollow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 解决事务失效问题
 */
@Service
public class UserServiceTran {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserFollowDao userFollowDao;


	@Transactional
	public void follow(Integer currentId, Integer id) {
		// 查询有没有关注
		UserFollow followed = userFollowDao.isFollowed(currentId, id);
		if (followed == null) {
			// 新增用户关注数据
			UserFollow userFollow = new UserFollow().setUserId(currentId).setFollowedUserId(id);
			userFollowDao.saveFollow(userFollow);
		} else {
			// 更新关注状态
			userFollowDao.updateFollow(currentId, id, followed.getIsDel());
		}
		// 更新 关注数 和 粉丝数

		// 数量减一还是加一
		boolean b = followed == null || !followed.getIsDel();
		userDao.updateFollowCount(currentId, b);
		userDao.updateFansCount(id, b);
	}
}
