package com.eatery.cd.tools;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    // 将登录拦截器配置到容器中
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //AdminLoginInterceptor
        registry.addInterceptor(new AdminLoginInterceptor())
                .addPathPatterns("/**")//addPathPatterns 用来设置拦截路径，excludePathPatterns 用来设置白名单，也就是不需要触发这个拦截器的路径
                .excludePathPatterns( "/login", "/register");  // 排除登录注册等接口，注意这里的格式是 /**/xxx
    }

}
