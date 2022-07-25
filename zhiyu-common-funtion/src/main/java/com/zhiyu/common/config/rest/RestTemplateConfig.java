package com.zhiyu.common.config.rest;

import com.zhiyu.common.config.ApplicationConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author wengzhiyu
 * @since 2022/7/5 20:20
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    ApplicationConfig applicationConfig;

    @Bean("restTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        RestTemplateProperties restTemplateProperties = applicationConfig.getRestTemplateProperties();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClientBuilder(restTemplateProperties).build());
        factory.setConnectTimeout(restTemplateProperties.getConnectTimeout());
        factory.setReadTimeout(restTemplateProperties.getReadTimeout());
        factory.setConnectionRequestTimeout(restTemplateProperties.getConnectionRequestTimout());
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }

    public HttpClientBuilder httpClientBuilder(RestTemplateProperties restTemplateProperties) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(poolingConnectionManager(restTemplateProperties));
        if (restTemplateProperties.getRetryTimes() > 0) {
            httpClientBuilder
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(restTemplateProperties.getRetryTimes(), true));
        }
        // 设置默认请求头
        // List<Header> headers = getDefaultHeaders();
        // httpClientBuilder.setDefaultHeaders(headers);
        // 设置长连接保持策略
        // httpClientBuilder.setKeepAliveStrategy(connectionKeepAliveStrategy());

        // TODO: 调用第三方, 使用长连接是一种危险行为, 别人的服务器可以随意中断连接,
        // 此时再去获取连接使用的时候, 会返回no response
        httpClientBuilder.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE);
        httpClientBuilder.evictExpiredConnections();
        httpClientBuilder.evictIdleConnections(5L, TimeUnit.SECONDS);

        return httpClientBuilder;
    }

    /**
     * HttpClinet连接池
     */
    public HttpClientConnectionManager poolingConnectionManager(RestTemplateProperties restTemplateProperties) {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager =
                new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(restTemplateProperties.getMaxConnect());
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(restTemplateProperties.getMaxConnectPerRoute());
        poolingHttpClientConnectionManager
                .setValidateAfterInactivity(restTemplateProperties.getValidateAfterInactivity());
        return poolingHttpClientConnectionManager;
    }
}
