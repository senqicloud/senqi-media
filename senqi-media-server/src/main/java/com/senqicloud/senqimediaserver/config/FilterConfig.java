package com.senqicloud.senqimediaserver.config;

import com.senqicloud.senqimediaserver.filter.JwtAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilter() {
        FilterRegistrationBean<JwtAuthenticationFilter> registration = new FilterRegistrationBean<>();

        // 设置自定义 Filter
        registration.setFilter(new JwtAuthenticationFilter());

        // 设置拦截路径
        registration.addUrlPatterns("/*");

        // 设置执行顺序，值越小优先级越高
        registration.setOrder(1);

        return registration;
    }
}
