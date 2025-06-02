package com.tianchang.jwt;

import com.alibaba.fastjson.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianchang.constant.ConstClassNickName;
import com.tianchang.constant.ConstMapClassNickName;
import com.tianchang.constant.ConstThreadLocal;
import com.tianchang.constant.PublicConstList;
import com.tianchang.redisConfig.RedisUtil;
import com.tianchang.response.Result;
import com.tianchang.threadContext.ThreadLocalContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.IOException;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import reactor.util.annotation.NonNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    List<GrantedAuthority> authentications = null;
    @Resource
    private RedisUtil redisUtil;

    // 重写 OncePerRequestFilter 的 doFilterInternal 方法，用于处理每个请求
    @Order(2)
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull  HttpServletResponse response,
            @NonNull  FilterChain chain)
                throws ServletException, IOException, java.io.IOException {
        response.setContentType("application/json;charset=UTF-8");
        // 获取请求的 URL,用于权限控制
        String url = request.getRequestURI();
        if (PublicConstList.PERMISSION_WHITE_LIST.contains(url)) {
            log.info("公开端点，放行");
            // 放行
            chain.doFilter(request, response);
            return;
        }
        String[] splitUrl = url.split("/");
        String urlAuth = splitUrl[1] + ":" + splitUrl[2];
//        log.error("urlAuth :================" + urlAuth);
        //从当前线程中获取用户信息
        String userInfo = request.getHeader(ConstThreadLocal.USER_INFO_KEY);
        System.out.println("+++++++++++++");
        System.out.println(userInfo);
        System.out.println("userInfo :================");
        Map<String, Object> map = null;
        try {
            //将用户信息转换成Map
            map = objectMapper.readValue(userInfo, Map.class);
        } catch (JsonProcessingException e) {
            log.error("用户信息有误，联系管理员",e);
            response.getWriter().write(
                    JSONObject.toJSONString(
                            Result.builder()
                                    .code(401)
                                    .msg("用户信息有误，联系管理员")
                                    .build()
                    )
            );
        }
        //获取用户id
        Integer userId = (Integer) map.get(ConstMapClassNickName.MAP_USERID_KEY);
        // 获取用户类型
        Integer userType = (Integer) map.get(ConstMapClassNickName.MAP_USERTYPE_KEY);
        // 从redis中获取用户权限
        Object authenticationInfo = redisUtil.hGet(ConstClassNickName.REDIS_AUTHORITY_KEY, userId);
        // 判断是否包含访问权限
        if (authenticationInfo instanceof List<?>) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rawList = (List<Map<String, Object>>) authenticationInfo;
            // 将List<Map<String, Object>>转换为List<SimpleGrantedAuthority>
            authentications = rawList.stream()
                    .map(maps -> new SimpleGrantedAuthority((String) maps.get("authority")))
                    .collect(Collectors.toList());

        } else {
            //  判断用户权限，如果权限不足
            log.error("Invalid authentication information retrieved from Redis");
            response.getWriter().write(
                    JSONObject.toJSONString(
                            Result.builder()
                                    .code(401)
                                    .msg("权限不足")
                                    .build()
                    )
            );
        }
        // 判断用户权限，如果权限不足，则提示用户重新登录
        if (authentications == null) {
            log.error("权限不足");
            response.getWriter().write(
                    JSONObject.toJSONString(
                            Result.builder()
                                    .code(401)
                                    .msg("权限不足，联系管理员")
                                    .build()
                    )
            );
            return;
        }
//        log.error("authentications :================" + checkAuthority(urlAuth, authentications));
//        log.error("authentications :================" + checkAuthority("ROLE_"+userType, authentications));
        // 判断用户权限，如果权限不足，则提示用户重新登录
            if(!checkAuthority(urlAuth, authentications) || !checkAuthority("ROLE_"+userType, authentications)){
                log.error("权限不足");
                log.error("请求的URL是：{}",url);
                response.getWriter().write(
                        JSONObject.toJSONString(
                                Result.builder()
                                        .code(401)
                                        .msg("权限不足，联系管理员")
                                        .build()
                        )
                );
                return;
            }
        String username = (String) map.get(ConstMapClassNickName.MAP_USERNAME_KEY);
        //认证通过， 放行
        //要在spring security中创建一个Authentication对象，放入SecurityContextHolder中，
        // 放入后，spring security会自动校验，如果校验通过，则放行，如果校验不通过，则抛出异常
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        authentications
                );
//        log.error("用户名：{}", authentications);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //才知道是否是登录
        log.info("令牌正确，放行");
        chain.doFilter(request, response);
    }

    /**
     * 从 HTTP 请求头中提取 JWT Token
     *
     * @param request HTTP 请求对象
     * @return 提取出的 JWT Token，如果不存在或格式不正确则返回 null
     */
    private String extractToken(HttpServletRequest request) {
        // 从请求头中获取 "Authorization" 字段
        String header = request.getHeader("Authorization");
        String token = header.replace("Bearer ", "");
        token = token.trim();
        // 检查请求头是否存在且以 "Bearer " 开头
        // 如果满足条件，则提取 Token（去掉 "Bearer " 前缀）
        return token;
    }

    //判断权限
    private boolean checkAuthority(String auth, List<GrantedAuthority> authorityList){
        for (GrantedAuthority grantedAuthority : authorityList){
            String authority = grantedAuthority.getAuthority();
            if(authority.contains(auth)){
//                log.error("权限不足:{}",true);
                return true;
            }
        }
        return false;
    }
}
