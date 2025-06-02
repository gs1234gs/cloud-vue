//package com.tianchang.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//public class CrossConfiguration {
//
//    /**
//     * 定义一个 CorsFilter Bean，用于全局配置 CORS（跨域资源共享）。
//     *
//     * @return 配置好的 CorsFilter 对象
//     */
//    @Bean
//    public CorsFilter corsFilter() {
//        // 创建 CORS 配置对象
//        CorsConfiguration config = new CorsConfiguration();
//
//        // 允许所有源（域名）跨域访问
//        // 在生产环境中，建议指定具体的源以增强安全性
//        config.addAllowedOrigin("http://127.0.0.1:9000");
//        config.addAllowedOrigin("http://localhost:9000");
//        // 允许所有请求头
//        config.addAllowedHeader("*");
//
//        // 允许所有 HTTP 方法（GET, POST, PUT, DELETE, OPTIONS 等）
//        config.addAllowedMethod("*");
//
//        // 是否允许发送 Cookie 等凭证信息
//        config.setAllowCredentials(true);
//
//        // 预检请求的缓存时间（秒），在此时间内，浏览器不需要再次发送预检请求
//        config.setMaxAge(3600L);
//
//        // 创建 URL 基础的 CORS 配置源
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        // 对所有路径应用 CORS 配置
//        source.registerCorsConfiguration("/**", config);
//
//        // 返回 Spring 提供的 CORS 过滤器
//        return new CorsFilter(source);
//    }
//}