package com.tianchang.internetConfig;

import com.tianchang.constant.ConstThreadLocal;
import com.tianchang.threadContext.ThreadLocalContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 注册RestTemplate，用于远程调用
 * */
@Configuration
public class RegisterRestTemplate {
    @Bean
    public RestTemplate restTemplate(){
         return new RestTemplate();
    }
    // 拦截器，用于传递用户信息
    @Bean
    public RequestInterceptor userInfoRequestInterceptor(){
        return new RequestInterceptor(){
            @Override
            public void apply(RequestTemplate requestTemplate) {
                String userInfoContext = (String)ThreadLocalContext.getObjectContext();
                if(userInfoContext != null){
                    requestTemplate.header(ConstThreadLocal.USER_INFO_KEY, userInfoContext);
                }
            }
        };
    }
}
