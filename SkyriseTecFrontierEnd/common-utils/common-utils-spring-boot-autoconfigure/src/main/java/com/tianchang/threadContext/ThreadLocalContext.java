package com.tianchang.threadContext;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ThreadLocal 工具类，用于管理线程上下文信息。
 */
public class ThreadLocalContext {

    private static final Logger logger = Logger.getLogger(ThreadLocalContext.class.getName());

    // 使用 ThreadLocal 存储字符串类型的上下文信息
    private static final ThreadLocal<String> stringContext = ThreadLocal.withInitial(() -> "default");

    // 使用 ThreadLocal 存储对象类型的上下文信息
    private static final ThreadLocal<Object> objectContext = new ThreadLocal<>();

    /**
     * 设置字符串类型的上下文信息。
     *
     * @param value 上下文信息值
     */
    public static void setStringContext(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Context value cannot be null");
        }
        stringContext.set(value);
        logger.log(Level.INFO, "String context set to: {0}", value);
    }

    /**
     * 获取字符串类型的上下文信息。
     *
     * @return 上下文信息值
     */
    public static String getStringContext() {
        String value = stringContext.get();
        logger.log(Level.INFO, "Current string context: {0}", value);
        return value;
    }

    /**
     * 设置对象类型的上下文信息。
     *
     * @param value 上下文信息值
     */
    public static void setObjectContext(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Context value cannot be null");
        }
        objectContext.set(value);
        logger.log(Level.INFO, "Object context set to: {0}", value);
    }

    /**
     * 获取对象类型的上下文信息。
     *
     * @return 上下文信息值
     */
    public static Object getObjectContext() {
        Object value = objectContext.get();
        logger.log(Level.INFO, "Current object context: {0}", value);
        return value;
    }

    /**
     * 清除字符串类型的上下文信息。
     */
    public static void clearStringContext() {
        String value = stringContext.get();
        stringContext.remove();
        logger.log(Level.INFO, "String context cleared: {0}", value);
    }

    /**
     * 清除对象类型的上下文信息。
     */
    public static void clearObjectContext() {
        Object value = objectContext.get();
        objectContext.remove();
        logger.log(Level.INFO, "Object context cleared: {0}", value);
    }

    /**
     * 检查当前字符串上下文是否为默认值。
     *
     * @return 如果为默认值返回 true，否则返回 false
     */
    public static boolean isDefaultStringContext() {
        return "default".equals(stringContext.get());
    }

    /**
     * 检查当前对象上下文是否为空。
     *
     * @return 如果为空返回 true，否则返回 false
     */
    public static boolean isEmptyObjectContext() {
        return objectContext.get() == null;
    }
}
