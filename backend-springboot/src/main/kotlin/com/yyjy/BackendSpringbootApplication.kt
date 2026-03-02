package com.yyjy

import com.yyjy.common.ExcludePathProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(ExcludePathProperties::class)
@SpringBootApplication
class BackendSpringbootApplication

fun main(args: Array<String>) {
	runApplication<BackendSpringbootApplication>(*args)
}
