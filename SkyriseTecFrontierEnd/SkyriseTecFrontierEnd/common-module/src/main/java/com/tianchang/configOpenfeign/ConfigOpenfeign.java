package com.tianchang.configOpenfeign;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@EnableFeignClients(defaultConfiguration = ConfigOpenfeign.class)
public class ConfigOpenfeign {
    // 可选：自定义Feign配置
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL; // 开启Feign请求日志
    }

    // 可选：配置全局超时时间
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.header("X-Custom-Header", "value");
    }

}
