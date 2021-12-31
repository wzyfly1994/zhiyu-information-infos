package com.zhiyu.common.config.elasticsearch;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wengzhiyu
 * @since 2021/8/27 17:52
 */
//@ConfigurationProperties(prefix = "es")
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
     * 最大总连接数
     */
    private int connectNum;

    /**
     * 每个路由值的最大连接数
     */
    private int connectPerRoute;
}
