package com.zhiyu.gateway.filter;

import com.zhiyu.gateway.common.Constant;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 接受过滤器（解决POST请求报错：Only one connection receive subscriber allowed）
 *
 * @author wengzhiyu
 * @date 2021/01/30 10:01
 */
@Component
public class ReceiveFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Object requestBodyCache = exchange.getAttribute(Constant.REQUEST_BODY_CACHE);
        DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
        ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public Flux<DataBuffer> getBody() {
                byte[] body;
                if (requestBodyCache != null && (body = (byte[]) requestBodyCache).length > 0) {
                    return Flux.just(dataBufferFactory.wrap(body));
                }
                return Flux.empty();
            }
        };
        return chain.filter(exchange.mutate().request(decorator).build());
    }

    @Override
    public int getOrder() {
        return -1;
    }
}