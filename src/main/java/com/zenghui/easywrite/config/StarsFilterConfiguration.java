package com.zenghui.easywrite.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zenghui.easywrite.jwt.JwtAuthenticationFilter;

/**
 * 在 Springboot 中注册filter
 */
@Configuration
public class StarsFilterConfiguration {

    /*FilterRegistrationBean 用来配置urlpattern 来确定哪些路径触发filter */
	@Bean
	public FilterRegistrationBean someFilterRegistration() {

	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(new JwtAuthenticationFilter());
	    registration.addUrlPatterns("/*");	//这里过滤所有请求，在过滤器内部再细分
		registration.setName("MyFilter");// 过滤器名称
	    registration.setOrder(1);
	    return registration;
	} 

}