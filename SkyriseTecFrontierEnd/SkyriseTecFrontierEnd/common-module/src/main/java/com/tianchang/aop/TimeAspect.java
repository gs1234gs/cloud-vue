package com.tianchang.aop;//package com.guanshiyun.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
//@Slf4j
//@Component
//@Aspect//AOP 切面
//public class TimeAspect {
//
//    @Around("execution(* com.guanshiyun.service.*.*(..))")
//    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
//       // 记录当前时间
//        long startTime = System.currentTimeMillis();
//
//
//        // 调用方法执行获取方法执行时长
//
//        Object result = joinPoint.proceed();
//
//
//        // 记录方法执行时长
//
//        long endTime = System.currentTimeMillis();
//
//
//        // 打印日志
//        log.info("{}方法执行时长：{} ms",joinPoint.getSignature(), endTime - startTime);
//        // 返回方法执行结果
//        return result;
//    }
//}
