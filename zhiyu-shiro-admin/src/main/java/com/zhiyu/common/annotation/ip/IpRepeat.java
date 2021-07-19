package com.zhiyu.common.annotation.ip;

import java.lang.annotation.*;

/**
 * 防止同一个ip重复提交
 *
 * @author wengzhiyu
 * @date 2020/6/3
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface IpRepeat {

    /**
     * 防重复时间,默认2s
     *
     * @return
     */
    long time() default 2L;

    /**
     * 重复提交提示语
     *
     * @return
     */
    String msg() default "请勿频繁操作!";

}
