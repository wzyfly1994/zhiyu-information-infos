package com.zhiyu.common.config.redis;

import lombok.Getter;
import lombok.Setter;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wengzhiyu
 * @date 2020/11/3
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedissonConfig implements WebMvcConfigurer {
    private String host;

    private String port;

    private String password;

    private Integer database;

    private RedissonClient redissonClient;

    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setDatabase(database)
                .setPassword(password);
        return Redisson.create(config);
    }


}
