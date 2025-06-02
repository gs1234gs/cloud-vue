package com.tianchang;

import com.tianchang.userInfoInterceptor.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 将拦截器添加到Spring容器中
 * */
@Configuration
@ConditionalOnClass(DispatcherServlet.class)
public class WebMvcConfigAutoConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new UserInfoInterceptor());
    }
}
