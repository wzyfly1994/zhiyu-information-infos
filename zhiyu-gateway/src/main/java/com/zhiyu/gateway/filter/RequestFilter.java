package com.zhiyu.gateway.filter;


import com.zhiyu.gateway.common.Constant;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 请求过滤器
 *
 * @author wengzhiyu
 * @date 2021/01/30 10:01
 */
@Component
public class RequestFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return DataBufferUtils.join(exchange.getRequest().getBody()).map(dataBuffer -> {
            byte[] bytes = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(bytes);
            DataBufferUtils.release(dataBuffer);
            return bytes;
        }).defaultIfEmpty(new byte[0])
                .doOnNext(bytes -> {
                    // 将请求体存入Attributes
                    exchange.getAttributes().put(Constant.REQUEST_BODY_CACHE, bytes);
                    exchange.getAttributes().put(Constant.REQUEST_TIME_CACHE, System.currentTimeMillis());
                })
                .then(chain.filter(exchange));
    }

    @Override
    public int getOrder() {
        return -3;
    }
}