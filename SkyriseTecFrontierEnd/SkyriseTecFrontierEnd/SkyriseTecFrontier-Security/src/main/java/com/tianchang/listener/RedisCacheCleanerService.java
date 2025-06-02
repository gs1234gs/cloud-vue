//package com.tianchang.listener;
//
//import com.tianchang.constant.ConstClassNickName;
//import com.tianchang.redisConfig.RedisUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
//
//@Slf4j
//public class RedisCacheCleanerService {
//
//    private final RedisUtil redisUtil;
//
//    // 构造器注入（推荐方式）
//    public RedisCacheCleanerService(RedisUtil redisUtil) {
//        this.redisUtil = redisUtil;
//    }
//
//    /**
//     * 定时清理Redis缓存（每天凌晨2点执行）
//     * cron表达式格式：秒 分 时 日 月 周(年)
//     */
//    @Scheduled(cron = "0 0 2 * * ?")
//    public void scheduledCleanCache() {
//        try {
//            redisUtil.delete(ConstClassNickName.REDIS_TOKEN_KEY);
//            log.info("定时清理Redis缓存完成，键：{}", ConstClassNickName.REDIS_TOKEN_KEY);
//        } catch (Exception e) {
//            log.error("定时清理Redis缓存失败", e);
//        }
//    }
//
//    /**
//     * 保留原有清理逻辑（供关闭监听器调用）
//     */
//    public void cleanCache() {
//        redisUtil.delete(ConstClassNickName.REDIS_TOKEN_KEY);
//        log.info("手动清理Redis缓存完成，键：{}", ConstClassNickName.REDIS_TOKEN_KEY);
//    }
//}
