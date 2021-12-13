package com.zhiyu.common;

import com.zhiyu.common.thread.ThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wengzhiyu
 * @date 2020/12/4
 */
@Slf4j
public class Test01 {

    @Test
    public void test() {

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2222");
                System.out.println("ThresdName---"+Thread.currentThread().getName());
                String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
                System.out.println("methodName+++++++++++"+methodName);
                for (int i = 0; i <10 ; i++) {
                }
            }
        });
        thread1.start();
    }




    @Test
    public void testThread() {
        ThreadFactory.init();
        ExecutorService executor = ThreadFactory.getFirstExecutor();
        try {
            Future<Integer> submit = executor.submit(this::send);
            System.out.println(submit.get());
            Future submits= executor.submit(this::send1);
           // Thread.currentThread().join();
            submits.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // send();
        //send();
        System.out.println("结束了");
//
//        int cpu=Runtime.getRuntime().availableProcessors();
//        System.out.println(cpu);
    }


    public    int send() {
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println("线程名字111:" + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 1;
    }


    public    void send1() {
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println("线程名字2222:" + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void testThreads(){
        AtomicInteger atomicInteger=new AtomicInteger(0);
        new ThreadPoolExecutor(10, 20, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024)
                , r -> new Thread(r, "Thread" + "-" + atomicInteger.getAndIncrement()), new ThreadPoolExecutor.AbortPolicy());
    }
}
