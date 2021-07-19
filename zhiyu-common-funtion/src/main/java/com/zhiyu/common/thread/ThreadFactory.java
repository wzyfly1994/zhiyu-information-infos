package com.zhiyu.common.thread;

import java.util.concurrent.ExecutorService;

/**
 * 线程工厂
 *
 * @author wengzhiyu
 * @date 2020/06/23 16:05
 */
public class ThreadFactory {

    private static ExecutorService firstExecutor;

    private static ExecutorService secondExecutor;

    private ThreadFactory() {
    }

    /**
     * 初始化线程池
     */
    public static void init(){
        if (firstExecutor == null || secondExecutor ==null){
            synchronized (ThreadFactory.class){
                if (firstExecutor == null) {
                    firstExecutor = ThreadPoolFactory.createFixedThreadPool(desiredThreadNum(), "firstThreadPool");
                }
                if(secondExecutor == null){
                    secondExecutor = ThreadPoolFactory.createFixedThreadPool(desiredThreadNum() / 2, "secondThreadPool");
                }
            }
        }
    }

    public static ExecutorService getFirstExecutor() {
        return firstExecutor;
    }

    public static ExecutorService getSecondExecutor() {
        return secondExecutor;
    }


    /**
     * 理想的线程数，使用 2倍cpu核心数
     */
    public static int desiredThreadNum() {
        return Runtime.getRuntime().availableProcessors() * 2;
    }
}
