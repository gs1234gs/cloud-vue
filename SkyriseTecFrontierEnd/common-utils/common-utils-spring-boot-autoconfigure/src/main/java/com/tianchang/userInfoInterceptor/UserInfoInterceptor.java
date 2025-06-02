package com.tianchang.userInfoInterceptor;


import cn.hutool.core.util.StrUtil;
import com.tianchang.constant.ConstThreadLocal;
import com.tianchang.threadContext.ThreadLocalContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
/**
*用户信息拦截器，供微服务调用
* */
@Slf4j
public class UserInfoInterceptor implements HandlerInterceptor {

    //重写preHandle方法，用于处理每个请求
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object hanlder) throws IOException {
        //1.获取登录用户信息
        String userInfo = request.getHeader(ConstThreadLocal.USER_INFO_KEY);
//        log.error("用户信息：{}", userInfo);
//        String userInfo = request.getHeader("userInfo");
        //2.判断用户信息是否存在
        if(StrUtil.isNotBlank(userInfo)){
            //3.存在则存储到ThreadLocal中
            ThreadLocalContext.setObjectContext(userInfo);
        }

        return true;
    }
    //重写afterCompletion方法，用于清理用户信息
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,Exception ex){
        //清理用户
        ThreadLocalContext.clearObjectContext();
    }
}
