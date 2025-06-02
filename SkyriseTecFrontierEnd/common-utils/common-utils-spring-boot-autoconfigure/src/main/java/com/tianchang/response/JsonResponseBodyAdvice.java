//package com.tianchang.response;
//
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//
///**
// * 统一设置响应编码，避免乱码
// * */
//@ControllerAdvice
//public class JsonResponseBodyAdvice implements ResponseBodyAdvice<Object> {
//
//    @Override
//    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        // 你可以在这里添加条件，决定是否应用这个 advice
//        return true;
//    }
//
//    @Override
//    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
//                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
//                                  ServerHttpRequest request,
//                                  ServerHttpResponse response) {
//        // 设置响应的内容类型为 application/json;charset=UTF-8
//        response.getHeaders().setContentType(MediaType.valueOf("application/json;charset=UTF-8"));
//        return body;
//    }
//}
