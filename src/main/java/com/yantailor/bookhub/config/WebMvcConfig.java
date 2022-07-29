package com.yantailor.bookhub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by yantailor
 * on 2022/7/7 16:35 @Version 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mvc/**").addResourceLocations("file:E:/");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//    }
}
