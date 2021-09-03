package com.zhiyu.common.config.elasticsearch;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * elasticsearch 工厂类
 *
 * @author wengzhiyu
 * @since 2021/8/27 17:52
 */
public class EsClientSpringFactory {
    private static final Logger log = LoggerFactory.getLogger(EsClientSpringFactory.class);
    private static final int CONNECT_TIMEOUT_MILLIS = 1000;
    private static final int SOCKET_TIMEOUT_MILLIS = 30000;
    private static final int CONNECTION_REQUEST_TIMEOUT_MILLIS = 500;
    private static int MAX_CONN_PER_ROUTE = 10;
    private static int MAX_CONN_TOTAL = 30;

    private static HttpHost httpHost;
    private static String userName;
    private static String password;
    private RestClientBuilder builder;
    private RestClient restClient;
    private RestHighLevelClient restHighLevelClient;

    private static final EsClientSpringFactory ES_CLIENT_SPRING_FACTORY = new EsClientSpringFactory();

    private EsClientSpringFactory() {
    }

    public static EsClientSpringFactory build(HttpHost host, String uName, String pwd,
                                              Integer maxConnectNum, Integer maxConnectPerRoute) {
        httpHost = host;
        userName = uName;
        password = pwd;
        MAX_CONN_TOTAL = maxConnectNum;
        MAX_CONN_PER_ROUTE = maxConnectPerRoute;
        return ES_CLIENT_SPRING_FACTORY;
    }


    public void init() {
        builder = RestClient.builder(httpHost);
        setConnectTimeOutConfig();
        setMuftiConnectConfig();
        restClient = builder.build();
        restHighLevelClient = new RestHighLevelClient(builder);
        log.info("初始化ES工厂类");
    }

    /**
     * 配置连接时间延时
     */
    public void setConnectTimeOutConfig() {
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
            requestConfigBuilder.setSocketTimeout(SOCKET_TIMEOUT_MILLIS);
            requestConfigBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT_MILLIS);
            return requestConfigBuilder;
        });
    }

    /**
     * 使用异步httpclient时设置并发连接数
     */
    public void setMuftiConnectConfig() {
        //连接Elasticsearch集群
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(userName, password));
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(MAX_CONN_TOTAL);
            httpClientBuilder.setMaxConnPerRoute(MAX_CONN_PER_ROUTE);
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            return httpClientBuilder;
        });
    }

    public RestClient getClient() {
        return restClient;
    }

    public RestHighLevelClient getRhlClient() {
        return restHighLevelClient;
    }

    public void close() {
        if (restClient != null) {
            try {
                restClient.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        log.info("ES客户端实例关闭");
    }
}
