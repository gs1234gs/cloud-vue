//package com.tianchang.listenerConfig;
//
//import com.tianchang.listener.ApplicationCloseDownListener;
//import com.tianchang.listener.RedisCacheCleanerService;
//import com.tianchang.redisConfig.RedisUtil;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
///**
// * 监听器配置
// * */
//@Configuration
//public class ListenerConfig {
//    // 关闭监听器
//    @Bean
//    public ApplicationCloseDownListener applicationCloseDownListener() {
//        return new ApplicationCloseDownListener();
//    }
//    //定时清理 Redis 缓存
//    @Bean
//    public RedisCacheCleanerService redisCacheCleanerService(RedisUtil redisUtil) {
//        return new RedisCacheCleanerService(redisUtil);
//    }
//}
