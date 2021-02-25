package com.zenghui.easywrite;

import com.kuisama.oss.annotation.EnableAliyunOss;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableAliyunOss
@EnableSwagger2Doc
@EnableJpaAuditing
@SpringBootApplication
public class BysjApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BysjApplication.class, args);
    }

}
