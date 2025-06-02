package com.tianchang.response;

import org.springframework.core.MethodParameter;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 统一设置响应编码，避免乱码
 * */
@ControllerAdvice
public class JsonResponseBodyAdvice extends ResponseBodyResultHandler {

    public JsonResponseBodyAdvice(ServerCodecConfigurer serverCodecConfigurer,
                                  RequestedContentTypeResolver contentTypeResolver) {
        super(serverCodecConfigurer.getWriters(), contentTypeResolver);
    }

    @Override
    public boolean supports(HandlerResult handlerResult) {
        return true;
    }

    @Override
    public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {
        ServerHttpResponse response = (ServerHttpResponse) exchange.getResponse();
        response.getHeaders().setContentType(MediaType.valueOf("application/json;charset=UTF-8"));
        return super.handleResult(exchange, result);
    }
}
