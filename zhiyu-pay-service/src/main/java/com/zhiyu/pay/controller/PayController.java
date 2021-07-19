package com.zhiyu.pay.controller;

import com.zhiyu.pay.entity.dto.PayDTO;
import com.zhiyu.pay.service.impl.PayCommonService;
import com.zhiyu.pay.utils.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengzhiyu
 * @date 2020/11/17
 */
@RestController
@RequestMapping("/pay")
@Api(tags = "支付模块")
public class PayController {

    @Autowired
    private PayCommonService payCommonService;

    @PostMapping("/start")
    @ApiOperation("发起支付")
    public ResponseData start(@RequestBody PayDTO payDTO) {

        return payCommonService.payStart(payDTO);
    }


}
