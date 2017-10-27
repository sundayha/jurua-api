package com.jurua.api.config.Filter;

import com.jurua.api.config.jwt.JwtAuthenticationTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 创建人：张博
 */
@Configuration
public class AppFilterConfig {

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/23 上午11:30
     * @apiNote 添加jwt token过滤器
     */
    @Bean(name = "jwtAuthenticationTokenFilter")
    public Filter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/23 上午11:44
     * @apiNote spring boot 自定义过滤器
     */
    @Bean
    public FilterRegistrationBean FilterRegistration() {
        //Map<String, String> initParameters = new HashMap<>(1);
        //initParameters.put("urls", "/v2/api-docs,/configuration/ui,/swagger-resources/**,/configuration/**,/swagger-ui.html,/webjars/**");
        //initParameters.put("urls", "/swagger-ui.html,/webjars/*,/swagger-resources,/configuration/*,/v2/api-docs,/images/*,*");
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(jwtAuthenticationTokenFilter());
        registration.addUrlPatterns("/*");
        registration.setName("jwtAuthenticationTokenFilter");
        //registration.setInitParameters(initParameters);
        return registration;
    }
}
