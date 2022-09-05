package com.zhiyu.common.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyu.common.annotation.ActionLog;
import com.zhiyu.common.entity.dto.elasticsearch.SearchDocDto;
import com.zhiyu.common.entity.dto.log.ActionLogDto;
import com.zhiyu.common.feign.PayClient;
import com.zhiyu.common.service.DemoService;
import com.zhiyu.common.utils.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wengzhiyu
 * @since 2021/5/14 15:22
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    private final DemoService demoService;
    private final RedissonClient redissonClient;
    private final PayClient payClient;

    private final static Object lock = new Object();

    public DemoController(DemoService demoService, RedissonClient redissonClient, PayClient payClient) {
        this.demoService = demoService;
        this.redissonClient = redissonClient;
        this.payClient = payClient;
    }


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
        return demoService.locks();
    }


    @GetMapping("/setList/{meta}")
    public void setList(@PathVariable String meta) {
        demoService.setList(meta);
    }


    @GetMapping("/getList")
    public List<String> getList() {
        return demoService.getList();
    }


    @PostMapping("/clientHeader")
    public ResponseData demoClientHeader(@RequestHeader(value = "client", defaultValue = "1", required = false) String client
            , String name) {
        log.info("common--header---client-->{}", client);
        log.info("common--name---client-->{}", name);
        String value = payClient.testHttp(name);
        log.info("client返回-->{}", value);
        return ResponseData.success(value);
    }


    @PostMapping("/recordLog")
    @ActionLog(ActionLogDto.class)
    public ResponseData recordLogs(SearchDocDto searchDocDto) {
        log.info("recordLog----->{}", JSON.toJSONString(searchDocDto));
        return demoService.recordLog(searchDocDto);
    }

}
