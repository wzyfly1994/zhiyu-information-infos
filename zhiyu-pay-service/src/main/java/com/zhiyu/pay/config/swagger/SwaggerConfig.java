package com.zhiyu.pay.config.swagger;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wengzhiyu
 * @Data: 2019-03-14 18:00
 * @Description:
 */
@Configuration
@EnableSwagger2
@Profile({"local", "dev", "test"})
public class SwaggerConfig {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${service.registry.expose-host}")
    private String exposeHost;

    @Bean
    @SuppressWarnings("unchecked")
    public Docket apiDocket() {

        // 添加请求头
        // ParameterBuilder headerPair = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
//        headerPair.name("Authorization").description("bearer加空格加token，例如：bearer XXX。开放接口(url上带有/open/api/**之类的)不需要此header")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false)
//                .build();
//        pars.add(headerPair.build());


        return new Docket(DocumentationType.SWAGGER_2)
                // 同一个group不允许有多个docket
                .apiInfo(apiInfo())
                .select()
                .apis(Predicates.or(RequestHandlerSelectors.basePackage("com.zhiyu.pay.controller")))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("支付服务 Api-IP：" + exposeHost)
                .description("swagger接口-IP：" + exposeHost)
                .version("1.0")
                .license("Apache License, Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.html")
                .contact(new Contact("xx", "", "xx@gmail.com"))
                .build();
    }

}