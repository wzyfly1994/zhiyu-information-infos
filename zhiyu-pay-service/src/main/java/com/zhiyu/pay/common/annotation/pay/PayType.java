package com.zhiyu.pay.common.annotation.pay;

import java.lang.annotation.*;

/**
 * @author wengzhiyu
 * @date 2020/11/24
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PayType {


    /**
     * 支付方式
     *
     * @return
     */
    String value();
}
