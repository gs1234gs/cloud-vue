package com.tianchang.memory;

import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.MEMORYSTATUSEX;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WindowsCacheMonitor {

    public static void main(String[] args) {
        try {
            // 获取内存信息
            MEMORYSTATUSEX memInfo = new MEMORYSTATUSEX();
            memInfo.dwLength = new WinDef.DWORD(memInfo.size());
            Kernel32.INSTANCE.GlobalMemoryStatusEx(memInfo);

            long totalPhysMem = memInfo.ullTotalPhys.longValue();
            long physMemUsed = memInfo.ullTotalPhys.longValue() - memInfo.ullAvailPhys.longValue();

            System.out.println("总物理内存: " + formatMemory(totalPhysMem));
            System.out.println("已用物理内存: " + formatMemory(physMemUsed));
            System.out.println("可用物理内存: " + formatMemory(memInfo.ullAvailPhys.longValue()));

            // 获取缓存统计信息(更精确的方法需要更复杂的API调用)
            // 这里只是示例，实际实现需要更多Windows API调用
            WindowsCacheMonitor.CacheHitInfo hitInfo =
                    WindowsCacheMonitor.getCacheHitRate();

            System.out.println("缓存命中率: " + hitInfo.hitRate() + "%");
            System.out.println("缓存未命中率: " + hitInfo.missRate() + "%");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String formatMemory(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        char pre = "KMGTPE".charAt(exp-1);
        return String.format("%.1f %sB", bytes / Math.pow(1024, exp), pre);
    }
    // 获取缓存命中率（需要管理员权限）
    public static CacheHitInfo getCacheHitRate() {
        try {
            // 执行 PowerShell 命令获取性能计数器数据
            Process process = Runtime.getRuntime().exec(
                    "powershell -Command \"& {" +
                            "$hit = Get-Counter '\\Cache\\Copy Read Hits %' " +
                            "| Select-Object -ExpandProperty CounterSamples " +
                            "| Select CookedValue;" +
                            "$miss = Get-Counter '\\Cache\\Copy Read Misses %' " +
                            "| Select-Object -ExpandProperty CounterSamples " +
                            "| Select CookedValue;" +
                            "Write-Output ($hit.CookedValue.ToString() + ',' + $miss.CookedValue.ToString())" +
                            "}\""
            );

            // 读取命令输出
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            String output = reader.readLine();

            // 解析结果
            if (output != null && output.contains(",")) {
                String[] parts = output.split(",");
                double hitRate = Double.parseDouble(parts[0]);
                double missRate = Double.parseDouble(parts[1]);
                CacheHitInfo cacheHitInfo = new CacheHitInfo(hitRate, missRate);
                return cacheHitInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CacheHitInfo(0, 0);
    }
    // 数据结构
    public record CacheHitInfo(double hitRate, double missRate) {}
}