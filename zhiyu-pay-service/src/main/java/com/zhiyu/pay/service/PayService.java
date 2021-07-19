package com.zhiyu.pay.service;

import com.zhiyu.pay.entity.dto.PayDTO;
import com.zhiyu.pay.utils.ResponseData;

/**
 * @author wengzhiyu
 * @date 2020/11/17
 */
public interface PayService {


    /**
     * 发起支付
     *
     * @param payDTO
     * @return
     */
    ResponseData payStart(PayDTO payDTO);
}
