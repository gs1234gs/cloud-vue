package com.tianchang.memory;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
@Service
public class MemoryMonitorService {

    /**
     * 获取当前 JVM 的内存使用情况。
     *
     * 该方法通过 Java Management Extensions (JMX) 获取堆内存和非堆内存的使用情况，
     * 并将这些信息转换为以 MB 为单位的易读格式返回。
     *
     * @return 包含内存使用信息的 MemoryInfo 对象，包括：
     *         - 堆内存已使用量 (heapUsed)
     *         - 堆内存已提交量 (heapCommitted)
     *         - 堆内存最大量 (heapMax)
     *         - 非堆内存已使用量 (nonHeapUsed)
     *         - 非堆内存已提交量 (nonHeapCommitted)
     *         - 非堆内存最大量 (nonHeapMax)
     */
    public MemoryInfo getMemoryUsage() {
        // 获取 MemoryMXBean 实例，用于监控 JVM 内存使用情况
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        // 获取堆内存使用情况
        // 堆内存是 JVM 中用于存储对象实例的区域
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();

        // 获取非堆内存使用情况
        // 非堆内存包括方法区、JVM 内部使用的内存等
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

        // 返回一个 MemoryInfo 对象，包含内存使用信息
        // 将字节转换为 MB（1 MB = 1024 KB = 1024 * 1024 B）
        return new MemoryInfo(
                heapMemoryUsage.getUsed() / (1024 * 1024),  // 堆内存已使用量（MB）
                heapMemoryUsage.getCommitted() / (1024 * 1024),  // 堆内存已提交量（MB）
                heapMemoryUsage.getMax() / (1024 * 1024),  // 堆内存最大量（MB）
                nonHeapMemoryUsage.getUsed() / (1024 * 1024),  // 非堆内存已使用量（MB）
                nonHeapMemoryUsage.getCommitted() / (1024 * 1024),  // 非堆内存已提交量（MB）
                nonHeapMemoryUsage.getMax() / (1024 * 1024)  // 非堆内存最大量（MB）
        );
    }

    @Data
    @AllArgsConstructor
    public static class MemoryInfo {
        private final long heapUsed;
        private final long heapCommitted;
        private final long heapMax;
        private final long nonHeapUsed;
        private final long nonHeapCommitted;
        private final long nonHeapMax;
    }
}
