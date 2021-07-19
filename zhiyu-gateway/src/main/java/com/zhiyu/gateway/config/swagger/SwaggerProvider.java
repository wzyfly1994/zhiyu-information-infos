package com.zhiyu.gateway.config.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wengzhiyu
 * @time 2020/4/19 16:42
 * @description
 */
@Component
@Primary
@Slf4j
public class SwaggerProvider  implements SwaggerResourcesProvider {

    public static final String API_URI = "/v2/api-docs";

    public static final String PROD = "prod";

    @Value("${spring.profiles.active}")
    private String active;

    @Autowired
    private RouteDefinitionLocator routeDefinitionLocator;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        if (PROD.equals(active)) {
            return resources;
        }
        routeDefinitionLocator.getRouteDefinitions().subscribe(routeDefinition -> routeDefinition.getPredicates()
                .forEach(predicateDefinition -> resources.add(
                        swaggerResource(routeDefinition.getId(),
                                predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                        .replace("/**", API_URI)))));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        return swaggerResource;
    }

}