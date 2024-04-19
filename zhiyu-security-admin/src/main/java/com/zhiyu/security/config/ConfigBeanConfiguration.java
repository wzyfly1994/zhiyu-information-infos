package com.zhiyu.security.config;

import com.zhiyu.security.config.properties.LoginProperties;
import com.zhiyu.security.config.properties.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置文件转换Pojo类的 统一配置 类
 */
@Configuration
public class ConfigBeanConfiguration {

    /**
     * 从配置文件中加载JWT相关的属性。
     *
     * @return SecurityProperties JWT安全属性对象，包含JWT相关的配置。
     */
    @Bean
    @ConfigurationProperties(prefix = "jwt")
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }

    /**
     * 从配置文件中加载登录相关的属性。
     *
     * @return LoginProperties 登录属性对象，包含登录相关的配置。
     */
    @Bean
    @ConfigurationProperties(prefix = "login")
    public LoginProperties loginProperties() {
        return new LoginProperties();
    }
}
