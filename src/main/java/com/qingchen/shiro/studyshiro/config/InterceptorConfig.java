package com.qingchen.shiro.studyshiro.config;

import com.qingchen.shiro.studyshiro.interceptor.UserActionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Created by QingChen on 2019/11/18 11:10
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    /**
     * 目的是为了在UserActionInterceptor中可以注入service，否则service注入为null
     * @return
     */
    @Bean
    public UserActionInterceptor myInterceptor(){
        return new UserActionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
        super.addInterceptors(registry);
    }
}
