package com.zhiyu.common.service;

import java.util.concurrent.Future;

/**
 * @author wengzhiyu
 * @since 2021/5/14 15:30
 */
public interface DemoService {

    void  async1();

    Future<String> async2();

    void  async3();

}
