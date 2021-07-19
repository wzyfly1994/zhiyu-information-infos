package com.zhiyu.common.pattern.singleton;

/**
 * 单例模式 嵌套类
 *
 * @author wengzhiyu
 * @date 2020/12/31
 */
public class SingletonNested {
    private SingletonNested() {
    }

    /**
     * 嵌套类
     */
    private static class Holder {
        private static SingletonNested instance = new SingletonNested();
    }

    public static SingletonNested getInstance() {
        return Holder.instance;
    }
}
