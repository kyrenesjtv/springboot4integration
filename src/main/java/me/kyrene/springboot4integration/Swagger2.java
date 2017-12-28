package me.kyrene.springboot4integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by wanglin on 2017/12/28.
 */

@Configuration//在spring启动的时候加载
@EnableSwagger2//启动Swagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//用来创建该Api的基本信息（这些基本信息会展现在文档页面中）
                .select()//函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现
                .apis(RequestHandlerSelectors.basePackage("me.kyrene.springboot4integration.controller"))//基包位置 除了被@ApiIgnore指定的请求
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot中使用Swagger2构建RESTful APIs")
                .description("Swagger2官网：https://swagger.io/")
                .termsOfServiceUrl("https://swagger.io/")
                .contact("WwangLgg")
                .version("0.0.3")
                .build();
    }
}
