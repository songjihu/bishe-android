package com.sjh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源映射路径
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置本地访问路径 路径后8080/直接加文件路径
        registry.addResourceHandler("/temp-rainy/**").addResourceLocations("file:J:/temp-rainy/");
        //registry.addResourceHandler("/Desktop/**").addResourceLocations("file:C:/Users/Administrator/Desktop/");
    }
}
