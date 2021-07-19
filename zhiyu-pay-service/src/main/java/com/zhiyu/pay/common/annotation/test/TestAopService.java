package com.zhiyu.pay.common.annotation.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wengzhiyu
 * @date 2021/3/11
 */
@Service
@Slf4j
public class TestAopService {


    public Object aopValue(String value) {
        log.info("目标方法执行-----"+value);
        return value;
    }


}
