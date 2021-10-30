package com.example.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.function.Predicate;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 配置Swagger的Docket的Bean实例
     * @return
     */
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
//                .enable(false) //是否启用Swagger
                .groupName("组织机构")
                .select()
                //RequestHandlerSelectors, 配置要扫描接口的方式
                //basePackage, 指定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.controller"))
                //ant 指定要扫描的路径
//                .paths(PathSelectors.ant("/hell1/**"))
                .build();
    }

    /**
     * 设置分组1
     * @return
     */
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("人员维护")
                .select()
                //RequestHandlerSelectors, 配置要扫描接口的方式
                //basePackage, 指定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.controller1"))
                .build();
    }

    /**
     * 设置分组2
     * @return
     */
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("通讯录");
    }

    /**
     * 配置Swagger的信息 apiInfo
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "集成Swagger2",
                "方便前后端接口调用",
                "1.0",
                "https://swagger.io/",
                new Contact("沫殇", "https://swagger.io/", "cuijiye1993@163.com"),//作者信息
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
