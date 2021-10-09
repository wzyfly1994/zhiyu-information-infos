package com.zhiyu.common.feign;

import com.zhiyu.common.config.feign.hystrix.BaseFeignRequestInterceptor;
import com.zhiyu.common.feign.fallback.PayClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wengzhiyu
 * @date 2021/1/14
 */
@FeignClient(name = "pay-service", path = "/pay-service",fallbackFactory = PayClientFallbackFactory.class,configuration = BaseFeignRequestInterceptor.class)
public interface PayClient {

    /**
     * testHttp
     *
     * @return
     */
    @PostMapping("/test/testhttp")
    String testHttp(@RequestParam("name") String name);
    //String testHttp(@RequestHeader(value = "client",required = false) String client, @RequestParam("name") String name);

}
