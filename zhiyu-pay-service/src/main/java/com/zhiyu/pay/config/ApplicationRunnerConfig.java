package com.zhiyu.pay.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 容器启动后
 * @author wengzhiyu
 * @date 2021/1/12
 */
@Component
@Slf4j
public class ApplicationRunnerConfig implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        //初始化线程池
//        ThreadFactory.init();
//        log.info("初始化线程池完成");
    }
}
