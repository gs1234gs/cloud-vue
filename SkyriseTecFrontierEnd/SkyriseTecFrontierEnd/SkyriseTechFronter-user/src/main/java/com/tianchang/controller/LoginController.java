package com.tianchang.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.tianchang.handler.RewriteLoginSuccessHandler;
import com.tianchang.pojo.LoginUser;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 用户登录控制层
 * 处理用户登录请求，验证用户凭据，并生成 JWT 令牌。
 */

@Slf4j
@RestController
@RequestMapping
public class LoginController {

    private static final String KEY_CODE_NAME = "code";

    // 自动注入 LoginService，用于业务逻辑处理（如果需要）
    @Resource
    private RewriteLoginSuccessHandler rewriteLoginSuccessHandler;
    @Resource
    private AuthenticationManager authenticationManager;
    /**
     * 处理用户登录请求
     *
     * @param request 包含用户名和密码的登录请求体
     * @return 包含访问令牌和刷新令牌的响应结果
     */
    @Async
    @PostMapping("/login")
    public void login(@RequestBody LoginUser request,
                      HttpServletRequest req,
                      HttpServletResponse res) throws IOException {
//        log.error("用户登录请求：{}", request);

        try {
            // 使用 AuthenticationManager 进行认证
            // 创建一个包含用户名和密码的认证请求
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserName(),
                            request.getPassword(),
                            AuthorityUtils.NO_AUTHORITIES
                    )
            );
            // 手动触发成功处理器
            rewriteLoginSuccessHandler.onAuthenticationSuccess(req, res, authentication);
            return;
        }  catch (Exception e) {
            log.error("登录失败：", e);
        }
        rewriteLoginSuccessHandler.onAuthenticationError(res);
    }
//    /**
//      获取验证码
//     @return 返回验证码图片
//      * */
//    @GetMapping("/code")
//    public void getCode(HttpServletResponse response) throws IOException {
//        LineCaptcha  lineCaptcha = CaptchaUtil
//                .createLineCaptcha(
//                        100, 40, 4, 40
//                );
//        //设置响应头
//        String code = lineCaptcha.getCode();//获取验证码
//        lineCaptcha.write(response.getOutputStream());
//        response.setHeader(KEY_CODE_NAME, code);
//    }
//    /**
//     * 验证码校验
//     * */
//    @PostMapping("/code/check")
//    public boolean checkCode(@RequestBody String code, HttpServletRequest request) {
//        return code.equals(request.getHeader(KEY_CODE_NAME));
//    }
}
