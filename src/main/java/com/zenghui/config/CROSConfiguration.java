package com.zenghui.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CROSConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
            .allowedOrigins("*")
            //设置允许证书，不再默认开启
            .allowCredentials(true)
            //设置允许的方法
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            //设置跨域允许时间
            .maxAge(60*60);

    }
}
