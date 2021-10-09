package com.zhiyu.common.config.feign;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.zhiyu.common.config.feign.hystrix.BaseFeignRequestInterceptor;
import com.zhiyu.common.config.feign.hystrix.BaseHystrixConcurrencyStrategy;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wengzhiyu
 * @since 2021/10/9 17:59
 */
@Configuration
public class FeignConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({HystrixConcurrencyStrategy.class})
    public BaseHystrixConcurrencyStrategy baseHystrixConcurrencyStrategy() {
        return new BaseHystrixConcurrencyStrategy();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({RequestInterceptor.class})
    public BaseFeignRequestInterceptor baseFeignRequestInterceptor() {
        return new BaseFeignRequestInterceptor();
    }
}
