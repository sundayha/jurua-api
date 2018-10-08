package com.jurua.api.config.Filter;

import com.jurua.api.config.jwt.JwtAuthenticationTokenFilter;
import com.jurua.api.config.jwt.JwtTokenUtil;
import org.redisson.api.RedissonClient;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 */
@Configuration
public class AppFilterConfig {

    private RedissonClient redissonClient;
    private JwtTokenUtil jwtTokenUtil;

    public AppFilterConfig(RedissonClient redissonClient, JwtTokenUtil jwtTokenUtil) {
        this.redissonClient = redissonClient;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/23 上午11:30
     * @apiNote 添加jwt token过滤器
     */
    @Bean(name = "jwtAuthenticationTokenFilter")
    public Filter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter(redissonClient, jwtTokenUtil);
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/23 上午11:44
     * @apiNote spring boot 自定义过滤器
     */
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(jwtAuthenticationTokenFilter());
        registration.addUrlPatterns("/*");
        registration.setName("jwtAuthenticationTokenFilter");
        return registration;
    }
}
