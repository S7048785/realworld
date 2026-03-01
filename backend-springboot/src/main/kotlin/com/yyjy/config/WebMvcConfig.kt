package com.yyjy.config

import com.yyjy.interceptor.TokenInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.io.File

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(TokenInterceptor()) // 拦截所有路径
            .addPathPatterns("/**")
            .excludePathPatterns(
                "/user/login",
            )
            .excludePathPatterns( // Swagger相关路径
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v3/**",
                "/favicon.ico"
            )
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/systemPictures/**")
            .addResourceLocations("file:" + System.getProperty("user.dir") + File.separator + "uploadFile" + File.separator + "systemPictures" + File.separator);
        registry.addResourceHandler("/uploadFile/pluginFiles/logo/**")
            .addResourceLocations("file:" + System.getProperty("user.dir") + File.separator + "uploadFile" + File.separator + "pluginFiles" + File.separator + "logo" + File.separator);

        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}