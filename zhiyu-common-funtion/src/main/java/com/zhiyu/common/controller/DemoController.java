package com.zhiyu.common.controller;

import com.zhiyu.common.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author wengzhiyu
 * @since 2021/5/14 15:22
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    @Autowired
    private DemoService demoService;
    @Autowired
    private RedissonClient redissonClient;


    @GetMapping("/async")
    @Transactional(rollbackFor = Exception.class)
    public void demoAsync() {
       // demoService.async1();
        demoService.async2();
       // demoService.async3();
        log.info("《==================demoAsync====================================》");
    }

    @GetMapping("/redislock")
    public String demoRedisLock() {
        String threadName = Thread.currentThread().getName();
        log.info("=======================================================");
        log.info("=======================================================");
        log.info("=======================================================");
        log.info("线程：{}进方法", threadName);
        RLock rLock = redissonClient.getLock("redislock");
        if (rLock.tryLock()) {
            try {

                log.info("线程：{}进锁", threadName);
                try {
                    TimeUnit.SECONDS.sleep(3L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("线程：{}执行完毕", threadName);
            } catch (Exception e) {

            } finally {
                rLock.unlock();
            }
        } else {
            log.info("线程:{}没有锁住", threadName);
        }
        return threadName;
    }

    @GetMapping("/lock")
    public String demoLock() {
        String threadName = Thread.currentThread().getName();
        log.info("=======================================================");
        log.info("=======================================================");
        log.info("=======================================================");
        log.info("线程：{}进方法", threadName);
        synchronized (this) {
            log.info("线程：{}进锁", threadName);
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("线程：{}执行完毕", threadName);
        }
        return threadName;
    }
}
