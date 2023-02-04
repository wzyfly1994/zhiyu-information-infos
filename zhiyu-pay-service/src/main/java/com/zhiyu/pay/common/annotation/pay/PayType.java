package com.zhiyu.pay.common.annotation.pay;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
