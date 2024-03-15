package com.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 添加路径规则
                .allowCredentials(true)               // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")           // 允许请求来源的域规则
                .allowedMethods("*")
                .allowedHeaders("*") ;                // 允许所有的请求头
    }


    private LoginFilter loginFilter;
    @Autowired
    public WebConfig(LoginFilter loginFilter) {
        this.loginFilter = loginFilter;
    }

    // 添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludePathPatterns = {
                "/api/user/login",
                "/api/file/download/**",
                "/api/file/face",
                "/api/user/register",
                "/api/compare/face/export/**",
                "/doc.html",
                "/v3/api-docs/swagger-config",
                "/v3/api-docs/API",
                "/api/captcha",
                "/webjars/**",
                "/favicon.ico",
        };
        registry.addInterceptor(loginFilter)
                .excludePathPatterns("/**")
                .addPathPatterns("");
    }
}
