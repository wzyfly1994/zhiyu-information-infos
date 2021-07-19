package com.zhiyu.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.zhiyu.gateway.common.Constant;
import com.zhiyu.gateway.utils.TokenUtils;
import com.zhiyu.gateway.utils.UriUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author wengzhiyu
 * @date 2021/2/3
 */

//@Component
public class LoginFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        System.out.println("path+++++"+path);
        // 开放接口
        if (UriUtils.isOpenPathGlobal(path)) {
            return chain.filter(exchange);
        }
        JSONObject token = TokenUtils.verifyGet(exchange);
        ServerHttpRequest request = exchange.getRequest().mutate().headers(httpHeaders -> {
            httpHeaders.remove(Constant.AUTHORIZATION);
            httpHeaders.remove(Constant.USER_TOKEN);
            httpHeaders.add(Constant.USER_TOKEN, token.toJSONString());
        }).build();
        return chain.filter(exchange.mutate().request(request).build());
    }

    /**
     * order的值越小，说明越先被执行
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
