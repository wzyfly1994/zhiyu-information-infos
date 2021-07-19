package com.zhiyu.common.pattern.chain.flowchain.service;

import com.zhiyu.common.pattern.chain.flowchain.flow.Flow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wengzhiyu
 * @since 2021/5/24 17:41
 */
@Service
@Slf4j
public class LoadMessageService implements Flow<LoadMessageRule> {

    @Override
    public boolean isEnable(LoadMessageRule context) {
        return true;
    }


    @Override
    public boolean execute(LoadMessageRule context) {
        log.info("sendMsg----------------->");
        return true;
    }
}
