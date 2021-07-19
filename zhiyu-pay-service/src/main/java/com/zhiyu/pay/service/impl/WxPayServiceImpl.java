package com.zhiyu.pay.service.impl;

import com.zhiyu.pay.common.annotation.pay.PayType;
import com.zhiyu.pay.config.constants.Constants;
import com.zhiyu.pay.entity.dto.PayDTO;
import com.zhiyu.pay.service.PayService;
import com.zhiyu.pay.utils.ResponseData;
import org.springframework.stereotype.Service;

/**
 * @author wengzhiyu
 * @date 2020/11/17
 */
@Service
@PayType(Constants.WX_PAY)
public class WxPayServiceImpl implements PayService {
    @Override
    public ResponseData payStart(PayDTO payDTO) {
        return ResponseData.success("WxPay");
    }
}
