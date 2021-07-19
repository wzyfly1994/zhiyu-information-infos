package com.zhiyu.common.annotation.underline;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.TreeMap;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;

/**
 * @author wengzhiyu
 * @date 2020/6/3
 */
@Slf4j
public class MapBeanUtil {

    /**
     * 实体对象转成TreeMap
     *
     * @param objs 实体对象
     * @return
     */
    public static TreeMap<String, String> toTreeMap(Object... objs) {
        TreeMap<String, String> map = new TreeMap<>();
        for (Object obj : objs) {
            if (obj == null) {
                continue;
            }
            Class clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getAnnotation(NotAddToTreeMap.class) != null) {
                    continue;
                }
                Object value = null;
                try {
                    value = field.get(obj);
                } catch (Exception e) {
                    log.error("实体对象转成TreeMap错误：" ,e);
                }
                if (value == null || StringUtils.isBlank(value.toString())) {
                    continue;
                }
                Underline annotation = field.getAnnotation(Underline.class);
                if (annotation == null) {
                    map.put(field.getName(), value.toString());
                } else {
                    map.put(LOWER_CAMEL.to(LOWER_UNDERSCORE, field.getName()), value.toString());
                }
            }
        }
        return map;
    }

}