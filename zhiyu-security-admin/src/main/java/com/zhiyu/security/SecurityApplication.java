package com.zhiyu.security;

//import org.mybatis.spring.annotation.MapperScan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wengzhiyu
 * @since 2021/4/28 14:45
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.zhiyu.security.mapper")
@ComponentScan(basePackages = {"com.zhiyu.core", "com.zhiyu.security"})
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
