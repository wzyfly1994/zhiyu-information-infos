package com.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.api.service.OrderService;

/**
 * @author wengzhiyu
 * @date 2021/2/19
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Override
    public String number(String num) {
        return num;
    }
}
