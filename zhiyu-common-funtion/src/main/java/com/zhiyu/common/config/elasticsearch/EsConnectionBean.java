//package com.zhiyu.common.config.elasticsearch;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author wengzhiyu
// * @since 2021/8/27 17:52
// */
//@Configuration
//@ComponentScan(basePackageClasses = EsClientSpringFactory.class)
//@EnableConfigurationProperties(value = EsConnectionProperties.class)
//public class EsConnectionBean {
//    private final EsConnectionProperties esConnectionProperties;
//
//    public EsConnectionBean(EsConnectionProperties esConnectionProperties) {
//        this.esConnectionProperties = esConnectionProperties;
//    }
//
//    @Bean
//    public HttpHost httpHost() {
//        return new HttpHost(esConnectionProperties.getHost(), esConnectionProperties.getPort(), "http");
//    }
//
//    @Bean(initMethod = "init", destroyMethod = "close")
//    public EsClientSpringFactory getFactory() {
//        return EsClientSpringFactory.
//                build(httpHost(),
//                        esConnectionProperties.getUserName(),
//                        esConnectionProperties.getPassword(),
//                        esConnectionProperties.getConnectNum(),
//                        esConnectionProperties.getConnectPerRoute());
//    }
//
//    @Bean
//    public RestClient getRestClient() {
//        return getFactory().getClient();
//    }
//
//    @Bean
//    public RestHighLevelClient getRhlClient() {
//        return getFactory().getRhlClient();
//    }
//}
