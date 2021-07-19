package com.zhiyu.pay.common.context;

import com.zhiyu.pay.common.annotation.pay.PayType;
import com.zhiyu.pay.common.exception.BusinessException;
import com.zhiyu.pay.entity.dto.PayDTO;
import com.zhiyu.pay.service.PayService;
import com.zhiyu.pay.utils.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wengzhiyu
 * @date 2020/11/24
 */
@Slf4j
@Component
public class PayStrategyContext {

    private final Map<String, PayService> payServiceMap = new HashMap<>(16);


    public ResponseData payStart(PayDTO payDTO) {
        return getStrategy(payDTO.getPayType()).payStart(payDTO);
    }


    private PayService getStrategy(String payType) {
        PayService payService = payServiceMap.get(payType);
        if (payService == null) {
            throw new BusinessException("没有[" + payType + "]对应的映射");
        }
        return payService;
    }


    @Autowired(required = false)
    public void init(List<PayService> strategies) {
        if (CollectionUtils.isEmpty(strategies)) {
            return;
        }
        for (PayService strategy : strategies) {
            PayType payType = AnnotationUtils.findAnnotation(strategy.getClass(), PayType.class);
            if (payType == null) {
                continue;
            }
            payServiceMap.put(payType.value(), strategy);
        }
    }
}
