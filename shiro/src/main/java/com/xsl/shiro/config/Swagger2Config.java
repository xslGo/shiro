package com.xsl.shiro.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket accessToken() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("api")// 定义组
                .select() // 选择那些路径和 api 会生成 document
                .apis(RequestHandlerSelectors.basePackage("com.xsl.shiro.controller")) // 拦截的包路径
                .paths(PathSelectors.regex("/*/.*"))// 拦截的接口路径
                .build(); // 创建
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("中国中铁南京地铁项目")// 标题
                .description("南京地铁")// 描述
//                .termsOfServiceUrl("http://www.extlight.com")//
//                .contact(new Contact("nanJing_subway", "http://www.extlight.com", "xslmast@qq.com"))// 联系
                .version("1.0")// 版本
                .build();
    }

}
