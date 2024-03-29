package com.zhiyu.common.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池
 *
 * @author wengzhiyu
 * @date 2020/06/12 10:24
 */
@Slf4j
public class ThreadPoolFactory {

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private final static int KEEP_ALIVE_TIME = 60;

    /**
     * 线程池所使用的缓冲队列大小的默认值
     */
    private final static int WORK_QUEUE_CAPACITY = 1024;

    private static final ThreadFactory ASYNC_MAIN_THREAD_FACTORY =
            new ThreadFactoryBuilder().setNameFormat("AsyncThread-%d").build();

    /**
     * 生成固定大小的线程池
     *
     * @param corePoolSize 核心线程数
     * @param threadName   线程名称
     * @return 线程池
     */
    public static ExecutorService createFixedThreadPool(int corePoolSize, String threadName) {
        AtomicInteger threadNumber = new AtomicInteger(0);
        return new ThreadPoolExecutor(
                corePoolSize,
                corePoolSize * 2,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(WORK_QUEUE_CAPACITY),
                new ThreadFactory(){
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, threadName + "-" + threadNumber.getAndIncrement());
                    }
                },
                new RejectedExecutionHandler(){
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        if (!executor.isShutdown()) {
                            try {
                                //尝试阻塞式加入任务队列
                                executor.getQueue().put(r);
                            } catch (InterruptedException e) {
                                //保持线程的中端状态
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                });
    }

    /**
     * 核心线程数
     *
     * @param corePoolSize
     * @return ExecutorService
     */
    public static ExecutorService createFixedThreadPool(int corePoolSize) {
        log.info("初始化线程池 核心线程数：{} 最大线程数：{}", corePoolSize, corePoolSize);
        // @formatter:off
        return new ThreadPoolExecutor(
                corePoolSize,
                corePoolSize ,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                ASYNC_MAIN_THREAD_FACTORY);
        // @formatter:on
    }
}