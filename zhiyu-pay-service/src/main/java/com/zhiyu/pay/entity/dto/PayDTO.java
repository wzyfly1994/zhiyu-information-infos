package com.zhiyu.pay.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wengzhiyu
 * @date 2020/11/17
 */
@Data
public class PayDTO {

    @ApiModelProperty(value = "商品ID",example = "1")
    private String productId;

    @ApiModelProperty(value = "订单名称",example = "订单名称")
    private String subject;

    @ApiModelProperty(value = "商品描述",example = "商品描述")
    private String body;

    @ApiModelProperty(value = "总金额(单位是分)",example = "100")
    private String totalFee;

    @ApiModelProperty(value = "订单号(唯一)",example = "ZY202011171053001")
    private String outTradeNo;

    @ApiModelProperty(value = "发起人IP地址",example = "127.0.0.1")
    private String spbillCreateIp;

    @ApiModelProperty(value = "附件数据主要用于商户携带订单的自定义数据",example = "")
    private String attach;

    @ApiModelProperty(value = "1:支付宝 2:微信 3:银联",example = "WX_PAY")
    private String payType;

    @ApiModelProperty(value = "支付方式 (1：PC,平板 2：手机)",example = "2")
    private Short payWay;

    @ApiModelProperty(value = "前台回调地址  非扫码支付使用",example = "''")
    private String frontUrl;





}
