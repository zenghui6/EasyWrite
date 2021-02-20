package com.zenghui.easywrite.config;

import io.swagger.annotations.Api;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootConfiguration
public class SwaggerConfiguration {

    /**
     * 功能描述：注入环境变量
     */
    @Bean("staffApi")
    public Docket accountApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("员工接口（官网）")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/staff.*"))
                .build()
                .apiInfo(apiInfo());
    }
    @Bean("mediaApi")
    public Docket mediaApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("员工接口（媒体分发）")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/media.*"))
                .build()
                .apiInfo(apiInfo());
    }
    @Bean("adminApi")
    public Docket adminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理员接口（官网）")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/admin.*"))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean("clientApi")
    public Docket clientApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("客户接口（官网）")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/client.*"))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean("mpClientApi")
    public Docket mpClientApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("客户接口（小程序）")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/client_miniprogram.*"))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean("mpStaffApi")
    public Docket mpStaffApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("员工接口（小程序）")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/staff_miniprogram.*"))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean("loginApi")
    public Docket loginApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("登陆接口（官网）")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/account.*"))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean("uploadApi")
    public Docket uploadApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("图片上传接口（综合）")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/picture.*"))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean("countApi")
    public Docket countApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("数据统计")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/count.*"))
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * 功能描述：SwaggerUI文档的基本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("数据一览")
                .description("所有的接口都在这里了~")
                .termsOfServiceUrl("")
                .build();
    }
}
