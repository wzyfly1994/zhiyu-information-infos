package com.zhiyu.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhiyu.common.entity.pojo.SystemRole;
import com.zhiyu.common.service.DemoService;
import com.zhiyu.common.service.SystemRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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
    @Autowired
    private SystemRoleService systemRoleService;

    @Value("${defaultZones}")
    private String value;
    @Value("${test1}")
    private String test1;

    @Test
    public  void  test(){
//        Future<String> future = demoService.async2();
//        try {
//            System.out.println(future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        List<SystemRole> list = systemRoleService.list(new LambdaQueryWrapper<SystemRole>().eq(SystemRole::getRoleName, "123"));
//        System.out.println(list.size());

//        System.out.println(value);
//        System.out.println(test1);
        systemRoleService.list();

    }

}
