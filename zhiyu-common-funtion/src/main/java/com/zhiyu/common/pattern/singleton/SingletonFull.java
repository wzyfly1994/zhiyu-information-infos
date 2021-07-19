package com.zhiyu.common.pattern.singleton;

/**
 * 单例模式 饱汉
 *
 * @author wengzhiyu
 * @date 2020/12/31
 */
public class SingletonFull {

    private SingletonFull() {
    }

    private static volatile SingletonFull singletonFull = null;

    public static SingletonFull getInstance() {
        if (singletonFull == null) {
            synchronized (SingletonFull.class) {
                if (singletonFull == null) {
                    singletonFull = new SingletonFull();
                }
            }
        }
        return singletonFull;
    }

}
