package com.zhiyu.common.config.rest;

import lombok.Data;

/**
 * @author wengzhiyu
 * @since 2022/7/5 20:12
 */
@Data
public class RestTemplateProperties {
    public RestTemplateProperties() {
    }

    public RestTemplateProperties(int poolSize, int timeout, int retryTimes) {
        this.setMaxConnect(poolSize);
        this.setMaxConnectPerRoute(poolSize);
        this.setConnectionRequestTimout(timeout);
        this.setConnectTimeout(timeout);
        this.setReadTimeout(timeout);
        this.setWriteTimeout(timeout);
        this.setRetryTimes(retryTimes);
    }

    /**
     * 连接池的总连接数
     */
    private int maxConnect = Integer.MAX_VALUE;
    /**
     * 单个路由上最大的连接数（不能超过连接池总连接数）,单接口的TPS上限是单个路由的最大连接数上限
     */
    private int maxConnectPerRoute = Integer.MAX_VALUE;
    /**
     * 永久连接空闲检查间隔(ms)，检查永久链接的可用性，而不推荐每次请求的时候才去检查
     */
    private int validateAfterInactivity = 5000;
    /**
     * 从连接池获取连接的超时时间
     */
    private int connectionRequestTimout = 3000;
    /**
     * 连接超时时间
     */
    private int connectTimeout = 3000;
    /**
     * 读超时时间
     */
    private int readTimeout = 3000;
    /**
     * 写超时时间
     */
    private int writeTimeout = 3000;
    /**
     * 重试次数
     */
    private int retryTimes = 0;
}