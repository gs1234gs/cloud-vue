package com.tianchang.config;

//
//
//import guanshiyun.interceptor.LoginCheckInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//@ComponentScan(basePackages = "guanshiyun")
//public class WebConfig implements WebMvcConfigurer {
//    // 拦截器
//    @Autowired
//     LoginCheckInterceptor loginCheckInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        registry.addInterceptor(loginCheckInterceptor)
//                .addPathPatterns("/**")
//               .excludePathPatterns("/login");
//    }
//}
