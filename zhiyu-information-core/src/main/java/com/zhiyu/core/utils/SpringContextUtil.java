package com.zhiyu.core.utils;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {

    /**
     * -- GETTER --
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    @Getter
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    /**
     * 通过name获取Bean
     *
     * @param name Bean名称
     * @param <T>  Bean类型
     * @return 对应类型的Bean实例
     */
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 通过class获取Bean
     *
     * @param clazz Bean的Class类型
     * @param <T>   Bean类型
     * @return 对应类型的Bean实例
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 检查指定的Bean是否在容器中存在
     *
     * @param name Bean名称
     * @return 是否存在
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * 检查ApplicationContext是否已经注入
     */
    public static boolean isApplicationContextInjected() {
        return applicationContext != null;
    }
}