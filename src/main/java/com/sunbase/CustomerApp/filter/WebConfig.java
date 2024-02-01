package com.sunbase.CustomerApp.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * WebConfig class to configure and add interceptors.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * Method to add custom interceptors to the application.
     *
     * @param registry InterceptorRegistry object to register interceptors.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor());
    }
}
