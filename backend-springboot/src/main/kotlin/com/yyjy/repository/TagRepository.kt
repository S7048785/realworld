package com.yyjy.repository

import com.yyjy.models.entity.Tags
import org.babyfish.jimmer.spring.repository.KRepository

interface TagRepository : KRepository<Tags, Int>