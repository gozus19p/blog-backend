package it.manuelgozzi.blog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Manuel Gozzi
 */
@Component
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final WebInterceptor webInterceptor;

    @Autowired
    public WebConfiguration(WebInterceptor webInterceptor) {
        this.webInterceptor = webInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(this.webInterceptor).addPathPatterns("/**");
    }
}
