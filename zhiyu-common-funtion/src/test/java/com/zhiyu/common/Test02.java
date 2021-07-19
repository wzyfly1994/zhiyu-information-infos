package com.zhiyu.common;

import org.springframework.context.annotation.Scope;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wengzhiyu
 * @date 2020/12/22
 */
@Scope
public class Test02 {


//    @Test
//    public void test() {
//
//    }

    public static void main(String[] args) throws InterruptedException {

       Lock  lock=new ReentrantLock();
       lock.lock();


       ThreadLocal<Integer> threadLocal=new ThreadLocal<>();
        threadLocal.set(1);
//        Res res=new Res();
//        ThreadDemo threadDemo = new ThreadDemo(res);
//        Threadnotify threadnotify=new Threadnotify(res);
//        Thread thread1 = new Thread(threadDemo);
//        Thread thread2 = new Thread(threadnotify);
//        thread1.start();
//        thread2.start();

        int number=500;
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < number; i++) {

                    System.out.println("ThreadName+++"+Thread.currentThread().getName());
                }
            }
        });
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < number; i++) {
                    System.out.println("ThreadName+++"+Thread.currentThread().getName());
                }
            }
        });

       // Thread.currentThread().join();

            thread.start();
            //Thread.yield();
            // thread.join();
            thread2.start();
            //thread2.join();

        for (int i = 0; i <number ; i++) {
            System.out.println("主线程--------------");
        }

    }

    static class ThreadDemo implements Runnable {
        public Res res;

        public ThreadDemo(Res res) {
            this.res = res;
        }

        int num = 100;

        @Override
        public void run() {
            try {
                while (true) {
                    if (num > 0) {
                        //  TimeUnit.SECONDS.sleep(2);
                        synchronized (this) {
                            System.out.println("wait");
                            this.wait();
                            System.out.println(Thread.currentThread().getName() + "砍了----" + num + "---执行了");
                            num--;
                        }
                        System.out.println("000000000000000000000000000");
                       // res.notify();
                    }
                    if (num <= 0) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Threadnotify implements Runnable {

        public Res res;

        public Threadnotify(Res res) {
            this.res = res;
        }

        @Override
        public void run() {
            synchronized (res) {
                System.out.println("notify");
                res.notify();
            }
        }
    }


    static class Res {
        public String name;
        public String sex;
        // flag false out线程未读取值
        public boolean flag = false;
        public Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
    }
}
