package com.zhiyu.common.flowTest.service;

import com.alibaba.fastjson.JSON;
import com.zhiyu.common.flowTest.Flow;
import com.zhiyu.common.flowTest.context.MessageContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wengzhiyu
 * @since 2021/9/14 17:40
 */
@Service
@Slf4j
public class ProcessService implements Flow<MessageContext> {
    @Override
    public boolean valid(MessageContext context) {
        log.info("ProcessService----valid>:" + JSON.toJSONString(context));
        return false;
    }

    @Override
    public boolean execute(MessageContext context) {
        log.info("ProcessService----valid>:" + JSON.toJSONString(context));
        return false;
    }
}
