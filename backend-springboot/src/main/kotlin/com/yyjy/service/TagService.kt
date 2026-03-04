package com.yyjy.service

import com.yyjy.repository.TagRepository
import org.springframework.stereotype.Service

@Service
class TagService(
    private val tagRepository: TagRepository
) {

    /**
     * 获取所有标签
     */
     fun getAllTags(): List<String> {
        return tagRepository.findAll().map { it.name }
    }
}