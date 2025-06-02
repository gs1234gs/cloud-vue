//package com.tianchang.handler;
//
//import com.alibaba.fastjson.JSONObject;
//import com.tianchang.response.Result;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
///**
// * 处理登录退出的LogoutSuccessHandler实现类
// * */
//@Component
//public class RewriteLogoutSuccessHandler implements LogoutSuccessHandler {
//    @Override
//    public void onLogoutSuccess(HttpServletRequest request,
//                                HttpServletResponse response,
//                                Authentication authentication)
//            throws IOException {
//        // 可以在此处将 Token 加入黑名单或删除
//        response.setContentType("application/json");
//        response.getWriter().write(
//                JSONObject.toJSONString(
//                        Result.builder().code(200).msg("注销成功").build()
//                ));
//    }
//}
