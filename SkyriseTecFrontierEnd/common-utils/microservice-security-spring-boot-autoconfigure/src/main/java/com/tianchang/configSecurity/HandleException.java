package com.tianchang.configSecurity;

import cn.hutool.json.JSONUtil;
import com.tianchang.response.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

public class HandleException {
    /**
     * 自定义认证异常处理（统一返回 Result 格式）
     *
     * @param request  HTTP 请求对象
     * @param response HTTP 响应对象
     * @param ex       认证异常对象
     * @throws IOException 如果写入响应时发生 I/O 错误
     */
    public static void handleUnauthorized(HttpServletRequest request,
                                    HttpServletResponse response,
                                    AuthenticationException ex) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        // 设置响应内容类型为 JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // 写入自定义的错误响应，状态码为 401（未认证），消息为 "未认证"
        response.getWriter().write(

                JSONUtil.toJsonStr(
                                    Result
                                        .builder()
                                        .code(401)
                                        .msg("未认证")
                                        .build())
        );
    }

    /**
     * 自定义权限不足处理
     *
     * @param request  HTTP 请求对象
     * @param response HTTP 响应对象
     * @param ex       权限不足异常对象
     * @throws IOException 如果写入响应时发生 I/O 错误
     */
    public static void handleForbidden(HttpServletRequest request,
                                 HttpServletResponse response,
                                 AccessDeniedException ex) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        // 设置响应内容类型为 JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // 写入自定义的错误响应，状态码为 403（权限不足），消息为 "权限不足"
        response.getWriter().write(
                JSONUtil.toJsonStr(
                                  Result
                                      .builder()
                                      .code(403)
                                      .msg("权限不足")
                                      .build())
        );
    }
}
