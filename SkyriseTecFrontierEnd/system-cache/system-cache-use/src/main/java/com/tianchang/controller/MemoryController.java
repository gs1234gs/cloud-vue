package com.tianchang.controller;

import com.tianchang.memory.MemoryMonitorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memory")
public class MemoryController {

    private final MemoryMonitorService memoryMonitorService;

    public MemoryController(MemoryMonitorService memoryMonitorService) {
        this.memoryMonitorService = memoryMonitorService;
    }

    /**
     * 获取内存使用情况
     *
     * @return
     */

    @GetMapping("/usage")
    public MemoryMonitorService.MemoryInfo getMemoryUsage() {
        return memoryMonitorService.getMemoryUsage();
    }
}
