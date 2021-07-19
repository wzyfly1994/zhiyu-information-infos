package com.zhiyu.pay.common.annotation.security;

import java.lang.annotation.*;

/**
 * 参数加密注解
 * @author wengzhiyu
 * @date 2020/11/19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Encryption {
}
