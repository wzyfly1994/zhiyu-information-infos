package com.zhiyu.gateway.config.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.List;
import java.util.Optional;


/**
 * @author wengzhiyu
 * @time 2020/4/19 16:42
 * @description
 */
@RestController
@RequestMapping("/swagger-resources")
@Slf4j
public class SwaggerHandler {


    @Autowired(required = false)
    private UiConfiguration uiConfiguration;

    @Autowired(required = false)
    private SecurityConfiguration securityConfiguration;

    @Autowired
    private SwaggerProvider swaggerProvider;

    @GetMapping("/configuration/ui")
    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration() {
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()), HttpStatus.OK));
    }

    @GetMapping("/configuration/security")
    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration() {
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()), HttpStatus.OK));
    }

    @GetMapping
    public Mono<ResponseEntity> swaggerResources() {
        List<SwaggerResource> list = swaggerProvider.get();
        return Mono.just((new ResponseEntity<>(list, HttpStatus.OK)));
    }
}
