package com.caostudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author 曹学习
 * @description Swagger2
 * @date 2020/11/3 15:29
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    //配置Swagger2核心配置 docket
    @Bean
    public Docket createRestApi() {
        //指定api类型为swagger2
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())  //用于定义api文档汇总信息
                .select().apis(RequestHandlerSelectors
                        .basePackage("com.caostudy.controller")) //扫描controller
                .paths(PathSelectors.any()) // 所有controller
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("天天吃货 电商平台接口api") //文档页标题
                .contact(new Contact("caoqiang",
                        "https://www.4399.com",
                        "969718359@qq.com")) //联系人信息
                .description("为天天吃货提供的api文档")
                .version("1.0.0") //文档版本号
                .termsOfServiceUrl("https://www.4399.com") //网页地址
                .build();
    }
}
