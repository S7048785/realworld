package com.realworld.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.realworld.constant.CacheConstant;
import com.realworld.dao.TagsDao;
import com.realworld.entity.Tags;
import com.realworld.service.TagsService;
import com.realworld.utils.CacheUtil;
import com.realworld.vo.TagsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author YYJYP
 */
@Service
public class TagsServiceImpl implements TagsService {

	@Autowired
	private TagsDao tagsDao;
	/**
	 * 返回所有标签名
	 * @return
	 */
	@Autowired
	private CacheUtil cacheUtil;


	@Override
	public String listTag() {
		String str = cacheUtil.getStr(CacheConstant.CACHE_TAGS);
		if (StrUtil.isBlank(str)) {
			List<Tags> list = tagsDao.list();
			// 存入Redis
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < list.size(); i++) {
				stringBuilder.append(list.get(i).getName());
				if (i < list.size() - 1) {
					stringBuilder.append(",");
				}
			}
			cacheUtil.setStr(CacheConstant.CACHE_TAGS, stringBuilder.toString());
			return stringBuilder.toString();
		}
		return str;
	}

}
