package com.realworld.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.realworld.constant.CacheConstant;
import com.realworld.constant.UserConstant;
import com.realworld.context.BaseContext;
import com.realworld.dao.UserDao;
import com.realworld.dao.UserFollowDao;
import com.realworld.dto.*;
import com.realworld.entity.User;
import com.realworld.exception.BaseException;
import com.realworld.service.Tran.UserServiceTran;
import com.realworld.service.UserService;
import com.realworld.utils.CacheUtil;
import com.realworld.utils.JwtUtil;
import com.realworld.vo.ProfileVO;
import com.realworld.vo.UserLoginVO;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author YYJYP
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserFollowDao userFollowDao;
	@Autowired
	private RedissonClient redissonClient;
	@Autowired
	private CacheUtil cacheUtil;
	@Autowired
	private UserServiceTran userServiceTran;



	@Override
	public ProfileVO getInfo(String username) {
		// TODO: 查询redis中的用户信息
//		String str = cacheUtil.getStr(CacheConstant.USER_INFO + id);
//		if (StrUtil.isNotBlank(str)) {
//			return JSONUtil.toBean(str, ProfileVO.class);
//		}
		ProfileVO profile = userDao.getProfile(username, BaseContext.getCurrentId());
		if (profile == null) {
			throw new BaseException(UserConstant.USER_NOT_EXIST);
		}
		// 存入redis
//		cacheUtil.setStr(CacheConstant.USER_INFO + id, JSONUtil.toJsonStr(profile));
		return profile;
	}

	@Override
	public void follow(Integer id) {
		Integer currentId = BaseContext.getCurrentId();
		RLock lock = redissonClient.getLock(CacheConstant.CACHE_FOLLOW + currentId);

		try {
			boolean isLock = lock.tryLock(1, 30, TimeUnit.SECONDS);
			 if (!isLock) {
				// 未获取到锁 直接返回
				return;
			}
			userServiceTran.follow(currentId, id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 只有在成功获取锁时才解锁
			if (lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
	}

	@Override
	public UserLoginVO login(UserLoginDTO userLoginDTO) {
		String str = cacheUtil.getStr("user");
		if (StrUtil.isNotBlank(str)) {
			return JSONUtil.toBean(str, UserLoginVO.class);
		}

		// TODO: 校验验证码

		// 查询用户
		String username = userLoginDTO.getUsername();
		String password = userLoginDTO.getPassword();
		// TODO: 密码加密
//		password = DigestUtil.md5Hex(password.getBytes());
		User user = userDao.getUserInfo(username, password);
		if (user == null || !user.getPassword().equals(password)) {
			throw new BaseException(UserConstant.USER_NOT_EXIST_OR_PASSWORD_ERROR);
		}

		// 封装成VO
		UserLoginVO userLoginVO = BeanUtil.copyProperties(user, UserLoginVO.class);

		// 生成token
		Map<String, Object> userClaims = Map.of("id", user.getId(), "username", user.getUsername());
		String token = JwtUtil.createJwt(userClaims);
		userLoginVO.setToken(token);
		return userLoginVO;
	}

	@Override
	public UserLoginVO register(UserRegisterDTO userRegisterDTO) {
		// TODO: 校验验证码

		// TODO: 密码加密

		// 查询用户是否已存在
		User userInfo = userDao.getUserInfo(userRegisterDTO.getUsername(), userRegisterDTO.getPassword());
		if (userInfo != null) {
			throw new BaseException(UserConstant.USER_EXIST);
		}

		User user = BeanUtil.copyProperties(userRegisterDTO, User.class);
		// 存入数据库
		userDao.save(user);

		// 生成token
		String token = JwtUtil.createJwt(Map.of("id", user.getId(), "username", user.getUsername()));
		// 封装VO
		UserLoginVO userLoginVO = BeanUtil.copyProperties(user, UserLoginVO.class);
		userLoginVO.setToken(token);
		// 存入Redis
//		cacheUtil.setStr(CacheConstant.USER_LOGIN_TOKEN + token, JSONUtil.toJsonStr(userLoginVO));
		return userLoginVO;
	}

	@Override
	public ProfileVO updateInfo(UserUpdateDTO userDTO) {
		User user = BeanUtil.copyProperties(userDTO, User.class).setId(BaseContext.getCurrentId());
		boolean isUpdated = userDao.updateById(user);
		if (isUpdated) {
			// 更新成功 获取新的用户信息
			User userNew = userDao.getById(user.getId());
			// 封装成ProfileVO
			ProfileVO profileVO = BeanUtil.copyProperties(userNew, ProfileVO.class);
			profileVO.setFollowing(false);
			return profileVO;
		}
		throw new BaseException("用户信息更新失败");
	}

	@Override
	public void logout(String token) {
		// 获取token
		cacheUtil.setStr(CacheConstant.USER_TOKEN_BLACKLIST + token, "1", 28800);
	}

}
