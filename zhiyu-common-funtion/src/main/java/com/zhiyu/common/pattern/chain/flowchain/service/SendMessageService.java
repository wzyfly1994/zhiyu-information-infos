package com.zhiyu.common.pattern.chain.flowchain.service;

import com.zhiyu.common.pattern.chain.flowchain.flow.Flow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wengzhiyu
 * @since 2021/5/24 17:40
 */
@Service
@Slf4j
public class SendMessageService implements Flow<SendMessageRule> {

    @Override
    public boolean isEnable(SendMessageRule context) {
        return true;
    }

    @Override
    public boolean execute(SendMessageRule context) {
        log.info("发送消息----->"+context.getMessage());
        return true;
    }
}
