package com.zhiyu.pay;

import com.zhiyu.common.thread.ThreadFactory;
import com.zhiyu.pay.common.annotation.test.TestAopService;
import com.zhiyu.pay.config.PayConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;


@SpringBootTest(classes = PayServiceApplication.class)
@RunWith(SpringRunner.class)
public class  Test01 {

    @Autowired
    private PayConfig payConfig;
    @Autowired
    private TestAopService testAopService;

    @Test
    void contextLoads() {
        //System.out.println(payConfig.getSignType());
        testAopService.aopValue("CC");
    }


    public  static void main(String[] args) {
        ThreadFactory.init();
        ExecutorService executorService = ThreadFactory.getFirstExecutor();
        AtomicReference<Integer> x = new AtomicReference<>(0);

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(Test01::tt, executorService);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(Test01::tt, executorService);


        future1.whenComplete((a, b) -> {
            System.out.println("a++=" + a);
            System.out.println("b++=" + b);
            x.updateAndGet(v -> v + a);
        });
        future2.whenComplete((a, b) -> {
            System.out.println("a++=" + a);
            System.out.println("b++=" + b);
            x.updateAndGet(v -> v + a);
        });
        System.out.println("111111111111111111111");
        System.out.println("xxxxxxxxxxx" + x);
        executorService.shutdown();
    }

    public static Integer tt() {
        String name = Thread.currentThread().getName();
        System.out.println(name);
        System.out.println("来了");
        //int a = 1 / 0;
        System.out.println("去了");
        return 1;
    }
}
