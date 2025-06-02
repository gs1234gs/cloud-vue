package com.tianchang.filter;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianchang.constant.ConstClassNickName;
import com.tianchang.constant.ConstMapClassNickName;
import com.tianchang.constant.ConstThreadLocal;
import com.tianchang.constant.PublicConstList;
import com.tianchang.redisConfig.RedisUtil;
import com.tianchang.response.Result;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//全局过滤器
@Slf4j
@Configuration
public class SecurityGlobeFilter implements GlobalFilter, Ordered {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Resource
    RedisUtil redisUtil;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //  获取请求头
        ServerHttpRequest request = exchange.getRequest();

        String path = request.getPath().toString();
//        log.error(path);
        if (PublicConstList.PERMISSION_WHITE_LIST.contains(path)) {
            return chain.filter(exchange);
        }
//        log.error("收到请求");
        String token = extractToken(request);
        if (token == null) {
            log.error("未登录");
            ServerHttpResponse response = exchange.getResponse();

            return response.writeWith(Mono.just(
                            response.bufferFactory()
                                    .wrap(JSONObject
                                            .toJSONString(Result
                                                    .builder()
                                                    .code(401)
                                                    .msg("未登录")
                                                    .build()
                                            ).getBytes()
                                    )
                    )
            );
        }
        Claims claims = null;
        //获取map，拿到用户信息，继续验证
        Map<String, Object> map = null;
        try {
            claims = JwtUtils.checkToken(token);
            //获取map，拿到用户信息，继续验证
            map = JwtUtils.getMap(claims);
        } catch (IOException e) {
            log.error("未登录", e);
            ServerHttpResponse response = exchange.getResponse();
            return response.writeWith(Mono.just(response.bufferFactory()
                            .wrap(JSONObject.toJSONString(
                                            Result
                                                    .builder()
                                                    .code(401)
                                                    .msg("未登录")
                                                    .build()
                                    ).getBytes()
                            )
                    )
            );
        }
        //获取用户id
        Integer userId = Integer.parseInt(claims.getSubject());
        Integer userType = (Integer) map.get(ConstMapClassNickName.MAP_USERTYPE_KEY);
        // 从redis中获取token，比较是否一致，避免漏洞，篡改
        String redisToken = (String) redisUtil.hGet(ConstClassNickName.REDIS_TOKEN_KEY, claims.getSubject());
        if (!token.equals(redisToken)) {
            log.error("令牌有误，请重新登录");
            ServerHttpResponse response = exchange.getResponse();
            // 手动返回json数据，返回错误信息给前端,调用阿里云的fastjson工具类，将错误信息转换为json数据
           return response.writeWith(Mono.just(response.bufferFactory()
                    .wrap(
                    JSONObject.toJSONString(
                            Result.builder()
                                    .code(401)
                                    .msg("令牌有误，请重新登录")
                                    .build()
                    ).getBytes()
            )));
        }
        // 将用户信息放入map中
        Map<String, Object> userInfos = new HashMap<>();
        userInfos.put(ConstMapClassNickName.MAP_USERID_KEY, userId);
        userInfos.put(ConstMapClassNickName.MAP_USERTYPE_KEY, userType);
        // 将用户信息转换为 JSON 字符串
        String userInfoJson;
        try {
           userInfoJson = objectMapper.writeValueAsString(userInfos);
        } catch (JsonProcessingException e) {
            ServerHttpResponse response = exchange.getResponse();
            return response.writeWith(Mono.just(response.bufferFactory()
                    .wrap(
                            JSONObject.toJSONString(
                                    Result.builder()
                                            .code(401)
                                            .msg("权限不足")
                                            .build()
                            ).getBytes()
                    )
            ));
        }
        //传递用户信息
        ServerWebExchange userInfo = exchange.mutate()
                .request(builder -> builder.header(ConstThreadLocal.USER_INFO_KEY, userInfoJson))
                .build();

//        return chain.filter(exchange);
        return chain.filter(userInfo);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 从 HTTP 请求头中提取 JWT Token
     *
     * @param request HTTP 请求对象
     * @return 提取出的 JWT Token，如果不存在或格式不正确则返回 null
     */
    private String extractToken(ServerHttpRequest request) {
        // 从请求头中获取 "Authorization" 字段
        try {
            List<String> header = request.getHeaders().get("Authorization");
//            log.error("==={}",header);
            if (header != null) {
                return header
                        .getFirst()
                        .replace("Bearer ", "")
                        .trim();
            }
        } catch (Exception e) {
            log.error("token异常{}", e.getMessage());
        }
        // 检查请求头是否存在且以 "Bearer " 开头
        // 如果满足条件，则提取 Token（去掉 "Bearer " 前缀）
        return null;
    }
}
