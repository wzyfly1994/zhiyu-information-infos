package com.zhiyu.common.annotation.underline;

import java.lang.annotation.*;

/**
 * 不添加到到TreeMap
 * @author wengzhiyu
 * @date 2020/6/3
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface NotAddToTreeMap {
}