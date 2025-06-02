//package com.tianchang.configSecurity;
//
//
//
//import com.tianchang.handler.RewriteLogoutSuccessHandler;
//
//import com.tianchang.jwt.JwtAuthFilter;
//import jakarta.annotation.Resource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Spring Security 安全配置类
// */
//@Configuration  // 标记为Spring配置类
//@EnableWebSecurity// 启用Spring Security的Web安全支持
//public class SecurityConfig {
//
//    @Resource
//    RewriteLogoutSuccessHandler rewriteLogoutSuccessHandler;
//
//    /**
//     * 配置安全过滤器链
//     * @param http HttpSecurity对象，用于配置Web安全
//     * @return 配置好的SecurityFilterChain
//     * @throws Exception 可能抛出的异常
//     */
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                // ==================== 1. 基础安全配置 ====================
//                // 禁用CSRF保护（前后端分离架构通常不需要CSRF保护）
//                // CSRF主要针对浏览器表单提交的防护，API接口通常使用token验证
//                .csrf(AbstractHttpConfigurer::disable)
//// ==================== 认证方式配置 ====================
//                // 禁用表单登录（因为使用JWT认证）
//                // 前后端分离架构通常不需要表单登录
//                .formLogin(AbstractHttpConfigurer::disable)
//                // 配置CORS跨域资源共享
//                // 允许指定来源的跨域请求，解决浏览器同源策略限制
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//
//                // 配置会话管理策略为无状态（STATELESS）
//                // 使用JWT等token认证时应该禁用session，每次请求都需要携带认证信息
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                // ==================== 2. 过滤器配置 ====================
//                // 添加自定义JWT认证过滤器
//                // 该过滤器会在Spring Security默认认证过滤器之前执行
//                // 主要用于从请求头中提取并验证JWT token
////                .addFilterBefore(loginCheckFilter(), UsernamePasswordAuthenticationFilter.class)
//                // ==================== JWT认证配置 ====================
////                .oauth2ResourceServer(oauth2 -> oauth2
////                        .jwt(jwt -> jwt
////                                .jwtAuthenticationConverter(jwtAuthConverter()) // JWT转换器
////                        )
////                )
//                // ==================== 3. 授权配置 ====================
//                // 配置请求授权规则
//                .authorizeHttpRequests(auth -> auth
//                        // 设置不需要认证的公开端点
//                        .requestMatchers(
//                                "/",                    // 根路径
//                                "/login",              // 登录接口
//                                "/refresh",   // token刷新接口
//                                "/error",               // 错误处理端点
//                                "/favicon.ico"  ,// 网站图标
//                                "/logout"
//                        ).permitAll()  // 允许匿名访问
//                        // 管理接口需要权限
//                        // 其他所有请求都需要认证后才能访问
//                        // 实际权限控制可以在方法上使用@PreAuthorize等注解进行细粒度控制
//                        .anyRequest().authenticated()
//                )
//                // ==================== 4. 注销配置 ====================
//                // 配置注销相关设置
//                .logout(logout -> logout
//                        .logoutUrl("/logout")  // 设置注销请求的URL路径
//                        .logoutSuccessHandler(rewriteLogoutSuccessHandler)  // 注销成功处理器
//                        .addLogoutHandler((request, response, authentication) -> {
//                            // 注销时清除安全上下文
//                            // 防止认证信息残留在当前线程中
//                            SecurityContextHolder.clearContext();
//                        })
//                )
//                // ==================== 5. 异常处理配置 ====================
//                // 配置认证和授权异常处理
//                .exceptionHandling(handling -> handling
//                        .authenticationEntryPoint(HandleException::handleUnauthorized // 使用方法引用或 lambda 表达式
//                        )
//                        .accessDeniedHandler(HandleException::handleForbidden // 使用方法引用或 lambda 表达式
//                        ))
//                //添加JWT的处理过滤器，用于从JWT中解析
//                .addFilterBefore(jwtAuthConverter(), UsernamePasswordAuthenticationFilter.class) // 使用注入的过滤器
//                // 禁用HTTP Basic认证
//                // Basic认证会将凭证直接放在请求头中，安全性较低
//                .httpBasic(AbstractHttpConfigurer::disable);
//        return http.build();
//    }
//
////    @Bean
////    public LoginCheckFilter loginCheckFilter() {
////        return new LoginCheckFilter();
////    }
//    /**
//     * CORS跨域配置
//     */
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        // 创建 CORS 配置对象
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        // 设置允许的源（前端地址），这里只允许来自 localhost:9000 的请求
//        // List.of() 创建不可变列表，确保配置安全
//        configuration.setAllowedOrigins(List.of("http://localhost:9000","http://127.0.0.1:9000"));
//        // 设置允许的 HTTP 方法
//        // 通常包括：GET（获取资源）、POST（创建资源）、PUT（更新资源）、
//        // DELETE（删除资源）和 OPTIONS（预检请求）
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE",
//                "OPTIONS", "HEAD", "PATCH", "TRACE"));
//
//        // 设置允许的请求头，* 表示允许所有头
//        // 注意：生产环境建议明确指定需要的头而不是使用通配符
//        configuration.setAllowedHeaders(List.of("*"));
//        // 暴露自定义头信息
////        configuration.setExposedHeaders(List.of("Authorization", "X-Ref resh-Token"));
//        // 允许发送凭据（如 cookies、HTTP认证等）
//        // 当设置为 true 时，AllowedOrigins 不能为 *，必须明确指定
//        configuration.setAllowCredentials(true);
//        // 设置预检请求的缓存时间
//        configuration.setMaxAge(3600L);
//        // 创建基于 URL 的 CORS 配置源
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        // 注册 CORS 配置，应用到所有路径（/**）
//        // 这意味着所有端点的请求都会应用这个 CORS 策略
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }
//    /*
//     * JWT认证转换器
//     */
//    @Bean
//    public JwtAuthFilter jwtAuthConverter() {
//        return new JwtAuthFilter();
//    }
//    /**
//     * 声明 AuthenticationManager Bean
//     *
//     * @param authenticationConfiguration 认证配置对象
//     * @return AuthenticationManager 实例
//     * @throws Exception 如果获取 AuthenticationManager 时发生错误
//     */
//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        // 从 AuthenticationConfiguration 中获取 AuthenticationManager
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    // 开发环境使用的明文密码编码器（仅用于开发调试）
//    @Bean
//    @Profile("dev") // 开发环境
//    public PasswordEncoder devPasswordEncoder() {
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                // 直接返回明文密码（仅用于开发环境）
//                System.out.println("警告：开发环境使用明文密码编码器！");
//                return rawPassword.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                // 直接比较明文密码（仅用于开发环境）
//                return rawPassword.toString().equals(encodedPassword);
//            }
//        };
//    }
//    /**
//     * 密码编码器
//     * @return BCrypt密码编码器
//     *
//     * */
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        // 使用BCrypt密码编码器
////        return new BCryptPasswordEncoder();
////    }
//}