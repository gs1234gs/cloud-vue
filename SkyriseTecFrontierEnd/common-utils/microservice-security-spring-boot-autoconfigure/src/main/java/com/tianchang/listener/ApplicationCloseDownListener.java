package com.tianchang.listener;

import com.tianchang.constant.ConstClassNickName;
import com.tianchang.redisConfig.RedisUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
/**
 * 关闭项目监听器，Spring 容器关闭时触发
 * 该类实现了 Spring 的 ApplicationListener 接口，用于监听 ContextClosedEvent 事件。
 * 当 Spring 容器关闭时（例如应用正常关闭或 JVM 停止），会触发该监听器。
 */
@Slf4j // 使用 Lombok 提供的日志功能，简化日志记录
public class ApplicationCloseDownListener implements ApplicationListener<ContextClosedEvent> {

    @Resource // 注入 Redis 工具类（需要确保 RedisUtil 已被 Spring 容器管理）
    private RedisUtil redisUtil;
    @Resource
     private RedisCacheCleanerService redisCacheCleanerService;


    /**
     * 当 Spring 容器关闭时，该方法会被调用。
     * @param event ContextClosedEvent 事件对象，包含容器关闭的相关信息。
     */
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        // 打印日志，表明项目正在关闭
        log.info("项目关闭，清理 Redis 缓存...{}", event);
        // 调用 Redis 工具类，删除指定的 Redis 缓存键
        // ConstClassNickName.REDIS_TOKEN_KEY 是一个常量，表示需要删除的 Redis 键，关闭时会清除该键对应的缓存。
        redisUtil.delete(ConstClassNickName.REDIS_TOKEN_KEY);
        // 调用服务层的清理方法，定时清理 Redis 缓存
        redisCacheCleanerService.cleanCache();
        log.info("Redis 缓存清理完成{}",event);
        // 如果需要，可以添加额外的清理逻辑，例如关闭资源、释放锁等
    }
}
