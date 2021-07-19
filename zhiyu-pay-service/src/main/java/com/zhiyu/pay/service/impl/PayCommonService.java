package com.zhiyu.pay.service.impl;

import com.zhiyu.pay.common.context.PayStrategyContext;
import com.zhiyu.pay.entity.dto.PayDTO;
import com.zhiyu.pay.utils.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wengzhiyu
 * @date 2020/11/25
 */
@Slf4j
@Service
public class PayCommonService {
    @Autowired
    private PayStrategyContext payStrategyContext;

    public ResponseData payStart(PayDTO payDTO) {
        return payStrategyContext.payStart(payDTO);
    }
}
