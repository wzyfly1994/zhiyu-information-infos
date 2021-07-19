package com.zhiyu.common;

import com.zhiyu.common.thread.ThreadFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 多线程调用Demo
 *
 * @author wengzhiyu
 * @date 2021/1/14
 */
public class ThreadDemo {


    public static void main(String[] args) throws Exception {

        ThreadFactory.init();
        ExecutorService executorService = ThreadFactory.getFirstExecutor();

        //方法一
        //execute 方法不会有返回值，能捕获到异常
        executorService.execute(ThreadDemo::send);
        //execute 方法不会有返回值，能捕获到异常，会引起线程阻塞
        Future<Integer> submit = executorService.submit(ThreadDemo::send);
        //等待线程执行完毕在执行下一个线程，并返回结果
        Integer value = submit.get();

        //方法二 获取返回值异步，不会引起线程阻塞，能捕获到异常
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(ThreadDemo::send, executorService);
        completableFuture.whenComplete((val, throwable) -> {
            //返回值
            System.out.println("val++=" + val);
            //异常信息
            System.out.println("throwable++=" + throwable);
        });

        //关闭线程
        executorService.shutdown();
    }


    private static Integer send() {
        String threadName = Thread.currentThread().getName();

        System.out.println("线程名字=====" + threadName);
        //int a=1/0;
        return 1;
    }

}
