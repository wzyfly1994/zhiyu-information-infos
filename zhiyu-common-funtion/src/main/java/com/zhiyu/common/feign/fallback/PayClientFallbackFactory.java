package com.zhiyu.common.feign.fallback;

import com.zhiyu.common.feign.PayClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wengzhiyu
 * @date 2021/2/4
 */
@Slf4j
@Component
public class PayClientFallbackFactory implements FallbackFactory<PayClient> {
    @Override
    public PayClient create(Throwable throwable) {
        return new PayClient() {
            @Override
            public String testHttp(String name) {
                log.error("支付服务降级: ", throwable);
                return "支付服务不可用";
            }
        };
    }
}
