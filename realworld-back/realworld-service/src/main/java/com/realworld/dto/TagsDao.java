package com.realworld.dto;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realworld.entity.Tags;
import com.realworld.mapper.TagsMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class TagsDao extends ServiceImpl<TagsMapper, Tags>{

	@Override
	public List<Tags> list() {
		return super.list(Wrappers.lambdaQuery(Tags.class).eq(Tags::getIsDel, 0));
	}
	public <E> List<E> listObjs(List<String> tagNameList) {
		return super.listObjs(Wrappers.lambdaQuery(Tags.class)
				.select(Tags::getName)
				.in(Tags::getName, tagNameList)
				.eq(Tags::getIsDel, 0));
	}

}
