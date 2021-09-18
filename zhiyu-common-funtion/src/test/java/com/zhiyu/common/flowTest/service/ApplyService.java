package com.zhiyu.common.flowTest.service;

import com.alibaba.fastjson.JSON;
import com.zhiyu.common.flowTest.Flow;
import com.zhiyu.common.flowTest.rule.RuleHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wengzhiyu
 * @since 2021/9/14 17:39
 */
@Service
@Slf4j
public class ApplyService implements Flow<RuleHandler> {


    @Override
    public boolean valid(RuleHandler context) {
        log.info("ApplyService----valid>:" + JSON.toJSONString(context));
        return false;
    }

    @Override
    public boolean execute(RuleHandler context) {
        log.info("ApplyService----execute>:" + JSON.toJSONString(context));
        return true;
    }
}
