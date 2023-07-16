package com.zhiyu.common;

import com.zhiyu.common.thread.ThreadFactory;
import org.apache.velocity.runtime.directive.Foreach;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程调用Demo
 *
 * @author wengzhiyu
 * @date 2021/1/14
 */
public class ThreadDemo {

    private final static Object lock = new Object();

    private static final Lock locks = new ReentrantLock();
    private static Condition condition = locks.newCondition();

    private static volatile int index1 = 0;
    private static volatile int index2 = 0;
    private static volatile boolean flag = true;


    public static void main(String[] args) throws Exception {

        int[] arr1 = new int[]{1, 2, 3, 4};
        String[] arr2 = new String[]{"A", "B", "C", "D"};
        //new Thread(() -> {
        //    //synchronized (lock) {
        //    //    for (int num : arr1) {
        //    //        lock.notify();
        //    //        System.out.println(num);
        //    //        try {
        //    //            lock.wait();
        //    //        } catch (InterruptedException e) {
        //    //            throw new RuntimeException(e);
        //    //        }
        //    //    }
        //    // lock.notify();
        //    //}
        //    locks.lock();
        //    try {
        //        for (Integer num : arr1) {
        //            System.out.println(num);
        //            condition.signal();
        //            condition.await();
        //        }
        //        condition.signal();
        //    } catch (InterruptedException e) {
        //        throw new RuntimeException(e);
        //    } finally {
        //        locks.unlock();
        //    }
        //
        //}).start();
        //new Thread(() -> {
        //    //synchronized (lock) {
        //    //    for (String num : arr2) {
        //    //        lock.notify();
        //    //        System.out.println(num);
        //    //        try {
        //    //            lock.wait();
        //    //        } catch (InterruptedException e) {
        //    //            throw new RuntimeException(e);
        //    //        }
        //    //    }
        //    // lock.notify();
        //    //}
        //
        //    locks.lock();
        //    try {
        //        for (String num : arr2) {
        //            System.out.println(num);
        //            condition.signal();
        //            condition.await();
        //        }
        //        condition.signal();
        //    } catch (InterruptedException e) {
        //        throw new RuntimeException(e);
        //    } finally {
        //        locks.unlock();
        //    }
        //}).start();


        Thread t1 = new Thread(() -> {
            int i = 0;
            while (i < arr1.length) {
                if (flag) {
                    System.out.println(arr1[i++]);
                    flag = false;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            int i = 0;
            while (i < arr2.length) {
                if (!flag) {
                    System.out.println(arr2[i++]);
                    flag = true;
                }
            }
        });

        t1.start();
        t2.start();

        //ThreadFactory.init();
        //ExecutorService executorService = ThreadFactory.getFirstExecutor();
        //
        ////方法一
        ////execute 方法不会有返回值，能捕获到异常
        //executorService.execute(ThreadDemo::send);
        ////execute 方法不会有返回值，能捕获到异常，会引起线程阻塞
        //Future<Integer> submit = executorService.submit(ThreadDemo::send);
        ////等待线程执行完毕在执行下一个线程，并返回结果
        //Integer value = submit.get();
        //
        ////方法二 获取返回值异步，不会引起线程阻塞，能捕获到异常
        //CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(ThreadDemo::send, executorService);
        //completableFuture.whenComplete((val, throwable) -> {
        //    //返回值
        //    System.out.println("val++=" + val);
        //    //异常信息
        //    System.out.println("throwable++=" + throwable);
        //});
        //
        ////关闭线程
        //executorService.shutdown();

    }


    private static Integer send() {
        String threadName = Thread.currentThread().getName();

        System.out.println("线程名字=====" + threadName);
        //int a=1/0;
        return 1;
    }

}
