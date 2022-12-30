package com.freya.onlinetech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index_1");
        registry.addViewController("/index_1.html").setViewName("index_1");
        registry.addViewController("main.html").setViewName("dashboard");
    }

    //自定义的国际化组件
    @Bean
    public LocaleResolver localeResolver()
    {
        return new MyLocaleResolver();
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/login", "/user/register", "/imserver/**", "/files/**", "/alipay/**",
//                        "/doc.html", "/webjars/**", "/swagger-resources/**");
//
////        registry.addInterceptor(new LoginHandelrInterceptor())
////                .addPathPatterns("/**").excludePathPatterns("index_1.html","/","/user/login","/css/*","/js/**","/img/**");
//    }
}
