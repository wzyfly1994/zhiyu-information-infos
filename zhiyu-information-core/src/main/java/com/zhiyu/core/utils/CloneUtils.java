package com.zhiyu.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Slf4j
@SuppressWarnings("unchecked")
public class CloneUtils {


    /**
     * 为对象执行浅克隆操作。注意：此方法要求目标类型有无参构造器。
     *
     * @param obj        要克隆的源对象。如果为null，方法将返回null。
     * @param targetType 目标类型的Class对象，必须能被实例化。
     * @param <T>        目标对象的类型。
     * @return 克隆出的对象，如果发生错误则返回null。
     */
    public static <T> T shallowClone(Object obj, Class<T> targetType) {
        if (obj == null) {
            // 源对象为null，直接返回null
            return null;
        }
        T clonedObj = null;
        try {
            // 获取并缓存无参构造器
            Constructor<T> constructor = targetType.getDeclaredConstructor();
            // 允许访问私有构造器
            constructor.setAccessible(true);
            // 使用反射创建目标类型的实例
            clonedObj = constructor.newInstance();
            // 复制源对象的属性到新对象
            BeanUtils.copyProperties(obj, clonedObj);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException("浅克隆对象失败:", e);
        }
        return clonedObj;
    }


    /**
     * 深度克隆对象的方法。
     * 该方法通过序列化和反序列化的方式实现对象的深度克隆，并将克隆的对象转换为目标类型返回。
     *
     * @param obj        需要被克隆的对象。
     * @param targetType 期望克隆对象转换成的目标类型。
     * @param <T>        目标类型参数。
     * @return 克隆后并转换为目标类型的新对象。
     * @throws RuntimeException         当深克隆过程中发生错误时抛出。
     * @throws IllegalArgumentException 当克隆的对象无法转换为目标类型时抛出。
     */
    public static <T> T deepClone(Object obj, Class<T> targetType) {
        try {
            // 将对象写入字节流
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.close();

            // 从字节流中读取对象
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object cloneObj = ois.readObject();
            ois.close();
            // 将克隆的对象转换为目标类型
            return targetType.cast(cloneObj);
        } catch (IOException | ClassNotFoundException e) {
            // 可以考虑抛出自定义异常或进行其他形式的错误处理
            throw new RuntimeException("Error occurred during deep cloning", e);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Cloned object cannot be cast to the specified targetType", e);
        }
    }


    /**
     * 使用 Fastjson 安全地深克隆对象。注意：此方法要求被克隆对象的类定义在序列化和反序列化时保持一致。
     *
     * @param obj 要被深克隆的对象
     * @param <T> 对象的类型
     * @return 克隆出的新对象
     */
    public static <T> T deepCloneWithFastjson(T obj) {
        try {
            // 使用TypeReference来确保类型安全
            String jsonString = JSON.toJSONString(obj);
            // 这里使用了DisableCircularReferenceDetect来防止循环引用的问题
            return JSON.parseObject(jsonString, new TypeReference<T>() {
            }, Feature.DisableCircularReferenceDetect);
        } catch (JSONException e) {
            throw new RuntimeException("json克隆对象失败:", e);
        }
    }
}
