package com.zhiyu.common.annotation;

import com.zhiyu.common.entity.dto.log.ActionLogDto;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: wengzhiyu
 * @create: 2022-09-05 20:39
 **/
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionLog {

    Class<? extends ActionLogDto>[] value() default {};

}
