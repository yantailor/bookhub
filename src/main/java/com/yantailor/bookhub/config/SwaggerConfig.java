package com.yantailor.bookhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yantailor
 * on 2021/11/27 10:14 @Version 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    public Docket guestApiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("前台匿名用户API接口文档")
                .apiInfo(guestApi())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yantailor.bookhub.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());

    }

//    @Bean
    public Docket AdminApiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("后台管理API接口文档")
                .apiInfo(adminApi())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yantailor.turing_web_re.controller.admin"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo guestApi(){
        return new ApiInfoBuilder()
                .title("可匿名访问的查询接口文档")
                .description("用于给游览网站的用户查看各种信息")
                .termsOfServiceUrl("www.4399.com")
                .version("1.0")
                .build();


    }
    private ApiInfo adminApi() {
        return new ApiInfoBuilder()
                .title("管理员操作数据的接口文档")
                .description("用于给维护图灵智能创新团队网站")
                .termsOfServiceUrl("https://github.com/SmallPineApp1e")
                .version("1.0")
                .build();
    }



    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList= new ArrayList();
        apiKeyList.add(new ApiKey("token", "token", "header"));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts=new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("token", authorizationScopes));
        return securityReferences;
    }
}
