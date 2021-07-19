package com.zhiyu.gateway.fallback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务降级
 *
 * @author wengzhiyu
 * @date 2021/02/03 17:04
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public Object fallback() {
        return "{\"code\":2,\"data\":null,\"message\":\"服务暂不可用\"}";
    }
}