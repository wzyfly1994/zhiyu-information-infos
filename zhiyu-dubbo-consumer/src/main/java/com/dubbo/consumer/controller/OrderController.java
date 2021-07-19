package com.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.api.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengzhiyu
 * @date 2021/2/19
 */
@RestController
public class OrderController {

    @Reference
    private OrderService orderService;

    @GetMapping("/order/number/{number}")
    public Object getOrder(@PathVariable String number) {
        return orderService.number(number);
    }
}
