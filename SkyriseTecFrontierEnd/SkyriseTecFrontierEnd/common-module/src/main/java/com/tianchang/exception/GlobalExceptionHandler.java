package com.tianchang.exception;


import com.tianchang.response.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 全局异常处理
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 处理异常,捕获所有异常
    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex) {
        log.error("GlobalExceptionHandler.ex。。。。。。。。。。", ex);
        return Result
                .builder()
                .code(500)
                .msg("对不起，操作失败，请联系管理员")
                .build();
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Result> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex,
            HttpServletRequest request) {

        log.error("HTTP方法不支持异常 - 请求路径: {}, 方法: {}", request.getRequestURI(), request.getMethod());
        // 返回适当的错误响应
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(Result.error("不支持的HTTP方法: " + request.getMethod()));
    }
//    @ExceptionHandler(NoSuchElementException.class)
//    public Result handleNoSuchElement(NoSuchElementException ex) {
//        // 处理逻辑
//        log.info("NoSuchElementException occurred: ................................................expection" + ex.getMessage());
//        return Result.error("对不起，操作失败，请联系管理员");
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
//        // 记录异常信息
////        System.err.println("Exception occurred: " + ex.getMessage());
//        log.info("Exception occurred: " + ex.getMessage());
//
//        // 返回响应实体
//        return new ResponseEntity<>("Invalid argument provided", HttpStatus.BAD_REQUEST);
//    }
}
