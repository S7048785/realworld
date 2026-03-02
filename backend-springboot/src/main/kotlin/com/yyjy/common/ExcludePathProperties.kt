package com.yyjy.common

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * @author Nyxcirea
 * @date 2026/3/2
 * @description: TODO
 */

@ConfigurationProperties("demo.exclude")
data class ExcludePathProperties(
    val paths: List<String>
)
