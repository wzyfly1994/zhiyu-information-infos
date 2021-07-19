package com.zhiyu.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wengzhiyu
 * @date 2021/1/14
 */
@FeignClient(name = "pay-service", path = "/pay-service")
public interface PayClient {

    /**
     * testHttp
     *
     * @return
     */
    @GetMapping("/test/testhttp")
    String testHttp();

}
