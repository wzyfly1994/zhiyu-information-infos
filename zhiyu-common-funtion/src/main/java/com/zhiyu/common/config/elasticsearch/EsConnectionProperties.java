package com.zhiyu.common.config.elasticsearch;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wengzhiyu
 * @since 2021/8/27 17:52
 */
@ConfigurationProperties(prefix = "es")
@Data
public class EsConnectionProperties {
    /**
     * 主机地址
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    /**
     * ES用户名
     */
    private String userName;

    /**
     * ES密码
     */
    private String password;

    /**
     * 连接数
     */
    private int connectNum;

    /**
     * 连接路由
     */
    private int connectPerRoute;
}
