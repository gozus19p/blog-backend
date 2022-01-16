package it.manuelgozzi.blog.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author Manuel Gozzi
 */
@Component
@Configuration
open class WebConfiguration(var webInterceptor: WebInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {

        registry.addInterceptor(this.webInterceptor).addPathPatterns("/**")
    }
}