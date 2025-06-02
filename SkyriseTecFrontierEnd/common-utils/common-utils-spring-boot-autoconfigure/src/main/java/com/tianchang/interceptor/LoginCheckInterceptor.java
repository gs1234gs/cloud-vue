package com.tianchang.interceptor;



//import com.alibaba.fastjson.JSONObject;
//
//import com.aliyun.oss.JwtUtils;
//import guanshiyun.response.Result;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//@Slf4j
//@Component
//public class LoginCheckInterceptor implements HandlerInterceptor {
//    // 在请求处理之前进行调用（Controller方法调用之前）返回值：true-放行；false-拦截
//    @Override
//    public boolean preHandle(HttpServletRequest req,
//                             HttpServletResponse res,
//                             Object handler) throws Exception {
//
//
//        // 获取请求的URI
//        String reqUrl = req.getRequestURI();
//        log.info("请求的URI是：{}", reqUrl);
//
//        // 判断请求的URI是否包含login或register，如果是则不需要拦截
//        if (reqUrl.contains("login") || reqUrl.contains("register")) {
//            log.info("这是登陆操作,登录操作不需要拦截");
//            // 放行
//
//            return true;
//        }
//
//
//        // 获取请求头中的token令牌是否存在，如果不存在则提示用户先登录
//        String jwt = req.getHeader("token");
//        if(!StringUtils.hasLength(jwt)){
//            log.info("请求头token为空，请先登录");
//            Result notLogin = Result.error("NOT_LOGIN");
////             手动返回json数据，返回错误信息给前端,调用阿里云的fastjson工具类，将错误信息转换为json数据
//            String error = JSONObject.toJSONString(notLogin);
//            res.getWriter().write(error);
//            return false;
//        }
//
//        // TODO 验证令牌是否正确，如果正确则放行，如果不正确则提示用户重新登录
//
//        try {
//            JwtUtils.checkToken(jwt);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.info("令牌有误，请重新登录");
//
//            // 手动返回json数据，返回错误信息给前端,调用阿里云的fastjson工具类，将错误信息转换为json数据
//            Result notLogin = Result.error("NOT_LOGIN");
//            String error = JSONObject.toJSONString(notLogin);
//            res.getWriter().write(error);
//            return false;
//        }
//
//        // 放行
//        log.info("令牌正确，放行");
//        return true;
//
//    }
//
//
//    // 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
//    @Override
//    public void postHandle(HttpServletRequest request,
//                           HttpServletResponse response,
//                           Object handler, ModelAndView modelAndView) throws Exception {
//
//        System.out.println("拦截器处理....................");
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//
//    // 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
//    @Override
//    public void afterCompletion(HttpServletRequest request,
//                                HttpServletResponse response,
//                                Object handler, Exception ex) throws Exception {
//
//        System.out.println("拦截器结束....................");
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//}
