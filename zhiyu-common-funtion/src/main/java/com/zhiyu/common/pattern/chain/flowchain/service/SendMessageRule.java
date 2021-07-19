package com.zhiyu.common.pattern.chain.flowchain.service;

import com.zhiyu.common.pattern.chain.flowchain.flow.FlowChainRule;

/**
 * @author wengzhiyu
 * @since 2021/5/24 19:56
 */
public interface SendMessageRule extends FlowChainRule {

    /**
     * 加载消息
     *
     * @return
     */
    String getMessage();
}
