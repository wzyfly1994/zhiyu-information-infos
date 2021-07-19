package com.zhiyu.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @author waengzhiyu
 * @date 2020/08/14 15:10
 */
public class ObjectUtils {

    /**
     * 是否为空
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof CharSequence) {
            return StringUtils.isBlank((CharSequence) object);
        } else if (object instanceof Collection) {
            return CollectionUtils.isEmpty((Collection) object);
        } else if (object instanceof Map) {
            return CollectionUtils.isEmpty((Map) object);
        } else if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        } else {
            return false;
        }
    }
}