package com.zhiyu.common.pattern.singleton;

/**
 * 单例模式 饿汉
 *
 * @author wengzhiyu
 * @date 2020/12/31
 */
public class SingletonHungry {

    /**
     * 私有化构造方法
     */
    private SingletonHungry() {

    }

    private static SingletonHungry singletonHungry = new SingletonHungry();

    public static SingletonHungry getInstance() {
        return singletonHungry;
    }
}
