package com.zhiyu.common.service;

import com.zhiyu.common.entity.dto.elasticsearch.SearchDocDto;
import com.zhiyu.common.utils.response.ResponseData;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author wengzhiyu
 * @since 2021/5/14 15:30
 */
public interface DemoService {

    void  async1();

    Future<String> async2();

    void  async3();


    String  locks();

    void setList(String meta);

    List<String> getList();

    ResponseData  recordLog(SearchDocDto searchDocDto);


    void testTransactionalA();

    void testTransactionalB();

}
