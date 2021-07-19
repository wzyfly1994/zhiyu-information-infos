package com.zhiyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author wengzhiyu
 * @date 2020/1/10
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 7200)
@EnableFeignClients
public class ShiroAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroAdminApplication.class, args);
    }

}
