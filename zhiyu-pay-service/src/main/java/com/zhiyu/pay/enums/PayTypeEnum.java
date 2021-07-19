package com.zhiyu.pay.enums;

/**
 * @author wengzhiyu
 * @date 2020/11/24
 */
public enum PayTypeEnum {
    /**
     * 支付方式
     */
    ALI_PAY(1, "ALI_PAY"),
    WX_PAY(2, "WX_PAY"),
    ;


    PayTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer code;

    public String value;


    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static String getCode(Integer code) {
        for (PayTypeEnum payTypeEnum : PayTypeEnum.values()) {
            if (payTypeEnum.code.equals(code)) {
                return payTypeEnum.value;
            }
        }
        return null;
    }
}
