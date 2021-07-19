package com.zhiyu.common.annotation.underline;

import java.lang.annotation.*;

/**
 * 驼峰转下划线
 * @author wengzhiyu
 * @date 2020/6/3
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Underline {
}