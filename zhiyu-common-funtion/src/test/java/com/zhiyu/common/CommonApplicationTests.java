package com.zhiyu.common;

import com.zhiyu.common.feign.PayClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CommonApplication.class)
class CommonApplicationTests {

    @Autowired
    private PayClient payClient;

    @Test
    void contextLoads() {
        String port = payClient.testHttp();
        System.out.println(port);
    }

}
