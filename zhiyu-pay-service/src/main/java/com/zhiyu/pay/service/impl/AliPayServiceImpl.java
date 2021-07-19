package com.zhiyu.pay.service.impl;

import com.zhiyu.pay.common.annotation.pay.PayType;
import com.zhiyu.pay.common.context.PayStrategyContext;
import com.zhiyu.pay.config.constants.Constants;
import com.zhiyu.pay.entity.dto.PayDTO;
import com.zhiyu.pay.service.PayService;
import com.zhiyu.pay.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wengzhiyu
 * @date 2020/11/17
 */
@Service
@PayType(Constants.ALI_PAY)
public class AliPayServiceImpl implements PayService {

    @Autowired
    private PayStrategyContext payStrategyContext;

    @Override
    public ResponseData payStart(PayDTO payDTO) {

        return ResponseData.success("AliPay");
    }
}
