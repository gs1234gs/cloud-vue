//package com.tianchang.redisConfig;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Slf4j
//@Configuration
//public class RedisConfig implements CommandLineRunner {
//
//    // 1. Redis 连接工厂配置
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        // Lettuce 是 Spring Data Redis 默认的客户端，比 Jedis 更高效且线程安全
//        return new LettuceConnectionFactory();
//    }
//
//    // 2. RedisTemplate 配置
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        // 使用 String 序列化键，保证键的可读性
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//
//        // 使用 Jackson2JsonRedisSerializer 序列化值
//        Jackson2JsonRedisSerializer<Object> jsonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        template.setValueSerializer(jsonSerializer);
//        template.setHashValueSerializer(jsonSerializer);
//        template.afterPropertiesSet(); // 初始化模板配置
//        return template;
//    }
//
//    // 实现 CommandLineRunner 接口来在启动后执行代码,检测 Redis 连接时候成功
//    @Override
//    public void run(String... args) throws Exception {
//        RedisConnectionFactory factory = redisConnectionFactory();
//        try (RedisConnection connection = factory.getConnection()) {
//            log.info("Successfully connected to Redis!{}", connection);
//            // 你可以在这里执行一些额外的 Redis 操作来进一步验证连接，比如 PING
//            // 注意：Lettuce/Jedis 连接通常不需要显式 PING，因为 getConnection() 成功即表示连接可用
//        } catch (Exception e) {
//            log.error("Failed to connect to Redis: {}", e.getMessage(), e);
//            // 根据需要，你可以在这里抛出异常或采取其他措施
//        }
//    }
//}