package com.tianchang.handler;

import cn.hutool.json.JSONUtil;
import com.tianchang.constant.ConstClassNickName;
import com.tianchang.constant.ConstMapClassNickName;
import com.tianchang.jwt.AssistantJwtUtils;
import com.tianchang.redisConfig.RedisUtil;
import com.tianchang.response.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class RewriteLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    RedisUtil redisUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        try {
            // 生成 JWT 令牌
            Map<String, Object> map = AssistantJwtUtils.createJwt(authentication);
            if(map != null){

            String token = (String) map.get(ConstMapClassNickName.MAP_TOKEN_KEY);
            // 获取用户信息，并将用户信息保存到 Redis 中
//                Users userInfo = (Users) map.get("userInfo");
//            log.error("生成 JWT 令牌成功：{}", token);
            int userId = (int) map.get(ConstMapClassNickName.MAP_USERID_KEY);
            //用户权限保存到redis
                redisUtil.hSet(ConstClassNickName.REDIS_AUTHORITY_KEY, userId,
                        map.get(ConstMapClassNickName.MAP_AUTHORITY_KEY));
            //将 token 保存到 Redis 中
//                log.error("redis保存token成功：{}", token);
            redisUtil.hSet(ConstClassNickName.REDIS_TOKEN_KEY, userId, token);
//            log.error("redis保存token成功：{}",token);
            // 构造统一响应
                Map<String,Object> userMap = new HashMap<>();
                userMap.put(ConstMapClassNickName.MAP_USERID_RESPONSE_KEY,userId);
                userMap.put(ConstMapClassNickName.MAP_TOKEN_RESPONSE_KEY,token);
            Result result = Result
                    .builder()
                    .code(200)
                    .msg("登录成功")
                    .data(userMap)
                    .build();
            response.getWriter().write(JSONUtil.toJsonStr(result));
            return;
            }
        } catch (Exception e) {
            log.error("处理器发生异常：{}", e.getMessage());
        }
        // 返回登录失败
        response.getWriter().write(

                JSONUtil.toJsonStr(
                        Result
                                .builder()
                                .code(500)
                                .msg("处理器发生异常")
                                .build())
        );

    }

    // 登录失败
    public void onAuthenticationError(HttpServletResponse response) throws IOException {

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(
                JSONUtil.toJsonStr(
                        Result
                                .builder()
                                .code(401)
                                .msg("用户名或密码错误")
                                .build())
        );
    }
}
