package com.zhiyu.gateway.limiter;

import com.zhiyu.gateway.common.Constant;
import com.zhiyu.gateway.utils.IpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 限流器规则
 *
 * @author wengzhiyu
 * @date 2021/03/09 11:38
 */
@Configuration
public class KeyResolverConfig {

    @Primary
    @Bean
    public KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(getKey(exchange));
    }

    private String getKey(ServerWebExchange exchange) {
        String path = exchange.getRequest().getURI().getPath();
        String token = exchange.getRequest().getHeaders().getFirst(Constant.USER_TOKEN);
        if (StringUtils.isNotBlank(token)) {
            return path + token;
        } else {
            return path + IpUtils.getIp(exchange);
        }
    }
}
