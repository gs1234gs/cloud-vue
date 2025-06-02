//package guanshiyun.aop;//package com.guanshiyun.aop;
//
//import com.alibaba.fastjson.JSONObject;
//import com.aliyun.oss.JwtUtils;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import guanshiyun.mapper.OperateLogMapper;
//import guanshiyun.pojo.OperateLog;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.Map;
//
//
//@Slf4j
//@Component
//@Aspect//AOP 切面*/
//public class LogAspect {
//
//    // 用于JSON序列化和反序列化
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Autowired
//    private OperateLogMapper operateLogMapper;
//
//    @Autowired
//    private HttpServletRequest request;
//
//
//    @Around("@annotation(guanshiyun.anno.Log)")
//        public Object recordLog(
//                ProceedingJoinPoint joinPoint) throws Throwable {
//
//        //操作人ID
//
//        //获取请求头中的token令牌，获取用户id
//        String jwt = null;
//        try {
//            jwt = request.getHeader("token");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        Claims payload = JwtUtils.checkToken(jwt);
//
//        // 反序列化"name" claim，该claim存储了序列化的Map
//        String mapJson = payload.get("token", String.class);
//
//        // 反序列化 claim
//        Map<String, Object> userMap = objectMapper.readValue(
//                mapJson, new TypeReference<Map<String, Object>>() {});
//
//        int id = (int) userMap.get("id");
//
//        // 操作时间
//        LocalDateTime operateTime = LocalDateTime.now();
//        // 操作类名
//        String className = joinPoint.getTarget().getClass().getName();
//        // 操作方法名
//        String methodName = joinPoint.getSignature().getName();
//        // 操作参数
//        Object[] args = joinPoint.getArgs();
//        String methodParams = Arrays.toString(args);
//        // 操作返回值
//
//       Object result = joinPoint.proceed();
//        String resultValue = JSONObject.toJSONString(result);
//
//        // 操作耗时
//        long startTime = System.currentTimeMillis();
//        long endTime = System.currentTimeMillis();
//        long costTime = endTime - startTime;
//        //
//
//        //调用原始方法
//        Object proceed = joinPoint.proceed();
//
//        //记录日志
//OperateLog operateLog = new OperateLog(
//        null, id, operateTime, className, methodName,
//        methodParams, resultValue, costTime);
//
//        operateLogMapper.insert(operateLog);
//        // 日志输出
//        log.info("AOP记录操作日志{}", operateLog);
//        return proceed;
//
//    }
//
//}
