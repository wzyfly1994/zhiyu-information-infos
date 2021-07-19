package com.zhiyu.common;

import com.zhiyu.common.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author: wengzhiyu
 * @create: 2021-07-15 20:57
 **/
@SpringBootTest(classes = CommonApplication.class)
@RunWith(value = SpringRunner.class)
public class Test07 {
    @Autowired
    private DemoService demoService;

    @Test
    public  void  test(){
        Future<String> future = demoService.async2();
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
