package com.tianchang;

import com.tianchang.response.Result;
import com.tianchang.threadContext.ThreadLocalContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 将Result和ThreadLocalContext注入到容器中
 * */
@Configuration
public class TianchangAutoConfiguration{

    @Bean
    public Result result()
    {
        return Result.builder().build();
    }
    @Bean
    public ThreadLocalContext threadLocalContext()
    {
        return new ThreadLocalContext();
    }

}
