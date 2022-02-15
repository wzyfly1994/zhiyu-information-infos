package com.zhiyu.common.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wengzhiyu
 * @since 2022/2/8 11:47
 */
@Slf4j
public class DemoVolatile {

    static volatile int x = 0, y = 0;
    static volatile int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        begin();
    }

    public static void begin() throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    a = 1;
                    x = b;
                }
            }
        });

        Thread other = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    b = 1;
                    y = a;
                }
            }
        });
        one.start();
        other.start();
        one.join();
        other.join();
        System.out.println("(" + x + "," + y + ")");
    }

}